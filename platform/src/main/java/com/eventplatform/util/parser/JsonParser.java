package com.eventplatform.util.parser;

import com.eventplatform.exception.utils.JsonParserException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Scope(value = "singleton")
@Component
public class JsonParser {

    /**
     * @param json
     * @return Map parsedJson
     * @throws JsonParserException
     */
    public Map<String, Object> getParsedJson(String json) throws JsonParserException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
        };
        try {
            map = mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonParserException(ParserConstants.ERROR_TRANSFORM_TO_MAP);
        }
        return map;
    }

    /**
     * @param name
     * @param json
     * @return String value
     * @throws JsonParserException
     */
    public String getValueByName(String name, String json) throws JsonParserException {
        ObjectMapper mapper = new ObjectMapper();
        String nameValue = "";
        try {
            JsonNode jsonNode = mapper.readTree(json);
            nameValue = jsonNode.get(name).asText();
        } catch (IOException ex) {
            throw new JsonParserException(ParserConstants.ERROR_TRANSFORM_TO_MAP);
        } catch (NullPointerException e) {
            throw new JsonParserException(ParserConstants.ERROR_GET_VALUE_BY_NAME + " : " + name);
        }
        return nameValue;
    }
}
