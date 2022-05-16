/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TuanDT
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessageByName(String codeMessage) {
        String message = null;
        Map<String, String> messageMap = new ConcurrentHashMap<>();
        messageMap.put("name_required", "name required");
        messageMap.put("email_required", "email required");
        messageMap.put("password_required", "password required");
        messageMap.put("phone_required", "phone required");
        message = messageMap.get(codeMessage);
        return message;
    }

}
