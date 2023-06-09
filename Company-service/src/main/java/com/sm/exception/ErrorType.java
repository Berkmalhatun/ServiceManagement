package com.sm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    TOKEN_NOT_CREATED(3001,"Token not created",HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE(4001,"E-mail is already exist",HttpStatus.BAD_REQUEST),

    PASSWORD_MISMATCH(4002,"Passwords do not match.",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(4003,"E-mail is not found please try again",HttpStatus.BAD_REQUEST),
    PASSWORD_UNMATCH(4004,"Passwords are not matched",HttpStatus.BAD_REQUEST),

    LOGIN_ERROR(4005,"Login error",HttpStatus.BAD_REQUEST),
    COMPANY_NAME_DUPLICATE(4006,"Company name is already exist.",HttpStatus.BAD_REQUEST),

    COMPANY_NOT_CREATED(4007,"Company is not created",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(4008,"The company is not found.",HttpStatus.BAD_REQUEST),
    COMPANY_ALREADY_ACTIVE(4009,"The company is already active",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5001,"Token not created",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5100,"Eternal Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parameter Error",HttpStatus.BAD_REQUEST),





    ;


    private int code;
    private String message;
     HttpStatus httpStatus;
}
