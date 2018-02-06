/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.Managers.SerieManager;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */

@Stateless
@Path("series")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SerieRessource {
    
    @Inject 
    SerieManager sm;
    
    @GET
    public Response getCategories() {
        GenericEntity<List<Serie>> liste = new GenericEntity<List<Serie>>(this.sm.findAll()) { };
        return Response.ok(liste).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOneSerie(
        @PathParam("id") String id, @Context UriInfo uriInfo ){
        return Optional.ofNullable(sm.findById(id))
                .map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }    
    
}
