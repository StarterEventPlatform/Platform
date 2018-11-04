package com.eventplatform.tmp;

import com.eventplatform.factory.EventFactory;
import com.eventplatform.factory.GeoPosistionFactory;
import com.eventplatform.factory.MaintainerFactory;
import com.eventplatform.factory.UserFactory;
import com.eventplatform.model.Event;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.model.Maintainer;
import com.eventplatform.model.User;
import com.eventplatform.util.PasswordEncoder;
import com.eventplatform.util.UtilConstants;
import com.eventplatform.util.container.UserContainer;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        // test md5
        // System.out.println(PasswordEncoder.getInstance().encode("test", UtilConstants.ENCODE_MD5));

        System.out.println("******************************MAIN***************************************");
        System.out.println("************************TEST USER_FACTORY********************************");
        User test1 = new User(0, new Date(System.currentTimeMillis()), "name", "surname",
                "login", "email", PasswordEncoder.getInstance().encode("password", UtilConstants.ENCODE_MD5));
        User test2 = UserFactory.createUser(test1);
        test2.setId(0);

        User test3 = UserFactory.createUser(2, "name", "surname",
                "login", "email", PasswordEncoder.getInstance().encode("password", UtilConstants.ENCODE_MD5));


        System.out.println("test1:" + test1);
        System.out.println("test2:" + test2);
        System.out.println("test3:" + test3);

        UserContainer userContainer = UserContainer.getInstance();
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
    }
}
