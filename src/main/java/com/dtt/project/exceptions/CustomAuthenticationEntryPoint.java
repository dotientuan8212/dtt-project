/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.exceptions;

import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author TuanDT
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        LOGGER.error("Request authorization denied", authException);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", "authorization_denied");
        jsonObject.addProperty("error_description", "Request authorization denied");
        res.setStatus(401);
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(jsonObject.toString());
    }
}
