/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Managers;

import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.lpro.control.PasswordManagement;
import org.lpro.entity.Utilisateur;

/**
 *
 * @author Nicolas
 */
@Stateless
@Transactional
public class UtilisateurManager {
    
    @PersistenceContext
    EntityManager em;

    public Utilisateur findUtilisateur(String mail) {
        Utilisateur u = null;
       
        TypedQuery<Utilisateur> q=this.em.createQuery("SELECT u FROM Utilisateur u where u.mail = :mail",Utilisateur.class);
        q.setParameter("mail",mail);
      
        try{
            u=q.getSingleResult();
        }catch(NoResultException e){
            u=null;
        }
        
        return u;
    }
    
    public Utilisateur findUtilisateurById(String id){
         Utilisateur u = null;
       
        TypedQuery<Utilisateur> q=this.em.createQuery("SELECT u FROM Utilisateur u where u.id = :id",Utilisateur.class);
        q.setParameter("id",id);
      
        try{
            u=q.getSingleResult();
        }catch(NoResultException e){
            u=null;
        }
        
        return u;
    }
    
    public Utilisateur findUtilisateurByToken(String token){
         Utilisateur u = null;
       
        TypedQuery<Utilisateur> q=this.em.createQuery("SELECT u FROM Utilisateur u where u.token = :token",Utilisateur.class);
        q.setParameter("token",token);
      
        try{
            u=q.getSingleResult();
        }catch(NoResultException e){
            u=null;
        }
        
        return u;
    }

    public Utilisateur signup(String username, String mail, String password) {
        Utilisateur u = new Utilisateur(UUID.randomUUID().toString(), username, mail, PasswordManagement.digestPassword(password));
        return this.em.merge(u);
       
    }
    
}
