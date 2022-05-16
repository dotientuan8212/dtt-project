/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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

    @Email(message = "email_required")
    private String email;

    @NotEmpty(message = "name_required")
    private String name;

    @NotEmpty(message = "phone_required")
    private String phone;

    @NotEmpty(message = "password_required")
//    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
//    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
//            message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

}
