package com.sm.exception;

import lombok.Getter;

@Getter
public class AppointmentServiceException extends RuntimeException{
    private final ErrorType errorType;

    public AppointmentServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AppointmentServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
