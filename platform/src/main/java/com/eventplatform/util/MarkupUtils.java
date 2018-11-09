package com.eventplatform.util;

import com.eventplatform.exception.utils.MarkupUtilsException;
import com.eventplatform.model.Entity;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MarkupUtils {

    private static MarkupUtils instance;

    private MarkupUtils() {
    }

    public static MarkupUtils getInstance() {
        if (instance == null) instance = new MarkupUtils();
        return instance;
    }

    public String serialize(Entity entity, String type) throws MarkupUtilsException {
        String serialized;
        try {
            switch (type) {
                case UtilConstants.JSON_TYPE:
                    ObjectMapper jsonMapper = new ObjectMapper();
                    serialized = jsonMapper.writeValueAsString(entity);
                    break;
                case UtilConstants.XML_TYPE:
                    XmlMapper xmlMapper = new XmlMapper();
                    serialized = xmlMapper.writeValueAsString(entity);
                    break;
                default:
                    throw new MarkupUtilsException();
            }
        } catch (IOException e) {
            throw new MarkupUtilsException();
        }
        return serialized;
    }
}
