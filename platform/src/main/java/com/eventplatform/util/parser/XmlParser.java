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
     * @return Map parsedXml
     * @throws XmlParserException
     */
    public Map<String, String> getParsedXml(String xmlString) throws XmlParserException {
        HashMap<String, String> map;
        map = new HashMap<>();
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

    /**
     * @param xmlString
     * @return Map parsedXml
     * @throws XmlParserException
     */
    public Map<String, Object> getParsedXmlObject(String xmlString) throws XmlParserException {
        HashMap<String, Object> map;
        map = new HashMap<>();
        HashMap<String, Object> map1;
        map1 = new HashMap<>();
        Document xml = getDocumentFromString(xmlString);
        Node user = xml.getFirstChild();
        NodeList childs = user.getChildNodes();
        Node child;
        //for (int i = 0; i < childs.getLength(); i++) {
        //child = childs.item(i);
        //Node n = child.getFirstChild();
        //boolean fl = n.hasChildNodes();
        //map1.put(child.getNodeName(), child.getTextContent());
        map = getParsedMapRecurs(childs.item(0),childs.item(0));
        //}
        return map;
    }
    // todo Maintainer events = null
    private HashMap<String,Object> getParsedMapRecurs(Node node, Node firstNode) {
        HashMap<String, Object> map;
        map = new HashMap<>();
        while (node.getNextSibling() != null) {
            if (node.hasChildNodes()) {
                if (node.getFirstChild().hasChildNodes())
                    map.put(node.getNodeName(), getParsedMapRecurs(node.getFirstChild(),node.getFirstChild()));
                else
                    map.put(node.getNodeName(), node.getTextContent());
            }
            node = node.getNextSibling();
        }
        return map;
    }

    private HashMap<String,Object> getParsedMap(Node node) {
        HashMap<String, Object> map;
        map = new HashMap<>();
        if (node.hasChildNodes()) {
            if (node.getFirstChild().hasChildNodes())
                map.put(node.getNodeName(), getParsedMap(node.getFirstChild()));
            else
                map.put(node.getNodeName(), node.getTextContent());
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