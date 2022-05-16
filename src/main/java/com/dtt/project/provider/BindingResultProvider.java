/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author TuanDT
 */
@Service
public class BindingResultProvider {

    @Autowired
    private MessageSource messageSource;

    public String getErrorMessage(BindingResult result) {
        String message = null;
        for (Object object : result.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;

                message = messageSource.getMessage(fieldError, null);
            }

        }
        return message;
    }
}
