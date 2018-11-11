package com.eventplatform.util.serializer;

public class SerializerConstants {
    // Serializer types
    public static final String
            JSON_TYPE = "JSON",
            XML_TYPE = "XML",
            EVENT_CLAZZ = "EVENT",
            GEOPOSITION_CLAZZ = "GEOPOSITION",
            MAINTAINER_CLAZZ = "MAINTAINER",
            USER_CLAZZ = "USER";

    // Error message in serializer
    public static final String
            ERROR_UNKNOWN_TYPE = "Error! Can not find this type for serialization.\n" +
            "\nHINT: Please choose type from UtilConstants :)\n" +
            "CHOSEN_TYPE: ";
}
