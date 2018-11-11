package com.eventplatform.util;

public class UtilConstants {

    // Encoder constants
    public static final String
            ENCODE_MD5 = "MD5",
            ENCODE_EX_MSG = "Error! No such algorithm. Algorithm: ";

    // Error message in JsonParser
    public static final String
            ERROR_TRANSFORM_TO_MAP = "Error! Json doesn't transform to map",
            ERROR_GET_VALUE_BY_NAME = "Error! Not found value by name";

    // Error message in XmlParser
    public static final String
            ERROR_TRANSFORM_DOCUMENT_TO_STRING = "Error! Can't transform Document to String",
            ERROR_CREATE_DOCUMENT = "Error! Create Document",
            ERROR_TRANSFORM_STRING_TO_DOCUMENT = "Error! Can't transform String to Document";

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
