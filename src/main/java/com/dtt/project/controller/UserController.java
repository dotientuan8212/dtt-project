/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.controller;

import com.dtt.project.entity.User;
import com.dtt.project.exceptions.ErrorMessage;
import com.dtt.project.exceptions.HandlerException;
import com.dtt.project.service.BindingResultService;
import com.dtt.project.request.CreateUserRequest;
import com.dtt.project.request.GetUserRequest;
import com.dtt.project.response.UserDetail;
import com.dtt.project.service.message.MessageService;
import com.dtt.project.service.user.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TuanDT
 */
@RestController
@RequestMapping("project/v1")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userDao;

    @Autowired
    private MessageService messageDao;

    @Autowired
    BindingResultService bindingResultProvider;

    @GetMapping("get-user")
    public ResponseEntity<Object> getUserByEmail(HttpServletRequest request, HttpServletResponse response, @RequestBody GetUserRequest payload) {
        User ud = null;
        try {
            ud = userDao.getUserByEmail(payload.getEmail());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return ResponseEntity.ok(ud);
    }

    @GetMapping("get-all-user")
    public ResponseEntity<Object> getAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<UserDetail> users = new ArrayList<>();
        try {
            users = userDao.getAllUser();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("create-user")
    public ResponseEntity<Object> insertUser(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid CreateUserRequest payload, BindingResult result) {
        User ud = new User();
        ErrorMessage errorMessage = new ErrorMessage();
        HttpStatus httpStatus = HttpStatus.OK;
        String code = null;
        String message = null;
        try {
            if (result.hasErrors()) {
                httpStatus = HttpStatus.BAD_REQUEST;
                String errorCode = bindingResultProvider.getErrorMessage(result);
                throw new HandlerException(errorCode, messageDao.getMessageByName(errorCode), httpStatus);
            }
            String password = bCryptPasswordEncoder.encode(payload.getPassword());
            ud.setName(payload.getName());
            ud.setEmailAddress(payload.getEmail());
            ud.setPassword(password);
            ud.setPhoneNumber(payload.getPhone());
            ud.setLastModifiedBy(payload.getEmail());
            ud.setCreatedBy(payload.getEmail());
            ud.setLastModifiedDate(new Date());
            ud.setCreatedDate(new Date());
            userDao.insertUser(ud);
        } catch (HandlerException e) {
            code = e.getErrorCode();
            message = e.getMessage();
            httpStatus = e.getStatus();
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (httpStatus.isError()) {
                errorMessage.setCodeMessage(code);
                errorMessage.setMessage(message);
                return new ResponseEntity<>(errorMessage, httpStatus);
            }
        }
        errorMessage.setCodeMessage("success");
        errorMessage.setMessage(messageDao.getMessageByName("success"));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), httpStatus);
    }

}
