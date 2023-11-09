package com.shubhada.user_authorization_service.controllers;

import com.shubhada.user_authorization_service.dtos.ErrorResponseDTO;
import com.shubhada.user_authorization_service.exceptions.InvalidLoginCredentials;
import com.shubhada.user_authorization_service.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler({UserAlreadyExistsException.class,InvalidLoginCredentials.class})
    public ResponseEntity<ErrorResponseDTO> handleUserExistsException(Exception exception){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
        errorResponseDTO.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);

    }


}
