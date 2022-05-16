/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.repository;

import com.dtt.project.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TuanDT
 */
@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT * FROM USER WHERE emailAddress = #{email} LIMIT 1")
    User findByEmail(@Param("email") String email);

    @Select("SELECT * FROM USER ")
    List<User> findAllUser();

    @Insert("INSERT INTO `user` (emailAddress,`name`,phoneNumber,`password`,"
            + "createdBy,createdDate,lastModifiedBy,lastModifiedDate)"
            + "VALUES (#{emailAddress},#{name}, #{phoneNumber}, #{password},"
            + "#{createdBy},#{createdDate},#{lastModifiedBy},#{lastModifiedDate})")
    int insetUser(User user);
}
