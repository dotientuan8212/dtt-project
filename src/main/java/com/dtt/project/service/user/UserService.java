/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service.user;

import com.dtt.project.entity.User;
import com.dtt.project.exceptions.HandlerException;
import com.dtt.project.response.UserDetail;
import java.util.List;

/**
 *
 * @author TuanDT
 */
public interface UserService {

    User getUserByEmail(String email) throws HandlerException;

    void insertUser(User user) throws HandlerException;
    
    List<UserDetail> getAllUser() throws HandlerException;
}
