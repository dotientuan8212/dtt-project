/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service;

import com.dtt.project.entity.User;
import com.dtt.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TuanDT
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        User userDetail = null;
        try {
            userDetail = userRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetail;
    }

    @Override
    public void insertUser(User user) {
        try {
            userRepository.insetUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
