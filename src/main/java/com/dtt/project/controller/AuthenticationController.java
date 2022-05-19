/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.controller;

import com.dtt.project.entity.User;
import com.dtt.project.exceptions.ErrorMessage;
import com.dtt.project.exceptions.HandlerException;
import com.dtt.project.request.LoginRequest;
import com.dtt.project.response.LoginResponse;
import com.dtt.project.service.BindingResultService;
import com.dtt.project.service.TokenService;
import com.dtt.project.service.message.MessageService;
import com.dtt.project.service.user.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TuanDT
 */
@RestController
@RequestMapping("project/v1/authentication")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userDao;

    @Autowired
    private MessageService messageDao;

    @Autowired
    BindingResultService bindingResultProvider;

    @Autowired
    TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<Object> login(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid LoginRequest payload, BindingResult result) {
        ErrorMessage errorMessage = new ErrorMessage();
        HttpStatus httpStatus = HttpStatus.OK;
        String code = null;
        String message = null;
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (result.hasErrors()) {
                httpStatus = HttpStatus.BAD_REQUEST;
                String errorCode = bindingResultProvider.getErrorMessage(result);
                throw new HandlerException(errorCode, messageDao.getMessageByName(errorCode), httpStatus);
            }
            User u = userDao.getUserByEmail(payload.getEmail());
            if (!BCrypt.checkpw(payload.getPassword(), u.getPassword())) {
                throw new HandlerException("wrong_password", messageDao.getMessageByName("wrong_password"), HttpStatus.UNAUTHORIZED);
            }
            String token = tokenService.generateToken(u);
            loginResponse.setToken(token);
            loginResponse.setTokenType("Beaer");
        } catch (HandlerException e) {
            code = e.getErrorCode();
            message = e.getMessage();
            httpStatus = e.getStatus();
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            message = "Lỗi hệ thống";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            code = "internal_server_error";
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (httpStatus.isError()) {
                errorMessage.setCodeMessage(code);
                errorMessage.setMessage(message);
                return new ResponseEntity<>(errorMessage, httpStatus);
            }
        }
        return ResponseEntity.ok(loginResponse);
    }
}
