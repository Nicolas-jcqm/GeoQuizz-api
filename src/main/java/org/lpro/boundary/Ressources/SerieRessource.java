/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Query;
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
import org.lpro.boundary.Managers.SerieManager;
import org.lpro.entity.Partie;
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
    
    @Inject 
    PartieManager pm;
    
    @GET
    public Response getSeries() {
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
    
    @POST
    public Response newSerie(
            @PathParam("id") String id, @Context UriInfo uriInfo,
            @DefaultValue("Nancy") @QueryParam("ville") String ville,
            @DefaultValue("48.6843900") @QueryParam("lat") double latitude,
            @DefaultValue("6.1849600") @QueryParam("lon") double longitude,
            @DefaultValue("10") @QueryParam("zoom") double zoom,
            @DefaultValue("500") @QueryParam("diffDist") int diffDist){
        
        Serie s = new Serie(UUID.randomUUID().toString(), ville, latitude, longitude, zoom, diffDist);
        Serie newSerie = this.sm.save(s);
        
        
        JsonObject succes = Json.createObjectBuilder()
                .add("success", "La série a été crée")
                .build();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+newSerie.getId()).build();
        return Response.created(uri).entity(succes).build();
    }
    
   
    
}
