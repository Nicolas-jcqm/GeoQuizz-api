/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import static jdk.nashorn.internal.runtime.JSType.toInteger;
import org.lpro.boundary.Managers.PartieManager;
import org.lpro.boundary.Managers.SerieManager;
import org.lpro.entity.Partie;
import org.lpro.entity.Photo;
import org.lpro.entity.Serie;

/**
 *
 * @author Nicolas
 */

@Stateless
@Path("parties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value="Partie")
public class PartieRessource {
    
    @Inject 
    PartieManager pm;
    
    @Inject 
    SerieManager sm;
    
    @GET
    public Response getParties() {
        GenericEntity<List<Partie>> liste = new GenericEntity<List<Partie>>(this.pm.findAll()) { };
        return Response.ok(liste).build();
    }
    
   
    
        @POST
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Creer"),
        @ApiResponse(code = 417, message = "Expectation failed"),
        @ApiResponse(code = 500, message = "Erreur Serveur")})
    public Response createPartie(JsonObject score, @Context UriInfo uriInfo) throws java.text.ParseException {
        JsonObjectBuilder error = Json.createObjectBuilder();
        String errorList = "";
        boolean flag = false;
        if(!score.containsKey("idSerie")  || score.getString("idSerie").isEmpty() || score.isNull("idSerie")){
            errorList += "pas d'id série";
            flag = true;
        }
        Serie s = this.sm.findById(score.getString("idSerie"));
        if(s == null){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "série inexistanse")
                            .build()
            ).build();
        }
        if(!score.containsKey("idJoueur")  || score.getString("idJouer").isEmpty()|| score.isNull("idJoueur")){
            errorList += "pas d'id joueur ";
            flag = true;
        }
        
        if(!score.containsKey("nbPhotos") || score.getString("nbPhotos").isEmpty()|| score.isNull("nbPhotos") ){
            errorList += "pas de nbPhotos ";
            flag = true;
        }
        if(flag){                        
            error.add("errors", errorList);
            JsonObject json = error.build();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(json).build();
        }
        
        Partie partie = new Partie(score.getString("id"),score.getString("token"),score.getInt("nbPhotos"),score.getBoolean("status"),score.getInt("score"),score.getString("joueur"),score.getString("idSerie"));
        partie = this.pm.create(partie);
        List<Photo> photos = this.sm.randomPhotos(s,toInteger(score.getInt("nbPhotos")));
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+partie.getId()).build();
        return Response.created(uri).entity(this.builJson(s, partie, photos)).build();
       
    }
    
    @PUT
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Creer"),
        @ApiResponse(code = 417, message = "Expectation failed"),
        @ApiResponse(code = 500, message = "Erreur Serveur")})
    public Response scorePartie(JsonObject score, @QueryParam("token") String token, @HeaderParam("X-geoquizz-token") String header, @Context UriInfo uriInfo) throws java.text.ParseException {
        
        if (header == null && token == null ) {
            return Response.status(Response.Status.FORBIDDEN).entity(
                    Json.createObjectBuilder()
                            .add("error", "pas de token")
                            .build()
            ).build();
        }

        String pt = (token != null) ? token : header;

        Partie p = this.pm.findByToken(pt);

        if(p == null){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "pas de partie avec ce token")
                            .build()
            ).build();
        }
        
        if(!score.containsKey("score") || score.getString("score").isEmpty()|| score.isNull("score") ){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(
                    Json.createObjectBuilder()
                            .add("error", "pas de score")
                            .build()
            ).build();
        }else if(!Pattern.matches("^\\d+$", score.getString("score"))){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(
                    Json.createObjectBuilder()
                            .add("error", "score non respecter")
                            .build()
            ).build();
        }
        
        p = this.pm.updateScore(p, Integer.parseInt(score.getString("score")));
        
        JsonObject succes = Json.createObjectBuilder()
                .add("success", "Score sauvegarder")
                .build();
        
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+p.getId()).build();
        return Response.created(uri).entity(succes).build();
       
    }
    
    private JsonObject builJson(Serie s, Partie g, List<Photo> p){

        JsonArrayBuilder photos = Json.createArrayBuilder();
            JsonObject position = Json.createObjectBuilder()
                .add("latitude", s.getLatitude())
                .add("longitude", s.getLongitude())
                .build();

        JsonObject serie = Json.createObjectBuilder()
                .add("id", s.getId())
                .add("ville", s.getVille())
                .add("nom", s.getNom())
                .add("position", position)
                .add("photos", photos)
                .build();
        p.forEach((ph ->{
            JsonObject pos = Json.createObjectBuilder()
                    .add("latitude", ph.getLatitude())
                    .add("longitude", ph.getLongitude())
                    .build();
            JsonObject photo = Json.createObjectBuilder()
                    .add("url", ph.getUrl())
                    .add("position", pos)
                    .build();

            photos.add(photo);
        }));
        return Json.createObjectBuilder()
                .add("type", "ressource")
                .add("token", g.getToken())
                .add("serie", serie)
                .build();
    }
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
/**
{"data":
    {"serie":"1","photo":"10","joueur":"1"}}
    * */