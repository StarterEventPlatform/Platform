import com.eventplatform.exception.utils.JsonParserException;
import com.eventplatform.util.JsonParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

public class JsonParserTest extends Assert {
    private Map<String, String> mapTest;
    private String nameValue = "lastName";
    private String valueTest = "Tester";
    private JsonParser jsonParser;
    private String jsonTest = "{\n" +
            "   \"firstName\": \"Test\",\n" +
            "   \"lastName\": \"Tester\"}";

    @Before
    public void init() {
        mapTest = new HashMap<>();
        mapTest.put("firstName", "Test");
        mapTest.put("lastName", "Tester");
        jsonParser = JsonParser.getJsonParser();
    }

    @Test
    public void returnMapFromParsed() throws JsonParserException {
        Map<String, String> map;
        map = jsonParser.getParsedJson(jsonTest);
        assertThat(map, is(mapTest));
    }

    @Test
    public void returnStringValue() throws JsonParserException {
        String value = jsonParser.getValueByName(nameValue, jsonTest);
        assertEquals(value, valueTest);
    }

    @After
    public void removeMap() {
        mapTest.clear();
    }
}
