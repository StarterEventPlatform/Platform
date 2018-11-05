package com.eventplatform.util;

import com.eventplatform.exception.utils.JsonParserException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    private static JsonParser parserJson = null;

    private JsonParser() {

    }

    public static JsonParser getParserJson() {
        if (parserJson == null)
            parserJson = new JsonParser();
        return parserJson;

    }

    /*
     * MAAP[KEY=NAMETAG,VALUE=MAP[KEY=ATTRIBUUTES||VALUE, VALUE = MAP [NAMEOFATTR OR NAME OF VALUE]]]
     *
     *
     * */
    // todo return Map parsedJson
    public Map<String, String> getParsedJson(String json) throws JsonParserException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {
        };
        try {
            map = mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonParserException(UtilConstants.ERROR_TRANSFORM_TO_MAP);
        }
        return map;
    }

    // todo return String ValueByName
    public String getValueByName(String name, String json) throws JsonParserException {
        ObjectMapper mapper = new ObjectMapper();
        String nameValue = "";
        try {
            JsonNode jsonNode = mapper.readTree(json);
            nameValue = jsonNode.get(name).asText();
        } catch (IOException ex) {
            throw new JsonParserException(UtilConstants.ERROR_TRANSFORM_TO_MAP);
        } catch (NullPointerException e) {
            throw new JsonParserException(UtilConstants.ERROR_GET_VALUE_BY_NAME + " : " + name);
        }
        return nameValue;
    }

}
