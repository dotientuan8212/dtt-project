/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.exceptions;

import org.springframework.http.HttpStatus;

public class HandlerException extends Exception {

    private final String errorCode;

    private final HttpStatus status;
    
    public HandlerException(String errorCode, HttpStatus status) {
        super("ErroCode::: " + errorCode);
        this.errorCode = errorCode;
        this.status = status;
    }
    
    public HandlerException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
    
    public HandlerException(String errorCode, HttpStatus status, Throwable cause) {
        super.addSuppressed(cause);
        //super(cause);
        this.errorCode = errorCode;
       this.status = status;
    }
    
    public HandlerException(String errorCode, String message, HttpStatus status, Throwable cause) {
        super("[" + errorCode + "]: " + message);
        super.addSuppressed(cause);
        this.errorCode = errorCode;
        this.status = status;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
