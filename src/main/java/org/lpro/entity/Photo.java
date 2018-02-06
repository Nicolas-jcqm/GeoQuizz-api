/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
import javax.persistence.Entity;
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
@NamedQuery(name="Photo.findAll",query="SELECT p FROM Photo p")
public class Photo implements Serializable {
    
    @Id
    private String id;
    private String descr;
    private double position_latitude;
    private double position_longitude;
    private String url;
    private String idSerie;
    
    public Photo(){
        
    }
    
    public Photo(String i, String d, double lat, double lon, String u, String s){
        this.id = i;
        this.descr = d;
        this.position_latitude = lat;
        this.position_longitude = lon;
        this.url = u;
        this.idSerie = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getDescr() {
        return descr;
    }

    public void setDescr(String d) {
        this.descr = d;
    }
    
    public double getLatitude() {
        return position_latitude;
    }

    public void setLatitude(double l) {
        this.position_latitude = l;
    }
    
    public double getLongitude() {
        return position_longitude;
    }

    public void setLongitude(double l) {
        this.position_longitude = l;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String u) {
        this.url = u;
    }
    
    public String getSerie() {
        return idSerie;
    }

    public void setSerie(String i) {
        this.idSerie = i;
    }
}
