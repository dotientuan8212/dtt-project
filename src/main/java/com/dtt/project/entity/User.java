/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtt.project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author TuanDT
 */
@Data
public class User implements Serializable {

    @Id
    private int id;

    private Boolean enabled;

    private String emailAddress;

    private String name;
    
    private String phoneNumber;

    private String password;

    private String createdBy;
    
    private Date createdDate;
    
    private String lastModifiedBy;
    
    private Date lastModifiedDate;

}
