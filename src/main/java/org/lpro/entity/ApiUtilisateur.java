/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import io.swagger.annotations.ApiModel;

/**
 *
 * @author Nicolas
 */
@ApiModel(value = "ApiUtilisateur")
public class ApiUtilisateur {
    
    private String username;
    
    private String mail;
    
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }
    
}
