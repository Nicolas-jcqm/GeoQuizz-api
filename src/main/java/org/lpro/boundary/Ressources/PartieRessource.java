/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import java.net.URI;
import java.security.SecureRandom;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.Managers.PartieManager;
import org.lpro.entity.Partie;

/**
 *
 * @author Nicolas
 */

@Stateless
@Path("parties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PartieRessource {
    
    @Inject 
    PartieManager pm;
    
    @GET
    public Response getParties() {
        GenericEntity<List<Partie>> liste = new GenericEntity<List<Partie>>(this.pm.findAll()) { };
        return Response.ok(liste).build();
    }
    
    @POST
    @Path("{idSerie}")
    public Response newPartie(
            @PathParam("idSerie") String idSerie, @Context UriInfo uriInfo,
            @DefaultValue("10") @QueryParam("diffImage") int diffImage){
        /** 
        Partie p = new Partie(id, token, diffImage, false, 0, idJoueur, idSerie);
        Partie newOne = this.pm.save(p);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build(); */
        Partie p = new Partie();
        Partie newOne = this.pm.save(p);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+p.getId()).build();
        return Response.created(uri).build();
        
    }
    
    
    
    
}
