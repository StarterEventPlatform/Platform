package com.eventplatform.util;

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
    public Map<String, String> getParsedJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {
        };
        try {
            map = mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getValueByName(String name, String json) {
        ObjectMapper mapper = new ObjectMapper();
        String nameValue = "";
        try {
            JsonNode jsonNode = mapper.readTree(json);
            nameValue = jsonNode.get(name).asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameValue;
    }

}
