/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.lpro.entity.Partie;

/**
 *
 * @author Nicolas
 */

@Stateless
public class PartieManager {
    
    @PersistenceContext
    EntityManager em;
    
    public List<Partie> findAll() {
        Query q = this.em.createNamedQuery("Partie.findAll", Partie.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
    
    public Partie createPartie(String idSerie, int nbPhotos, String idJoueur){
        Partie p = new Partie(UUID.randomUUID().toString(), UUID.randomUUID().toString(), nbPhotos, true, 0, idJoueur, idSerie);
        this.save(p);
        return p;
    }
    
    public Partie save(Partie p) {
        return this.em.merge(p);
    }
    
}
