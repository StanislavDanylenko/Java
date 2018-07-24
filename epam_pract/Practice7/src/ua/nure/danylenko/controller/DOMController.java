package ua.nure.danylenko.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.danylenko.constants.Constants;
import ua.nure.danylenko.constants.XML;
import ua.nure.danylenko.entity.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
* Controller for DOM parser.
*
*
@author S.Danylenko
*
*/
public class DOMController {

    private String xmlFileName;

    // main container
    private Planes planes;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Planes getTest() {
        return planes;
    }

    /**
     * Parses XML document.
     *
     * @param validate
     *            If true validate XML document against its XML schema.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        // make parser validating
        if (validate) {
            // turn validation on
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

            // turn on xsd validation
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        // set error handler
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw exception if XML document is NOT valid
                throw e;
            }
        });

        // parse XML document
        Document document = db.parse(xmlFileName);

        // get root element
        Element root = document.getDocumentElement();

        // create container
        planes = new Planes();

        // obtain questions nodes
        NodeList planeNodes = root
                .getElementsByTagName(XML.PLANE.value());

        // process questions nodes
        for (int j = 0; j < planeNodes.getLength(); j++) {
            ParentPlane question = getPlane(planeNodes.item(j));
            // add question to container
            planes.getPlane().add(question);
        }
    }

    /**
     * Extracts plane object from the question XML node.
     *
     * @param pNode
     *            Plane node.
     * @return Plane object.
     */
    private ParentPlane getPlane(Node pNode) {
        ParentPlane plane;
        Element pElement = (Element) pNode;

        // process plane text
        Node qtNode = pElement.getElementsByTagName(XML.TYPE.value())
                .item(0);

        String typePlane = qtNode.getTextContent();
        if ("Scout".equals(typePlane)) {
            plane = new ScoutPlane();
        } else {
            plane = new NotScoutPlane();
        }

        // set parameters
        Node model = pElement.getElementsByTagName(XML.MODEL.value())
                .item(0);
        plane.setModel(model.getTextContent());

        Node origin = pElement.getElementsByTagName(XML.ORIGIN.value())
                .item(0);
        plane.setOrigin(origin.getTextContent());

        Price price = getPrice(pElement.getElementsByTagName(XML.PRICE.value()).item(0));
        plane.setPrice(price);

        Parameters parameters = getParameters(pElement.getElementsByTagName(XML.PARAMETERS.value()).item(0));
        plane.setParameters(parameters);

        if (typePlane.equals("Scout")) {
            CharsScout chars = getCharsScout(pElement.getElementsByTagName(XML.CHARS.value()).item(0));
            plane.setChars(chars);
        } else {
            Chars chars = getChars(pElement.getElementsByTagName(XML.CHARS.value()).item(0));
            plane.setChars(chars);
        }
        return plane;
    }
    /**
     * Extracts price object from the answer XML node.
     *
     * @param prNode
     *            Price node.
     * @return Price object.
     */
    private Price getPrice(Node prNode) {
        Price price = new Price();
        Element prElement = (Element) prNode;

        // process correct
        String unit = prElement.getAttribute(XML.UNIT.value());
        price.setUnit(String.valueOf(unit));

        // process content
        String content = prElement.getTextContent();
        price.setContent(Double.valueOf(content));

        return price;
    }
    /**
     * Extracts length, width or height object from the answer XML node.
     *
     * @param uNode
     *            UnitAttribute node.
     * @return UnitAttribute object.
     */
    private UnitAttribute getUnitAttribute(Node uNode) {
        UnitAttribute par = new UnitAttribute();
        Element uElement = (Element) uNode;

        // process correct
        String unit = uElement.getAttribute(XML.UNIT.value());
        par.setUnit(String.valueOf(unit));

        // process content
        String content = uElement.getTextContent();
        par.setValue(Double.valueOf(content));

        return par;
    }
    /**
     * Extracts parametrs object from the answer XML node.
     *
     * @param parNode
     *            Parameter node.
     * @return Parameter object.
     */
    private Parameters getParameters(Node parNode) {
        Parameters par = new Parameters();
        Element parElement = (Element) parNode;

        par.setLength(getUnitAttribute(parElement.getElementsByTagName(XML.LENGTH.value()).item(0)));
        par.setWidth(getUnitAttribute(parElement.getElementsByTagName(XML.WIDTH.value()).item(0)));
        par.setHeight(getUnitAttribute(parElement.getElementsByTagName(XML.HEIGHT.value()).item(0)));

        return par;
    }
    /**
     * Extracts charScout object from the answer XML node.
     *
     * @param chsNode
     *            CharsScout node.
     * @return CharsScout object.
     */
    private CharsScout getCharsScout(Node chsNode) {
        CharsScout cScout = new CharsScout();
        Element chsElement = (Element) chsNode;

        Node places = chsElement.getElementsByTagName(XML.PLACES.value())
                .item(0);
        cScout.setPlaces(Integer.parseInt(places.getTextContent()));

        cScout.setType(TypeScout.SCOUT);
        Node radar = chsElement.getElementsByTagName(XML.RADAR.value())
                .item(0);
        cScout.setRadar(Boolean.valueOf(radar.getTextContent()));

        return cScout;
    }
    /**
     * Extracts char object from the answer XML node.
     *
     * @param chNode
     *            Chars node.
     * @return Chars object.
     */
    private Chars getChars(Node chNode) {
        Chars chars = new Chars();
        Element chElement = (Element) chNode;

        Node places = chElement.getElementsByTagName(XML.PLACES.value())
                .item(0);
        chars.setPlaces(Integer.parseInt(places.getTextContent()));

        Node type = chElement.getElementsByTagName(XML.TYPE.value())
                .item(0);
        chars.setType(Type.fromValue(type.getTextContent()));

        Node radar = chElement.getElementsByTagName(XML.RADAR.value())
                .item(0);
        chars.setRadar(Boolean.valueOf(radar.getTextContent()));

        Node rockets = chElement.getElementsByTagName(XML.ROCKETS.value())
                .item(0);
//        cScout.setRadar(Boolean.valueOf(radar.getTextContent()));

        Ammunition ammunition = new Ammunition();
        ammunition.setRockets(Integer.parseInt(rockets.getTextContent()));
        chars.setAmmunition(ammunition);

        return chars;
    }

    /**
     * Creates and returns DOM of the Test container.
     *
     * @param planes
     *            Planes object.
     * @throws ParserConfigurationException
     */
    public static Document getDocument(Planes planes) throws ParserConfigurationException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // create root element
        Element tElement = document.createElement(XML.PLANES.value());

        // add root element
        document.appendChild(tElement);

        for (ParentPlane pl : planes.getPlane()) {
            ScoutPlane scoutPlane = null;
            NotScoutPlane notScoutPlane = null;
            if(pl instanceof ScoutPlane) {
                scoutPlane = (ScoutPlane)pl;
            } else {
               notScoutPlane = (NotScoutPlane)pl;
            }

            Element planeElement = document.createElement(XML.PLANE.value());
            tElement.appendChild(planeElement);
            {
                Element mElement = document.createElement(XML.MODEL.value());
                mElement.setTextContent(pl.getModel());
                planeElement.appendChild(mElement);

                Element oElement = document.createElement(XML.ORIGIN.value());
                oElement.setTextContent(pl.getOrigin());
                planeElement.appendChild(oElement);

                Element cElement = document.createElement(XML.CHARS.value());
                planeElement.appendChild(cElement);

                // additing Chars children--------
                {
                    if (scoutPlane != null) {
                        Element tyElement = document.createElement(XML.TYPE.value());
                        tyElement.setTextContent(scoutPlane.getChars().getType().value());
                        cElement.appendChild(tyElement);

                        Element plElement = document.createElement(XML.PLACES.value());
                        plElement.setTextContent((Integer)scoutPlane.getChars().getPlaces()+"");
                        cElement.appendChild(plElement);

                        Element rElement = document.createElement(XML.RADAR.value());
                        rElement.setTextContent(scoutPlane.getChars().isRadar()+"");
                        cElement.appendChild(rElement);
                    } else {
                        Element tyElement = document.createElement(XML.TYPE.value());
                        tyElement.setTextContent(notScoutPlane.getChars().getType().value());
                        cElement.appendChild(tyElement);

                        Element plElement = document.createElement(XML.PLACES.value());
                        plElement.setTextContent(notScoutPlane.getChars().getPlaces()+"");
                        cElement.appendChild(plElement);

                        Element aElement = document.createElement(XML.AMMUNITION.value());
                        cElement.appendChild(aElement);
                        {
                            Element roElement = document.createElement(XML.ROCKETS.value());
                            roElement.setTextContent(notScoutPlane.getChars().getAmmunition().getRockets()+"");
                            aElement.appendChild(roElement);
                        }

                        Element rElement = document.createElement(XML.RADAR.value());
                        rElement.setTextContent(""+notScoutPlane.getChars().isRadar());
                        cElement.appendChild(rElement);
                    }
                }
                Element pElement = document.createElement(XML.PARAMETERS.value());
                planeElement.appendChild(pElement);
                // addition parameteres children----
                {
                    Element lElement = document.createElement(XML.LENGTH.value());
                    lElement.setTextContent(""+pl.getParameters().getLength().getValue());
                    lElement.setAttribute(XML.UNIT.value(), pl.getParameters().getLength().getUnit());
                    pElement.appendChild(lElement);

                    Element wElement = document.createElement(XML.WIDTH.value());
                    wElement.setTextContent(""+pl.getParameters().getWidth().getValue());
                    wElement.setAttribute(XML.UNIT.value(), pl.getParameters().getWidth().getUnit());
                    pElement.appendChild(wElement);

                    Element hElement = document.createElement(XML.HEIGHT.value());
                    hElement.setTextContent(""+pl.getParameters().getHeight().getValue());
                    hElement.setAttribute(XML.UNIT.value(), pl.getParameters().getHeight().getUnit());
                    pElement.appendChild(hElement);
                }
                Element prElement = document.createElement(XML.PRICE.value());
                prElement.setTextContent(""+pl.getPrice().getContent());
                prElement.setAttribute(XML.UNIT.value(), pl.getPrice().getUnit());
                planeElement.appendChild(prElement);
            }
        }
        return document;
    }

    /**
     * Saves Planes object to XML file.
     *
     * @param planes
     *            Planes object to be saved.
     * @param xmlFileName
     *            Output XML file name.
     * @throws ParserConfigurationException
     * @throws TransformerException
     *
     */
    public static void saveToXML(Planes planes, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        // Test -> DOM -> XML
        saveToXML(getDocument(planes), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document
     *            DOM to be saved.
     * @param xmlFileName
     *            Output XML file name.
     * @throws TransformerException
     */
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {

        // try to parse NOT valid XML document with validation on (failed)
        DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
        try {
            // parse with validation (failed)
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Test object --> " + domContr.getTest());
            System.err.println("====================================");
        }

        //Document d = getDocument(domContr.planes);

        // try to parse NOT valid XML document with validation off (success)
        domContr.parse(false);

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + domContr.getTest());
        System.out.println("====================================");

        // save test in XML file*/
        Planes test = domContr.getTest();
        DOMController.saveToXML(test, Constants.INVALID_XML_FILE + ".dom-result.xml");
    }
}
