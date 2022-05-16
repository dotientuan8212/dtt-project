/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service;

import com.dtt.project.entity.User;
import com.dtt.project.model.UserPrincipalCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TuanDT
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail = new User();
        Boolean enable = true;
        try {
            userDetail = userService.getUserByEmail(username);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
         UserDetails userDetails = (UserDetails) new UserPrincipalCustom( userDetail, userDetail.getEmailAddress(), userDetail.getPassword(), enable);
        return userDetails;
    }
    
}
