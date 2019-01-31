package com.shibro.nativeproducts.utils;


import org.thymeleaf.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TokenUtil {
    public static String generateToken(String s) throws UnsupportedEncodingException {
        if(StringUtils.isEmpty(s)){
            return s;
        }
        String token = Base64.getEncoder().encodeToString((s).getBytes("UTF-8"));
        return token;
    }
}
