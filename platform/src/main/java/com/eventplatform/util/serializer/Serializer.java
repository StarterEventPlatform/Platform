package com.eventplatform.util.serializer;

import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.domain.model.Event;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.domain.model.Maintainer;
import com.eventplatform.domain.model.User;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Scope(value = "singleton")
@Component
public class Serializer {
    private ObjectMapper jsonMapper;
    private XmlMapper xmlMapper;

    public Serializer() {
        jsonMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    public String serialize(Object object, String type) throws SerializerException {
        try {
            switch (type) {
                case SerializerConstants.JSON_TYPE:
                    return jsonMapper.writeValueAsString(object);
                case SerializerConstants.XML_TYPE:
                    return xmlMapper.writeValueAsString(object);
                default:
                    throw new SerializerException(SerializerConstants.ERROR_UNKNOWN_TYPE);
            }
        } catch (IOException e) {
            throw new SerializerException(e.getMessage());
        }
    }

    // todo refactor
    public Object deserialize(String clazzText, String clazzType, String textType) throws SerializerException {
        try {
            switch (textType) {
                case SerializerConstants.JSON_TYPE:
                    switch (clazzType) {
                        case SerializerConstants.USER_CLAZZ:
                            return jsonMapper.readValue(clazzText, User.class);
                        case SerializerConstants.MAINTAINER_CLAZZ:
                            return jsonMapper.readValue(clazzText, Maintainer.class);
                        case SerializerConstants.GEOPOSITION_CLAZZ:
                            return jsonMapper.readValue(clazzText, GeoPosition.class);
                        case SerializerConstants.EVENT_CLAZZ:
                            return jsonMapper.readValue(clazzText, Event.class);
                        default:
                            throw new SerializerException(SerializerConstants.ERROR_UNKNOWN_TYPE + clazzType);
                    }
                case SerializerConstants.XML_TYPE:
                    switch (clazzType) {
                        case SerializerConstants.USER_CLAZZ:
                            return xmlMapper.readValue(clazzText, User.class);
                        case SerializerConstants.MAINTAINER_CLAZZ:
                            return xmlMapper.readValue(clazzText, Maintainer.class);
                        case SerializerConstants.GEOPOSITION_CLAZZ:
                            return xmlMapper.readValue(clazzText, GeoPosition.class);
                        case SerializerConstants.EVENT_CLAZZ:
                            return xmlMapper.readValue(clazzText, Event.class);
                        default:
                            throw new SerializerException(SerializerConstants.ERROR_UNKNOWN_TYPE + clazzType);
                    }
                default:
                    throw new SerializerException(SerializerConstants.ERROR_UNKNOWN_TYPE + textType);
            }

        } catch (IOException e) {
            throw new SerializerException(e.getMessage());
        }
    }
}
