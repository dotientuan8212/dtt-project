/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service.user;

import com.dtt.project.entity.User;
import com.dtt.project.exceptions.HandlerException;
import com.dtt.project.repository.UserRepository;
import com.dtt.project.response.UserDetail;
import com.dtt.project.service.BindingResultService;
import com.dtt.project.service.message.MessageService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private MessageService messageDao;

    @Autowired
    BindingResultService bindingResultProvider;

    @Override
    public User getUserByEmail(String email) throws HandlerException {
        User u = null;
        try {
            u = userRepository.findByEmail(email);
            if (u == null) {
                throw new HandlerException("user_not_found", messageDao.getMessageByName("user_not_found"), HttpStatus.NOT_FOUND);
            }
        } catch (HandlerException e) {
            throw new HandlerException(e.getErrorCode(), e.getMessage(), e.getStatus());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return u;
    }

    @Override
    public void insertUser(User user) throws HandlerException {
        try {
            User u = userRepository.findByEmail(user.getEmailAddress());
            if (u != null) {
                throw new HandlerException("user_is_exist", messageDao.getMessageByName("user_is_exist"), HttpStatus.BAD_REQUEST);
            } else {
                userRepository.insetUser(user);
            }
        } catch (HandlerException e) {
            throw new HandlerException(e.getErrorCode(), e.getMessage(), e.getStatus());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public List<UserDetail> getAllUser() throws HandlerException {
        List<UserDetail> listUserDetail = new ArrayList<>();
        try {
            List<User> users = userRepository.findAllUser();
            for (User en : users) {
                UserDetail userDetail= new UserDetail();
                userDetail.setId(en.getId());
                userDetail.setEmail(en.getEmailAddress());
                userDetail.setPhoneNumber(en.getPhoneNumber());
                userDetail.setUserName(en.getName());
                listUserDetail.add(userDetail);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return listUserDetail;
    }

}
