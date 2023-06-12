package com.sm.exception;

import lombok.Getter;

@Getter
public class AppointmentRequestServiceException extends RuntimeException{
    private final ErrorType errorType;

    public AppointmentRequestServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AppointmentRequestServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
