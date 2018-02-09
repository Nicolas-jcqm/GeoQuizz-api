/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@NamedQuery(name="Serie.findAll",query="SELECT s FROM Serie s")
public class Serie implements Serializable{
    
    @Id
    private String id;
    @NotNull
    private String nom;
    @NotNull
    private String ville;
    @NotNull
    private double map_latitude;
    @NotNull
    private double map_longitude;
    @NotNull
    private double map_zoom;
    @NotNull
    private int dist;
    private String idutilisateur;
    
    @ManyToMany
    private Set<Photo> listePhotos = new HashSet<Photo>();

            
    public Serie(){
        
    }
    
    public Serie(String nom,String i, String v, double lat, double lon, double z, int d,String userid){
        this.id = i;
        this.nom=nom;
        this.ville = v;
        this.map_latitude = lat;
        this.map_longitude = lon;
        this.map_zoom = z;
        this.dist = d;
        this.idutilisateur=userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getVille() {
        return ville;
    }

    public void setVille(String v) {
        this.ville = v;
    }
    
    public double getLatitude() {
        return map_latitude;
    }

    public void setLatitude(double l) {
        this.map_latitude = l;
    }
    
    public double getLongitude() {
        return map_longitude;
    }

    public void setLongitude(double l) {
        this.map_longitude = l;
    }
    
    public double getZoom() {
        return map_zoom;
    }

    public void setZoom(double z) {
        this.map_zoom = z;
    }
    
    public int getDistance() {
        return dist;
    }

    public void setDistance(int d) {
        this.dist = d;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String n){
        this.nom=n;
    }
    public Set<Photo> getListePhotos() {
        return this.listePhotos;
    }
    
    public void setListePhotos(Set<Photo>p) {
        this.listePhotos = p;
    }
    public String getUserId(){
        return this.idutilisateur;
    }
    public void setUserId(String userid){
        this.idutilisateur=userid;
    }
    
}
