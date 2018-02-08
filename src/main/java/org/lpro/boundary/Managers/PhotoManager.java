/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.lpro.entity.Photo;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */
public class PhotoManager {
    
    @PersistenceContext
    EntityManager em;
    
    
    public Photo save(Photo p){
        p.setId(UUID.randomUUID().toString());
        return this.em.merge(p);
    }
    /** 
     public Photo findById(String id) {
        return this.em.find(Photo.class, id);
    }

    public Set<Photo> findAll() {
        Query query = em.createQuery("SELECT p FROM Photo p");
        Set<Photo> liste = new HashSet<>(query.getResultList());
        return liste;
    }

    public Photo ajoutePhoto(String serieId, Photo photo) {
        Photo p;
        TypedQuery<Photo> query = em.createQuery("SELECT p FROM Photo p WHERE p.id = :n", Photo.class);
        query.setParameter("n", photo.getId());
        try {
            p = query.getSingleResult();
        } catch (NoResultException e) {
            p = new Photo(photo.getId(), photo.getDescr(), photo.getLatitude(), photo.getLongitude(), photo.getUrl(), photo.getSerie());
            p.setId(UUID.randomUUID().toString());
            this.em.persist(p);
        }
        Serie po = this.em.find(Serie.class, serieId);
        po.getListePhotos().add(p);
        this.em.persist(po);
        return p;
    }*/
    
}
