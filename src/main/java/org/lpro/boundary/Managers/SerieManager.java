/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */

@Stateless
public class SerieManager {
    
    @PersistenceContext
    EntityManager em;

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
    
    public Serie save(Serie s) {
        return this.em.merge(s);
    }
  
}
