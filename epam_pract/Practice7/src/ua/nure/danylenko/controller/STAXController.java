package ua.nure.danylenko.controller;

/**
 * Controller for STAX parser.
 *
 * @author S.Danylenko
 */

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.danylenko.constants.Constants;
import ua.nure.danylenko.constants.XML;
import ua.nure.danylenko.entity.*;

import java.io.IOException;

public class STAXController extends DefaultHandler {

    private String xmlFileName;

    // main container
    private Planes planes;

    public Planes getTest() {
        return planes;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     *@throws XMLStreamException
     */
    public void parse() throws XMLStreamException {

        ParentPlane parentPlane = null;
        ScoutPlane scoutPlane = null;
        NotScoutPlane notScoutPlane = null;
        Chars chars = null;
        CharsScout charsScout = null;
        Ammunition ammunition = null;
        Parameters parameters = null;
        Price price = null;
        UnitAttribute length = null;
        UnitAttribute height = null;
        UnitAttribute width = null;

        // current element name holder
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            // skip any empty content
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            // region START
            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (XML.PLANES.equalsTo(currentElement)) {
                    planes = new Planes();
                    continue;
                }

                if (XML.PLANE.equalsTo(currentElement)) {
                    Attribute attribute = startElement.getAttributes().next();
                    if (attribute != null) {
                        String str = attribute.getValue();
                        str = str.substring(4);
                        if ("ScoutPlane".equals(str)) {
                            scoutPlane = new ScoutPlane();
                            parentPlane = scoutPlane;
                        } else {
                            notScoutPlane = new NotScoutPlane();
                            parentPlane = notScoutPlane;
                        }
                    }
                    continue;
                }

                if (XML.CHARS.equalsTo(currentElement)) {
                    if (parentPlane instanceof ScoutPlane) {
                        charsScout = new CharsScout();
                    } else {
                        chars = new Chars();
                    }
                    continue;
                }

                if (XML.AMMUNITION.equalsTo(currentElement)) {
                    ammunition = new Ammunition();
                    continue;
                }

                if (XML.PARAMETERS.equalsTo(currentElement)) {
                    parameters = new Parameters();
                    continue;
                }
                // Paremeters
                {
                    if (XML.LENGTH.equalsTo(currentElement)) {
                        length = new UnitAttribute();
                        Attribute attribute = startElement.getAttributeByName(
                                new QName(XML.UNIT.value()));
                        if (attribute != null) {
                            length.setUnit(attribute.getValue());
                        }
                        continue;
                    }
                    if (XML.WIDTH.equalsTo(currentElement)) {
                        width = new UnitAttribute();
                        Attribute attribute = startElement.getAttributeByName(
                                new QName(XML.UNIT.value()));
                        if (attribute != null) {
                            width.setUnit(attribute.getValue());
                        }
                        continue;
                    }
                    if (XML.HEIGHT.equalsTo(currentElement)) {
                        height = new UnitAttribute();
                        Attribute attribute = startElement.getAttributeByName(
                                new QName(XML.UNIT.value()));
                        if (attribute != null) {
                            height.setUnit(attribute.getValue());
                        }
                        continue;
                    }
                }

                if (XML.PRICE.equalsTo(currentElement)) {
                    price = new Price();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XML.UNIT.value()));
                    if (attribute != null) {
                        price.setUnit(attribute.getValue());
                    }
                }
            }
            // endregion
            //region CHARACTERS
            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (XML.MODEL.equalsTo(currentElement)) {
                    parentPlane.setModel(characters.getData());
                    continue;
                }

                if (XML.ORIGIN.equalsTo(currentElement)) {
                    parentPlane.setOrigin(characters.getData());
                    continue;
                }
                // CHARS
                {
                    if (XML.TYPE.equalsTo(currentElement)) {
                        if (charsScout != null) {
                            charsScout.setType(TypeScout.SCOUT);
                        } else if (chars != null) {
                            chars.setType(Type.fromValue(characters.getData()));
                        }
                        continue;
                    }

                    if (XML.PLACES.equalsTo(currentElement)) {
                        if (charsScout != null) {
                            charsScout.setPlaces(Integer.parseInt(characters.getData()));
                        } else if (chars != null) {
                            chars.setPlaces(Integer.parseInt(characters.getData()));
                        }
                        continue;
                    }

                    if (XML.ROCKETS.equalsTo(currentElement)) {
                        ammunition.setRockets(Integer.parseInt(characters.getData()));
                        continue;
                    }

                    if (XML.RADAR.equalsTo(currentElement)) {
                        if (charsScout != null) {
                            charsScout.setRadar(Boolean.parseBoolean(characters.getData()));
                        } else if (chars != null) {
                            chars.setRadar(Boolean.parseBoolean(characters.getData()));
                        }
                        continue;
                    }
                }
                // PARAMETERS
                {
                    if (XML.LENGTH.equalsTo(currentElement)) {
                        length.setValue(Double.valueOf(characters.getData()));
                        continue;
                    }
                    if (XML.WIDTH.equalsTo(currentElement)) {
                        width.setValue(Double.valueOf(characters.getData()));
                        continue;
                    }
                    if (XML.HEIGHT.equalsTo(currentElement)) {
                        height.setValue(Double.valueOf(characters.getData()));
                        continue;
                    }
                }
                if (XML.PRICE.equalsTo(currentElement)) {
                    price.setContent(Double.valueOf(characters.getData()));
                }
            }
            // endregion
            //region END
            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (XML.PLANE.equalsTo(localName)) {
                    planes.getPlane().add(parentPlane);
                    scoutPlane = null;
                    notScoutPlane = null;
                    parentPlane = null;
                    continue;
                }

                if (XML.AMMUNITION.equalsTo(localName)) {
                    chars.setAmmunition(ammunition);
                    continue;
                }

                if (XML.CHARS.equalsTo(localName)) {
                    if (charsScout != null) {
                        parentPlane.setChars(charsScout);
                    } else if (chars != null) {
                        parentPlane.setChars(chars);
                    }
                    chars = null;
                    charsScout = null;
                    continue;
                }

                if (XML.PARAMETERS.equalsTo(localName)) {
                    parameters.setHeight(height);
                    parameters.setWidth(width);
                    parameters.setLength(length);
                    parentPlane.setParameters(parameters);
                    continue;
                }

                if (XML.PRICE.equalsTo(localName)) {
                    parentPlane.setPrice(price);
                }
            }
            //endregion
        }

        reader.close();
    }

    public static void main(String[] args) throws Exception {

        // try to parse (valid) XML file (success)
        STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
        staxContr.parse(); // <-- do parse (success)

        // obtain container
        Planes test = staxContr.getTest();

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + test);
        System.out.println("====================================");

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        String xmlFileName = "input.xml";
        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        Planes test1 = staxController.getTest();

        // sort  (case 3)
        //Sorter.sortAnswersByContent(test);

        // save
        String outputXmlFile = "output.stax.xml";
        DOMController.saveToXML(test1, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);
    }

}
