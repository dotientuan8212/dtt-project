/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.service.message;

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
        messageMap.put("name_required", "Tên không được bỏ trống");
        messageMap.put("email_required", "Email không để trống");
        messageMap.put("password_required", "Mật khẩu không được để trống");
        messageMap.put("phone_required", "Số điện thoại không được để trống");
        messageMap.put("success", "success");
        messageMap.put("user_is_exist", "Người dùng đã tồn tại");
        messageMap.put("user_not_found", "Không tìm thấy người dùng");
        messageMap.put("wrong_password", "Sai mật khẩu");
        messageMap.put("password_invalid", "Mật khẩu phải ít nhất 8 ký tự và chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 ký tự đặc biệt và 1 chữ số");
        messageMap.put("email_invalid", "Email không hợp lệ");
        message = messageMap.get(codeMessage);
        return message;
    }

}
