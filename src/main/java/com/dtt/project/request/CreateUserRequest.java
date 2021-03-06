/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class CreateUserRequest {

    @NotEmpty(message = "email_required")
    @Email(message = "email_invalid")
    private String email;

    @NotEmpty(message = "name_required")
    private String name;

    @NotEmpty(message = "phone_required")
    private String phone;

    @NotEmpty(message = "password_required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
            message = "password_invalid")
    private String password;

}
