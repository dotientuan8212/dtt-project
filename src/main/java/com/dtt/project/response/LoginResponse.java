/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.response;

import lombok.Data;

/**
 *
 * @author TuanDT
 */
@Data
public class LoginResponse {
    private String token;
    private String tokenType;
    
}
