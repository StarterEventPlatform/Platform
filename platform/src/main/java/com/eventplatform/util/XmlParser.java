package com.eventplatform.util;

import com.eventplatform.exception.utils.XmlParserException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gleb Durygin
 */

public class XmlParser {

    private static XmlParser xmlParser = null;

    private XmlParser() {
    }

    public static XmlParser getXmlParser() {
        if (xmlParser == null)
            xmlParser = new XmlParser();
        return xmlParser;
    }

    // todo return Map parsedXml
    public Map<String, String> getParsedXml(String xmlString) throws XmlParserException {
        HashMap<String, String> map;
        map = new HashMap<String, String>();
        Document xml = getDocumentFromString(xmlString);
        Node user = xml.getFirstChild();
        NodeList childs = user.getChildNodes();
        Node child;
        for (int i = 0; i < childs.getLength(); i++) {
            child = childs.item(i);
            map.put(child.getNodeName(), child.getTextContent());
        }
        return map;
    }

    // todo return Document getDocumentFromString
    public Document getDocumentFromString(String xmlString) throws XmlParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            throw new XmlParserException(UtilConstants.ERROR_TRANSFORM_STRING_TO_DOCUMENT);
        }
    }

    // todo return String getStringFromDocument
    public String getStringFromDocument(Document document) throws XmlParserException, TransformerException {
        try (StringWriter stringWriter = new StringWriter()) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new XmlParserException(UtilConstants.ERROR_TRANSFORM_DOCUMENT_TO_STRING);
        }
    }

    public Document createDocument() throws XmlParserException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new XmlParserException(UtilConstants.ERROR_CREATE_DOCUMENT);
        }
    }

    public Node createNodeWithValueAndAttributes(Document document, String nodeName, String value, String[] attributes) {
        Element node = document.createElement(nodeName);
        node.appendChild(document.createTextNode(value));
        for (int i = 0; i < attributes.length; i++)
            node.appendChild(document.createAttribute(attributes[i]));
        return node;
    }

    public void addElementWithValueAndAttributesToDocument(Document document, String nodeName, String value, String[] attributes) {
        Element node = document.createElement(nodeName);
        node.appendChild(document.createTextNode(value));
        for (int i = 0; i < attributes.length; i++)
            node.appendChild(document.createAttribute(attributes[i]));
        document.appendChild(node);
    }

    public NodeList getElementsByTagName(String xml, String tagName) throws XmlParserException {
        return getDocumentFromString(xml).getDocumentElement().getElementsByTagName(tagName);
    }

    // todo return NamedMap Attributes
    public NamedNodeMap getAttributes(Node node) {
        return node.getAttributes();
    }

    public String getAttribute(Node node, String attributeName) {

        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }

    // todo return String ValueByName
    public String getValueByName(String xml, String tagName) throws XmlParserException {
        try {
            return getElementsByTagName(xml, tagName).item(0).getFirstChild().getNodeValue();
        } catch (NullPointerException e) {
            throw new XmlParserException(UtilConstants.ERROR_GET_VALUE_BY_NAME);
        }
    }

    // todo return Node get Node by tagname
    public Node getFirstNode(String xml, String tagName) throws XmlParserException {
        return getElementsByTagName(xml, tagName).item(0);
    }
}