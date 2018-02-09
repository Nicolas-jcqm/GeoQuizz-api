/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.lpro.entity.Photo;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */

@Stateless
public class SerieManager {
    
    @PersistenceContext
    EntityManager em;
    @Inject 
    PartieManager pm;

    public Response getAllSeries() {
        GenericEntity<List<Serie>> liste = new GenericEntity<List<Serie>>(this.findAll()) { };
        return Response.ok(liste).build();
    }
    
    public List<Serie> findAll() {
        Query q = this.em.createNamedQuery("Serie.findAll", Serie.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
    
    public Serie findById(String id) {
        return this.em.find(Serie.class, id);
    }
    
    public Serie save(Serie s, Set<Photo> p) {
        s.setId(UUID.randomUUID().toString());
        s.setListePhotos(p);
        return this.em.merge(s);
    }
    
    public Serie save(Serie s){
        s.setId(UUID.randomUUID().toString());
        return this.em.merge(s);
    }
  /*
    public List<Photo> randomPhotos(Serie s,int nbPhotos){
        List<Integer> i = new ArrayList<>();
        List<Photo> res=new ArrayList<>();
        for(int j=0;j<s.getListePhotos().size();j++){
            i.add(j);
        }
        Collections.shuffle(i);
        List<Integer> sous=i.subList(0, nbPhotos);
        for(int j:sous){
            res.add(s.getListePhotos().get(j));
        }
        return res;
    }
*/ 
    public List<Photo> randomPhotos(Serie s, int nbPhotos){
        ArrayList<Photo> photosRes = new ArrayList<>();
        Set<Photo> photos = s.getListePhotos();
        ArrayList<Photo> lol = new ArrayList<Photo>();
        lol.addAll(photos);
        Collections.shuffle(lol);
        for(int i=0; i<nbPhotos; i++){
            photosRes.add( lol.get(i));
        }
        return photosRes;
    }
}
