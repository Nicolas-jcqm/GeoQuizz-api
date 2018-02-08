/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private double position_latitude;
    @NotNull
    private double position_longitude;
    @NotNull
    private String url;
    private String idSerie;
    
    @ManyToMany(mappedBy = "listePhotos")
    private Set<Serie> listeSerie = new HashSet<Serie>();
    
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
    
    public Set<Serie> getListeSeries() {
        return listeSerie;
    }

    public void setListeSeries(Set<Serie> s) {
        this.listeSerie = s;
    }
    
}
