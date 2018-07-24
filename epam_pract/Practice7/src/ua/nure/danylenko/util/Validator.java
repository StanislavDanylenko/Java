package ua.nure.danylenko.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.danylenko.constants.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Validator {

    private static void validate(String xmlFileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        // make parser validating
        // turn validation on
        dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

        // turn on xsd validation
        dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);

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
    }

    public static boolean isValid(String xmlFileName) {
        try {
            validate(xmlFileName);
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            return false;
        }
        return true;
    }

}
