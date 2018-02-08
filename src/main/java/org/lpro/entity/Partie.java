/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NamedQuery(name="Partie.findAll",query="SELECT pt FROM Partie pt")
public class Partie implements Serializable {
    
    @Id
    private String id;
    private String token;
    private int nb_photos;
    private boolean status;
    private int score;
    private String idJoueur;
    private String idSerie;
    
    
    public Partie(){
        
    }
    
    public Partie(String i, String t, int nb, boolean s, int sc, String j, String ids){
        this.id = i;
        this.token = t;
        this.nb_photos = nb;
        this.status = s;
        this.score = sc;
        this.idJoueur = j;
        this.idSerie = ids;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String t) {
        this.token = t;
    }
    
    public int getNbPhotos() {
        return nb_photos;
    }

    public void setNbPhotos(int nb) {
        this.nb_photos = nb;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean s) {
        this.status = s;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        this.score = s;
    }
    
    public String getJoueur() {
        return idJoueur;
    }

    public void setJoueur(String j) {
        this.idJoueur = j;
    }
    
    public String getSerie() {
        return idSerie;
    }

    public void setSerie(String s) {
        this.idSerie = s;
    }
    
}
