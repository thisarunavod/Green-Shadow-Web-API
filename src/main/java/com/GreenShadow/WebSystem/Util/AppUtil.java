package com.GreenShadow.WebSystem.Util;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public class AppUtil {

    public static String createNoteID(){
        return "NOTE "+UUID.randomUUID();
    }
    public static String createUserID(){
        return "USER "+UUID.randomUUID();
    }
    public static String toBase64ProfilePic(byte[] field_image){
        // base64 formatt ekata ape file format eka convert karaa
        return Base64.getEncoder().encodeToString(field_image);
    }

}
