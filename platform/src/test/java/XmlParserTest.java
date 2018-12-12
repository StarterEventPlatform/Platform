import org.junit.Assert;

public class XmlParserTest extends Assert {
    /*
    private Map<String, Object> mapTest;
    private Document documentTest;
    private String nameValue = "password";
    private String testValue = "5f4dcc3b5aa765d69";
    private XmlParser xmlParser;
    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
            "<User>" +
            "<id>0</id>" +
            "<name>name</name>" +
            "<password>5f4dcc3b5aa765d69</password>" +
            "</User>";

    @Before
    public void init() throws ParserConfigurationException {
        // mapTest
        mapTest = new HashMap<>();
        mapTest.put("id", "0");
        mapTest.put("name", "name");
        mapTest.put("password", "5f4dcc3b5aa765d69");
        // documentTest
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        documentTest = dBuilder.newDocument();
        Element element = documentTest.createElement("User");
        documentTest.appendChild(element);
        Element element2 = documentTest.createElement("id");
        element.appendChild(element2);
        element2.insertBefore(documentTest.createTextNode("0"), element2.getLastChild());
        element2 = documentTest.createElement("name");
        element.appendChild(element2);
        element2.insertBefore(documentTest.createTextNode("name"), element2.getLastChild());
        element2 = documentTest.createElement("password");
        element.appendChild(element2);
        element2.insertBefore(documentTest.createTextNode("5f4dcc3b5aa765d69"), element2.getLastChild());
        xmlParser = XmlParser.getXmlParser();
    }

    @Test
    public void testGetParsedXml() throws XmlParserException {
        Map<String, Object> map;
        map = xmlParser.getParsedXml(xml);
        assertThat(map, is(mapTest));
    }

    @Test
    public void testGetStringFromDocument() throws XmlParserException, TransformerException {
        String string;
        string = xmlParser.getStringFromDocument(documentTest);
        assertEquals(string, xml);
    }

    @Test
    public void testGetValueByName() throws XmlParserException {
        String string;
        string = xmlParser.getValueByName(xml, nameValue);
        assertEquals(testValue, string);
    }

    @After
    public void removeMap() {
        mapTest.clear();
    }*/
}
