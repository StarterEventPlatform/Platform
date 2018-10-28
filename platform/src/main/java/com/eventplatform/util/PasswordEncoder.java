package com.eventplatform.util;

import com.eventplatform.exception.utils.PasswordEncoderException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// todo move to bean when we create web app and remove singleton
public class PasswordEncoder {

    private static PasswordEncoder instance;

    private PasswordEncoder() {
    }

    public static PasswordEncoder getInstance() {
        if (instance == null) instance = new PasswordEncoder();
        return instance;
    }

    public String encode(String password, String encodeType) throws PasswordEncoderException {
        try {
            MessageDigest md = MessageDigest.getInstance(encodeType);
            md.update(password.getBytes());

            StringBuffer sb = new StringBuffer();
            String hex;
            for (byte data : md.digest()) {
                hex = Integer.toHexString(0xFF & data);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncoderException(UtilConstants.ENCODE_EX_MSG + encodeType);
        }
    }

}
