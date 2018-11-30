package com.eventplatform.tmp;

import com.eventplatform.pojo.controller.DataController;
import com.eventplatform.pojo.controller.DataControllerAggregator;
import com.eventplatform.pojo.controller.DataControllerConstants;
import com.eventplatform.tmp.factory.MaintainerFactory;
import com.eventplatform.pojo.klass.GeoPosition;
import com.eventplatform.pojo.klass.Maintainer;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.util.PasswordEncoder;
import com.eventplatform.util.UtilConstants;
import com.eventplatform.util.parser.XmlParser;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;

import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        // test md5
        // System.out.println(PasswordEncoder.getInstance().encode("test", UtilConstants.ENCODE_MD5));
/*
        System.out.println("******************************MAIN***************************************");
        System.out.println("************************TEST USER_FACTORY********************************");
        String testJson = "{\n" +
                "  \"id\": 4,\n" +
                "  \"creationDate\": 1541882041781,\n" +
                "  \"user\": {\n" +
                "    \"id\": 5,\n" +
                "    \"creationDate\": 1541882041781,\n" +
                "    \"name\": \"userName\",\n" +
                "    \"surname\": \"userSurname\",\n" +
                "    \"login\": \"userLogin\",\n" +
                "    \"email\": \"userEmail\",\n" +
                "    \"password\": \"221068207e125b97beb4e2d062e888b1\"\n" +
                "  },\n" +
                "  \"name\": \"name\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"events\": [],\n" +
                "  \"geoPosition\": {\n" +
                "    \"id\": 6,\n" +
                "    \"creationDate\": 1541882041781,\n" +
                "    \"latitude\": 1.0,\n" +
                "    \"longitude\": 1.0\n" +
                "  }\n" +
                "}";
        System.out.println(JsonParser.getJsonParser().getParsedJson(testJson));


        User test1 = new User(0, new Date(System.currentTimeMillis()), "name", "surname",
                "login", "email", PasswordEncoder.getInstance().encode("password", UtilConstants.ENCODE_MD5));
        Maintainer test11 = MaintainerFactory.createMaintainer(4, new User(5,
                        new Date(System.currentTimeMillis()), "userName", "userSurname", "userLogin", "userEmail",
                        PasswordEncoder.getInstance().encode("userPassword", UtilConstants.ENCODE_MD5)), "name", "description",
                new GeoPosition(6, new Date(System.currentTimeMillis()), 1.0f, 1.0f));
        XmlParser xmlParser = XmlParser.getXmlParser();

        String string = Serializer.getInstance().serialize(test11, SerializerConstants.XML_TYPE);
        Map<String,Object> map = xmlParser.getParsedXml(string);
        System.out.println("Maintainer: " + map.toString());
        /*System.out.println(test12);
        String str = Serializer.getInstance().serialize(test12, UtilConstants.JSON_TYPE);
        JsonParser jsonParser = JsonParser.getJsonParser();
        Map<String, String> testmap = jsonParser.getParsedJson(str);
        System.out.println(testmap.toString());
        System.out.println(str);*/

        /*User test2 = UserFactory.createUser(test1);
        test2.setId(0);

        User test3 = UserFactory.createUser(2, "name", "surname",
                "login", "email", PasswordEncoder.getInstance().encode("password", UtilConstants.ENCODE_MD5));


        System.out.println("test1:" + test1);
        System.out.println("test2:" + test2);
        System.out.println("test3:" + test3);

        UserContainer userContainer = new UserContainer();
        userContainer.addValue(0, test1);
        userContainer.addValue(1, test2);
        userContainer.addValue(2, test3);
        System.out.println(userContainer);

        try {
            userContainer.addValue(0, test1);
        } catch (Exception e) {
            System.out.println("exc detected");
            System.out.println(e.getClass().getName());
        }

        System.out.println("*********************TEST GEOPOSITION_FACTORY****************************");
        GeoPosition test4 = new GeoPosition(0, new Date(System.currentTimeMillis()), 1.0f, 1.0f);
        GeoPosition test5 = GeoPosistionFactory.createGeoPosition(test4);
        test5.setId(0);

        GeoPosition test6 = GeoPosistionFactory.createGeoPosition(2, 2.0f, 2.0f);

        System.out.println("test4:" + test4);
        System.out.println("test5:" + test5);
        System.out.println("test6:" + test6);

        System.out.println("************************TEST EVENT_FACTORY********************************");
        Event test7 = new Event(0, new Date(System.currentTimeMillis()), "name", "description",
                new GeoPosition(1, new Date(System.currentTimeMillis())), "type", new Date(1));
        Event test8 = EventFactory.createEvent(test7);
        test8.setId(0);

        Event test9 = EventFactory.createEvent(2, "name", "description",
                new GeoPosition(4, new Date(System.currentTimeMillis())), "type", new Date(1));
        System.out.println("test7:" + test7);
        System.out.println("test8:" + test8);
        System.out.println("test9:" + test9);

        System.out.println("**********************TEST MAINTAINER_FACTORY*****************************");
        Maintainer test10 = new Maintainer(0, new Date(System.currentTimeMillis()), new User(1,
                new Date(System.currentTimeMillis()), "userName", "userSurname", "userLogin", "userEmail",
                PasswordEncoder.getInstance().encode("userPassword", UtilConstants.ENCODE_MD5)), "name", "description",
                new GeoPosition(2, new Date(System.currentTimeMillis()), 1.0f, 1.0f));
        Maintainer test11 = MaintainerFactory.createMaintainer(test10);
        test11.setId(0);

        Maintainer test12 = MaintainerFactory.createMaintainer(4, new User(5,
                        new Date(System.currentTimeMillis()), "userName", "userSurname", "userLogin", "userEmail",
                        PasswordEncoder.getInstance().encode("userPassword", UtilConstants.ENCODE_MD5)), "name", "description",
                new GeoPosition(6, new Date(System.currentTimeMillis()), 1.0f, 1.0f));
        System.out.println("test10:" + test10);
        System.out.println("test11:" + test11);
        System.out.println("test12:" + test12);
        System.out.println("**************************************************************************");

        //System.out.println("**********************TEST MARKUP_UTILS*****************************");
        String json = Serializer.getInstance().serialize(test1, SerializerConstants.JSON_TYPE);
        //System.out.println("user json: " + json);
        //System.out.println("user xml: " + Serializer.getInstance().serialize(test1, SerializerConstants.XML_TYPE));

        DataController cu = DataControllerAggregator.getInstance().getByType(DataControllerConstants.USER_TYPE);
        cu.create(json, SerializerConstants.JSON_TYPE);

        Maintainer test12 = MaintainerFactory.createMaintainer(4, (User) cu.get(0), "name", "descr",
                new GeoPosition(6, new Date(System.currentTimeMillis()), 1.0f, 1.0f));

        String xml = Serializer.getInstance().serialize(test12, SerializerConstants.XML_TYPE);
        DataController<Maintainer> mu = DataControllerAggregator.getInstance().getByType(DataControllerConstants.MAINTAINER_TYPE);
        mu.create(xml, SerializerConstants.XML_TYPE);
        test12 = mu.get(4);
        System.out.println(test12.toString());
        System.out.println(mu.getAll().toString());

        System.out.println("user from maint xml: " + XmlParser.getXmlParser().getParsedXml(xml).get("user"));
        //System.out.println(JsonParser.getJsonParser().getParsedJson(json));
        System.out.println("**************************************************************************");*/
    }
}
