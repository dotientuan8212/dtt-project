/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service;

import com.dtt.project.entity.User;

/**
 *
 * @author TuanDT
 */
public interface UserService {
    
    User getUserByEmail(String email);
    
    void insertUser(User user);
    
}
