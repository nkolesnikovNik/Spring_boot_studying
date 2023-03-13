package com.ah;

import java.security.MessageDigest;
import java.util.Base64;

public class Hasher {

    //Generate hash from URL
    public static String getHash(String url) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] arr = md.digest(url.getBytes());
        return Base64.getEncoder().encodeToString(arr);
    }
}