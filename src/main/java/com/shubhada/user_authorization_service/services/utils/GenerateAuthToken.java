package com.shubhada.user_authorization_service.services.utils;

public class GenerateAuthToken {
    public static String generateRandomToken(){
        String alphaNumericString="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb=new StringBuilder(20);
        for(int i=0;i<20;i++){
            int index=(int)(alphaNumericString.length()*Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
       return sb.toString();
    }
}
