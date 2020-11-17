package net.renfei.sdk.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.TransformerFactory;

/**
 * <p>Title: JXmlUtils</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class JXmlUtils {
    private final static String FRATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
    private final static String FRATURE_EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    private final static String FRATURE_EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    private final static String FRATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature(FRATURE_DISALLOW_DOCTYPE_DECL, true);
        documentBuilderFactory.setFeature(FRATURE_EXTERNAL_GENERAL_ENTITIES, false);
        documentBuilderFactory.setFeature(FRATURE_EXTERNAL_PARAMETER_ENTITIES, false);
        documentBuilderFactory.setFeature(FRATURE_LOAD_EXTERNAL_DTD, false);
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);
        return documentBuilderFactory.newDocumentBuilder();
    }

    public static Document newDocument() throws ParserConfigurationException {
        return newDocumentBuilder().newDocument();
    }

    public static SAXParserFactory newSAXParserFactory() throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setFeature(FRATURE_EXTERNAL_GENERAL_ENTITIES, false);
        saxParserFactory.setFeature(FRATURE_EXTERNAL_PARAMETER_ENTITIES, false);
        saxParserFactory.setFeature(FRATURE_LOAD_EXTERNAL_DTD, false);
        return saxParserFactory;
    }

    public static SAXParser newSAXParser() throws ParserConfigurationException, SAXException {
        return newSAXParserFactory().newSAXParser();
    }

    public static XMLReader newXMLReader() throws SAXException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setFeature(FRATURE_DISALLOW_DOCTYPE_DECL, true);
        // This may not be strictly required as DTDs shouldn't be allowed at all,
        // per previous line.
        reader.setFeature(FRATURE_LOAD_EXTERNAL_DTD, false);
        reader.setFeature(FRATURE_EXTERNAL_GENERAL_ENTITIES, false);
        reader.setFeature(FRATURE_EXTERNAL_PARAMETER_ENTITIES, false);
        return reader;
    }

    public static TransformerFactory newTransformerFactory() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        return transformerFactory;
    }

    public static XMLInputFactory newXMLInputFactory() {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        // This disables DTDs entirely for that factory
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        // disable external entities
        xmlInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        return xmlInputFactory;
    }
}
