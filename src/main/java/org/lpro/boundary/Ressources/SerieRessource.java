/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import io.swagger.annotations.Api;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
import org.lpro.boundary.Managers.PhotoManager;
import org.lpro.boundary.Managers.SerieManager;
import org.lpro.entity.Partie;
import org.lpro.entity.Photo;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */

@Stateless
@Path("series")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value="Serie")
public class SerieRessource {
    
    @Inject 
    SerieManager sm;
    
    @Inject 
    PartieManager pm;
    
    @Inject 
    PhotoManager phm;
    
    @GET
    public Response getSeries() {
        List<Serie> liste =this.sm.findAll();
        return Response.ok(Response.Status.OK).entity(toJsonSerie(liste)).build();
    }
    private JsonObject toJsonSerie(List<Serie>s){
        JsonArrayBuilder json=Json.createArrayBuilder();
        s.forEach((ser)->{
            JsonObject serie=Json.createObjectBuilder()
                    .add("id",ser.getId())
                    .add("ville",ser.getVille())
                    .add("nom",ser.getNom())
                    .add("latitude",ser.getLatitude())
                    .add("longitude",ser.getLongitude())
                    .add("distance",ser.getDistance())
                    .add("zoom",ser.getZoom())
                    .build();
            json.add(serie);
        });
        return Json.createObjectBuilder().add("series",json.build()).build();
    }
    @GET
    @Path("{id}/parties")
    public Response getParties(@PathParam("id") String id,@Context UriInfo uriInfo) {
            Serie serie=this.sm.findById(id);
            if(serie==null){
                return Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("error","mauvaise idSerie")).build();
            }
             List<Partie> partie=this.pm.findBySerieId(serie);
             return Response.status(Response.Status.OK).entity(toJsonPartie(serie,partie)).build();
    }       
   private JsonObject toJsonPartie(Serie s, List<Partie> p){
        JsonArrayBuilder parties = Json.createArrayBuilder();
        p.forEach((partie)->{
            JsonObject gam = Json.createObjectBuilder()
                    .add("id", partie.getId())
                    .add("idJoueur", partie.getJoueur())
                    .add("score", partie.getScore())
                    .add("nbPhotos", this.pm.findById(partie.getId()).getNbPhotos())
                    .build();

            parties.add(gam);
        });
        
        return Json.createObjectBuilder()
                .add("type", "collection")
                .add("parties", parties)
                .build();
    }
    
   
    @GET
    @Path("{id}")
    public Response getOneSerie(
        @PathParam("id") String id, @Context UriInfo uriInfo ){
        Serie s=sm.findById(id);
         return Response.ok(serieToJson(s)).build();
    }   
    private JsonObject serieToJson(Serie s){
         return Json.createObjectBuilder()
                .add("nom",s.getNom())
                .add("id",s.getId())
                 .add("latitude",s.getLatitude())
                 .add("longitude",s.getLongitude())
                 .add("distance",s.getDistance())
                 .add("ville",s.getVille())
                 .add("zoom",s.getZoom())
                 .build();
    }
    
    @GET
    @Path("{idSerie}/parties")
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
    
    @GET
    @Path("{idSerie}/photos")
    public Response getPhotosPartie (
        @PathParam("idSerie") String idSerie, @Context UriInfo uriInfo,
        @DefaultValue("10") @QueryParam("photo") int nbPhotos){
            Serie s = null;
            List<Photo> listePhotos = sm.randomPhotos(s,nbPhotos);
            JsonArrayBuilder jab = Json.createArrayBuilder();
            listePhotos.forEach((e) -> {
                jab.add(toJson(e));
                        });
            return Response.ok(jab.build()).build();
    }
    
    /**
@POST
    @Path("/{postId}/tags")
    public Response addTag(@PathParam("postId") String postId, Tag tag) {
        System.out.println("tag: " + tag.getNom());
        Tag t = this.tagResource.ajouteTag(postId, tag);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/")
                .path(t.getId())
                .build();
        return Response.created(uri).entity(buildJsonForTag(t)).build();
    }

     */
    
    
    
    private JsonObject toJson(Photo e){
        JsonObject json= Json.createObjectBuilder()
                .add("descr",e.getDescr())
                .add("position_latitude",e.getLatitude())
                .add("position_longitude",e.getLongitude())
                .add("url",e.getUrl())
                .build();
        return json;
    }
    /**
    @POST
    public Response newSerie(
            @PathParam("id") String id, @Context UriInfo uriInfo,
            @DefaultValue("Nancy") @QueryParam("ville") String ville,
            @DefaultValue("48.6843900") @QueryParam("lat") double latitude,
            @DefaultValue("6.1849600") @QueryParam("lon") double longitude,
            @DefaultValue("10") @QueryParam("zoom") double zoom,
            @DefaultValue("500") @QueryParam("diffDist") int diffDist,
            @QueryParam("nom")String nom){
        
        Serie s = new Serie(nom,UUID.randomUUID().toString(), ville, latitude, longitude, zoom, diffDist);
       Serie newSerie = this.sm.save(s,);
        
        JsonObject succes = Json.createObjectBuilder()
                .add("success", "La série a été crée")
                .build();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+newSerie.getId()).build();
        return Response.created(uri).entity(succes).build();
    }
    * */
   
}
