package com.eventplatform.util.parser;

import com.eventplatform.exception.utils.XmlParserException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

    /**
     * @param xmlString
     * @return HashMap parsedXml
     * @throws XmlParserException
     */
    public HashMap<String, Object> getParsedXml(String xmlString) throws XmlParserException {
        HashMap<String, Object> map;
        Document xml = getDocumentFromString(xmlString);
        Node user = xml.getFirstChild();
        NodeList childs = user.getChildNodes();
        map = getParsedMap(childs);
        return map;
    }

    /**
     * @param node
     * @return HashMap parsedMapFromNode
     */
    private HashMap<String, Object> getParsedMapFromNode(Node node) {
        HashMap<String, Object> map;
        map = new HashMap<>();
        if (node.hasChildNodes()) {
            if (node.getFirstChild().hasChildNodes())
                map.put(node.getNodeName(), getParsedMap(node.getChildNodes()));
            else
                map.put(node.getNodeName(), node.getTextContent());
        } else
            map.put(node.getNodeName(), node.getTextContent());
        return map;
    }

    /**
     * @param nodes
     * @return HashMap parsedMap
     */
    private HashMap<String, Object> getParsedMap(NodeList nodes) {
        HashMap<String, Object> map;
        map = new HashMap<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            String str = node.getNodeName();
            map.putAll(getParsedMapFromNode(node));
        }
        return map;
    }

    /**
     * @param xmlString
     * @return Document document
     * @throws XmlParserException
     */
    public Document getDocumentFromString(String xmlString) throws XmlParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            throw new XmlParserException(ParserConstants.ERROR_TRANSFORM_STRING_TO_DOCUMENT);
        }
    }

    /**
     * @param document
     * @return String document
     * @throws XmlParserException
     * @throws TransformerException
     */
    public String getStringFromDocument(Document document) throws XmlParserException, TransformerException {
        try (StringWriter stringWriter = new StringWriter()) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new XmlParserException(ParserConstants.ERROR_TRANSFORM_DOCUMENT_TO_STRING);
        }
    }

    /**
     * @param document
     * @param nodeName
     * @param value
     * @return Node node
     */
    public Node createNodeWithValue(Document document, String nodeName, String value) {
        Element node = document.createElement(nodeName);
        node.appendChild(document.createTextNode(value));
        return node;
    }

    /**
     * @param xml
     * @param tagName
     * @return NodeList nodeList
     * @throws XmlParserException
     */
    public NodeList getElementsByTagName(String xml, String tagName) throws XmlParserException {
        return getDocumentFromString(xml).getDocumentElement().getElementsByTagName(tagName);
    }

    /**
     * @param node
     * @return NamedNodeMap namedNodeMap
     */
    public NamedNodeMap getAttributes(Node node) {
        return node.getAttributes();
    }

    /**
     * @param node
     * @param attributeName
     * @return String attribute
     */
    public String getAttribute(Node node, String attributeName) {
        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }

    /**
     * @param xml
     * @param tagName
     * @return String value
     * @throws XmlParserException
     */
    public String getValueByName(String xml, String tagName) throws XmlParserException {
        try {
            return getElementsByTagName(xml, tagName).item(0).getFirstChild().getNodeValue();
        } catch (NullPointerException e) {
            throw new XmlParserException(ParserConstants.ERROR_GET_VALUE_BY_NAME);
        }
    }

    /**
     * @param xml
     * @param tagName
     * @return Node node
     * @throws XmlParserException
     */
    public Node getFirstNode(String xml, String tagName) throws XmlParserException {
        return getElementsByTagName(xml, tagName).item(0);
    }
}