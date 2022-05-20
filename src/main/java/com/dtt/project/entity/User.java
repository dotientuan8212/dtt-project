/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.entity;

import com.dtt.project.model.AuthenticationProvider;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TuanDT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private Long id;

    private Boolean enabled;

    private String emailAddress;

    private String name;
    
    private String phoneNumber;

    private String password;

    private String createdBy;
    
    private Date createdDate;
    
    private String lastModifiedBy;
    
    private Date lastModifiedDate;
    
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authProvider;
}
