/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.lpro.entity.Partie;
import org.lpro.entity.Serie;

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
    public Partie findById(String id){
        return this.em.find(Partie.class,id);
    }
    public List<Partie> findBySerieId(Serie s){
        String q="SELECT p FROM Partie p WHERE p.idSerie='"+s.getId()+"'";
        Query query=this.em.createQuery(q);
        return query.getResultList();
    }
    
    public Partie findByToken(String token){
        TypedQuery<Partie> q=em.createQuery("SELECT p FROM Partie p WHERE token='"+token+"'",Partie.class);
        return q.getSingleResult();
    }
    public Partie createPartie(String idSerie, int nbPhotos, String idJoueur){
       // Partie p = new Partie(UUID.randomUUID().toString(), UUID.randomUUID().toString(), nbPhotos, true, 0, idJoueur, idSerie);
       Partie p= new Partie(UUID.randomUUID().toString(), UUID.randomUUID().toString(),nbPhotos,true,0,idJoueur,idSerie);
        this.save(p);
        return p;

    }
    public Partie create(Partie p){
        p.setId(UUID.randomUUID().toString());
        p.setToken(randomString());
        return this.em.merge(p);
    }
    private String randomString(){
       StringBuffer rand=new StringBuffer();
       for(int i=0;i<64;i++){
           int n=randomInt();
           char c="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt(n);
           rand.append(c);
       }
       return rand.toString(); 
    }
    private int randomInt(){
        int random=0;
        Random rg=new Random();
        random=rg.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length());
        if(random -1 != -1){
            return random-1;
        }else{
            return random;
        }
    }
    public Partie save(Partie p) {
        return this.em.merge(p);
    }
    
    public Partie updateScore(Partie p,int s){
        p.setScore(s);
        return this.em.merge(p);
    }
    
    public Partie updateStatus(Partie p, boolean  status){
        p.setStatus(status);
        return this.em.merge(p);
    }
    
}
