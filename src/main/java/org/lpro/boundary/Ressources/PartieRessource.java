/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;

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
    
    @GET
    @Path("{idSerie}")
    public Response newPartie(
        @PathParam("idSerie") String idSerie, @Context UriInfo uriInfo,
        @DefaultValue("10") @QueryParam("photo") int photo,
        @DefaultValue("JoueurInconnu") @QueryParam("joueur") String joueur){
            Partie tok = pm.createPartie(idSerie, photo, joueur);
            
         JsonObject ob = Json.createObjectBuilder()
                 .add("id", tok.getId())
                 .add("token", tok.getToken())
                 .add("nbPhotos", tok.getNbPhotos())
                 .add("status", tok.getStatus())
                 .add("score", tok.getScore())
                 .add("joueur", tok.getJoueur())
                 .add("serie", tok.getSerie()).build();
         return Response.ok(ob).build();
    }
    
    /**
    @POST
    public Response newPartieFalse(JsonObject jso, @Context UriInfo uriInfo){
            String serie = jso.getJsonObject("data").getString("serie");
            int nbPhotos = Integer.parseInt(jso.getJsonObject("data").getString("photo"));
            String idJoueur = jso.getJsonObject("data").getString("joueur");
            pm.createPartie(serie, nbPhotos, idJoueur);
            return Response.ok().build();
    } */
}
/**
{"data":
    {"serie":"1","photo":"10","joueur":"1"}}
    * */