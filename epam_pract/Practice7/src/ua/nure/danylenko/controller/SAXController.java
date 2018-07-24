package ua.nure.danylenko.controller;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.danylenko.constants.Constants;
import ua.nure.danylenko.constants.XML;
import ua.nure.danylenko.entity.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Controller for SAX parser.
 *
 * @author S.Danylenko
 *
 */
public class SAXController extends DefaultHandler {
    private String xmlFileName;

    // current element name holder
    private String currentElement;

    // main container
    private Planes planes;

    private ParentPlane parentPlane;
    private ScoutPlane scoutPlane;
    private NotScoutPlane notScoutPlane;
    private Chars chars;
    private CharsScout charsScout;
    private Ammunition ammunition;
    private Parameters parameters;
    private Price price;
    private UnitAttribute length;
    private UnitAttribute height;
    private UnitAttribute width;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     * @param validate
     *            If true validate XML document against its XML schema. With
     *            this parameter it is possible make parser validating.
     *@throws ParserConfigurationException
     *@throws SAXException
     *@throws IOException
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain sax parser factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // XML document contains namespaces
        factory.setNamespaceAware(true);

        // set validation
        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    // ///////////////////////////////////////////////////////////
    // ERROR HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        // if XML document not valid just throw exception
        throw e;
    }

    public Planes getTest() {
        return planes;
    }

    // ///////////////////////////////////////////////////////////
    // CONTENT HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        currentElement = localName;

        if (XML.PLANES.equalsTo(currentElement)) {
            planes = new Planes();
            return;
        }

        if (XML.PLANE.equalsTo(currentElement)) {
            if (attributes.getLength() > 0) {
                String str = attributes.getValue(0);
                str = str.substring(4);
                if ("ScoutPlane".equals(str)) {
                    scoutPlane = new ScoutPlane();
                    parentPlane = scoutPlane;
                } else {
                    notScoutPlane = new NotScoutPlane();
                    parentPlane = notScoutPlane;
                }
            }
        }

        if (XML.CHARS.equalsTo(currentElement)) {
            if (parentPlane instanceof ScoutPlane) {
                charsScout = new CharsScout();
            } else {
                chars = new Chars();
            }
        }

        if (XML.AMMUNITION.equalsTo(currentElement)) {
            ammunition = new Ammunition();
        }

        if (XML.PARAMETERS.equalsTo(currentElement)) {
            parameters = new Parameters();
        }
        // Paremeters
        {
            if (XML.LENGTH.equalsTo(currentElement)) {
                length = new UnitAttribute();
                if (attributes.getLength() > 0) {
                    length.setUnit(attributes.getValue(0));
                }
            }
            if (XML.WIDTH.equalsTo(currentElement)) {
                width = new UnitAttribute();
                if (attributes.getLength() > 0) {
                    width.setUnit(attributes.getValue(0));
                }
            }
            if (XML.HEIGHT.equalsTo(currentElement)) {
                height = new UnitAttribute();
                if (attributes.getLength() > 0) {
                    height.setUnit(attributes.getValue(0));
                }
            }
        }

        if(XML.PRICE.equalsTo(currentElement)) {
            price = new Price();
            if (attributes.getLength() > 0) {
                price.setUnit(attributes.getValue(0));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }

        if (XML.MODEL.equalsTo(currentElement)) {
            parentPlane.setModel(elementText);
            return;
        }

        if (XML.ORIGIN.equalsTo(currentElement)) {
            parentPlane.setOrigin(elementText);
            return;
        }
        // CHARS
        {
            if (XML.TYPE.equalsTo(currentElement)) {
                if (charsScout != null) {
                    charsScout.setType(TypeScout.SCOUT);
                } else if (chars != null) {
                    chars.setType(Type.fromValue(elementText));
                }
                return;
            }

            if (XML.PLACES.equalsTo(currentElement)) {
                if (charsScout != null) {
                    charsScout.setPlaces(Integer.parseInt(elementText));
                } else if (chars != null) {
                    chars.setPlaces(Integer.parseInt(elementText));
                }
                return;
            }

            if (XML.ROCKETS.equalsTo(currentElement)) {
                ammunition.setRockets(Integer.parseInt(elementText));
                return;
            }

            if (XML.RADAR.equalsTo(currentElement)) {
                if (charsScout != null) {
                    charsScout.setRadar(Boolean.parseBoolean(elementText));
                } else if (chars != null) {
                    chars.setRadar(Boolean.parseBoolean(elementText));
                }
                return;
            }
        }
        // PARAMETERS
        {
            if (XML.LENGTH.equalsTo(currentElement)) {
                this.length.setValue(Double.valueOf(elementText));
                return;
            }
            if (XML.WIDTH.equalsTo(currentElement)) {
                this.width.setValue(Double.valueOf(elementText));
                return;
            }
            if (XML.HEIGHT.equalsTo(currentElement)) {
                this.height.setValue(Double.valueOf(elementText));
                return;
            }
        }
        if(XML.PRICE.equalsTo(currentElement)) {
            price.setContent(Double.valueOf(elementText));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if(XML.PLANE.equalsTo(localName)) {
            planes.getPlane().add(parentPlane);
            scoutPlane = null;
            notScoutPlane = null;
            parentPlane = null;
            return;
        }

        if (XML.AMMUNITION.equalsTo(localName)) {
            chars.setAmmunition(ammunition);
            return;
        }

        if (XML.CHARS.equalsTo(localName)) {
            // just add question to container
            if (charsScout != null) {
                parentPlane.setChars(charsScout);
            } else if (chars != null) {
                parentPlane.setChars(chars);
            }
            chars = null;
            charsScout = null;
            return;
        }

        if (XML.PARAMETERS.equalsTo(localName)) {
            parameters.setHeight(height);
            parameters.setWidth(width);
            parameters.setLength(length);
            parentPlane.setParameters(parameters);
            return;
        }

        if (XML.PRICE.equalsTo(localName)) {
            parentPlane.setPrice(price);
            return;
        }
    }

    public static void main(String[] args) throws Exception {

        // try to parse valid XML file (success)
        SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

        // do parse with validation on (success)
        saxContr.parse(true);

        // obtain container
        Planes test = saxContr.getTest();

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + test);
        System.out.println("====================================");

        // now try to parse NOT valid XML (failed)
        saxContr = new SAXController(Constants.INVALID_XML_FILE);
        try {
            // do parse with validation on (failed)
            saxContr.parse(true);
        } catch (Exception ex) {
            System.err.println("====================================");
            System.err.println("Validation is failed:\n" + ex.getMessage());
            System.err
                    .println("Try to print test object:" + saxContr.getTest());
            System.err.println("====================================");
        }

        // and now try to parse NOT valid XML with validation off (success)
        saxContr.parse(false);

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + saxContr.getTest());
        System.out.println("====================================");

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        String xmlFileName = "input.xml";

        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        Planes test1 = saxController.getTest();

        // save
        String outputXmlFile = "output.sax.xml";

        // other way:
        DOMController.saveToXML(test1, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);
    }
}
