/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Utilisateur.findAll",query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable{
    
    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String mail;
    private String password;
    
    public Utilisateur(){
        
    }
    
    public Utilisateur(String i, String u, String m, String p){
        this.id = i;
        this.username = u;
        this.mail = m;
        this.password = p;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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
