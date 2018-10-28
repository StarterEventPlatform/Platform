package com.eventplatform.tmp;

import com.eventplatform.factory.UserFactory;
import com.eventplatform.model.User;
import com.eventplatform.util.PasswordEncoder;
import com.eventplatform.util.UtilConstants;
import com.eventplatform.util.container.UserContainer;

import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        // test md5
        // System.out.println(PasswordEncoder.getInstance().encode("test", UtilConstants.ENCODE_MD5));


        User test1 = new User(new BigInteger("0"), new Date(System.currentTimeMillis()), "name", "surname",
                "login", "email", PasswordEncoder.getInstance().encode("password", UtilConstants.ENCODE_MD5));
        User test2 = UserFactory.createUser(test1);
        test2.setId(new BigInteger("1"));

        User test3 = UserFactory.createUser(new BigInteger("2"), "name", "surname",
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


    }
}
