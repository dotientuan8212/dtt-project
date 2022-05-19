/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TuanDT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotEmpty(message = "email_required")
    @Email(message = "email_invalid")
    private String email;
    
    @NotEmpty(message = "password_required")
    private String password;
}
