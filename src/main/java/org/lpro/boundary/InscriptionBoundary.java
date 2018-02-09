/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.Managers.UtilisateurManager;
import org.lpro.entity.ApiUtilisateur;
import org.lpro.entity.Utilisateur;

/**
 *
 * @author Nicolas
 */
@Path("/signup")
@Api(value = "Inscription")
public class InscriptionBoundary {
    
    @Inject 
    private UtilisateurManager um;
    
    @Context
    private UriInfo uriInfo;
    
    @POST
    public Response signUp(
        JsonObject au, @Context UriInfo uriInfo){
            String username = au.getString("username");
            String mail = au.getString("mail");
            String password = au.getString("password");
          
          if(username != null && mail != null && password != null){
            
                //if(mail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'+/=?^_`{|}~-]+)|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-][a-z0-9])?.)+[a-z0-9](?:[a-z0-9-][a-z0-9])?|[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")){
                    Utilisateur u = this.um.findUtilisateur(mail);
                    if(u == null){
                         
                        u = this.um.signup(username, mail, password);
                         
                        if(u != null){
                            return Response.status(Response.Status.CREATED).entity((Json.createObjectBuilder().add("réponse","inscription réussis")).build()).build();
                        }
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((Json.createObjectBuilder().add("réponse","erreur inscription")).build()).build();
                    }
                     

                //}

           }

        return null;

    }
    
    
}
