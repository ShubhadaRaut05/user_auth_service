package com.shubhada.user_authorization_service.exceptions;

public class InvalidLoginCredentials extends  Exception{

    public InvalidLoginCredentials(String message){
        super(message);
    }
}
