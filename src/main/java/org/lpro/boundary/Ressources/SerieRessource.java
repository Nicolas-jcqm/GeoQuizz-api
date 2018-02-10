/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.Ressources;

import io.swagger.annotations.Api;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.Query;
import javax.validation.Valid;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
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
    
    @Context
    UriInfo uriInfo;

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
            System.out.println(serie);
            if(serie!=null){
                System.out.println("ok");
              List<Partie> partie = this.pm.findBySerieId(serie);
             return Response.ok(toJsonPartie(serie,partie)).build();
            }else{
                 System.out.println("erreur");
                 return Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("error","mauvais idSerie")).build();
            }

    }
   private JsonObject toJsonPartie(Serie s, List<Partie> p){
        JsonArrayBuilder parties = Json.createArrayBuilder();
        p.forEach((partie)->{
            JsonObject gam = Json.createObjectBuilder()
                    .add("id", partie.getId())
                    .add("idJoueur", partie.getJoueur())
                    .add("score", partie.getScore())
                    .add("nbPhotos", partie.getNbPhotos())
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
    
    @GET
    @Path("admin")
    public Response getSeriesAdmin(
        @QueryParam("userid") String userId){
         List<Serie> listSeries = this.sm.findSeriesByIdUser(userId);
        return Response.ok(listToJson(listSeries,userId)).build();
    }
    
    private JsonObject listToJson(List<Serie>l, String userId){
        JsonArrayBuilder s = Json.createArrayBuilder();
        JsonObject jsonuserid =Json.createObjectBuilder().add("userid",userId).build();
        l.forEach((serie ->{
            JsonObject pos = Json.createObjectBuilder()
                .add("id",serie.getId())
                 .add("nom",serie.getNom())
                .add("latitude",serie.getLatitude())
                .add("longitude",serie.getLongitude())
                .add("distance",serie.getDistance())
                .add("ville",serie.getVille())
                .add("zoom",serie.getZoom())
                .build();
        s.add(pos);
        }));
        return Json.createObjectBuilder()
                .add("type", "ressource")
                .add("userid", jsonuserid)
                .add("seriesUser",s).build();
      
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
                .add("success", "La s�rie a �t� cr�e")
                .build();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+newSerie.getId()).build();
        return Response.created(uri).entity(succes).build();
    }
    * */

     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public Response ajouterSerie(@Valid Serie serie)
     {
         serie.setId(UUID.randomUUID().toString());
         sm.save(serie);
         URI uri = uriInfo.getBaseUriBuilder().path("series/" + serie.getId()).build();
         return Response.created(uri).build();
     }


  @POST
  @Path("{id}")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addPhotoToSerie(@PathParam("id") String id,
                                  @DefaultValue("") @QueryParam("desc") String desc,
                                  @DefaultValue("") @QueryParam("latitude") double latitude,
                                  @DefaultValue("") @QueryParam("longitude") double longitude,
                                  MultipartFormDataInput input)
  {
      Serie serie = sm.findById(id);
      if(serie == null){
        return Response.status(Response.Status.NOT_FOUND).build();
      }
      Map<String, List<InputPart>> formulaire = input.getFormDataMap();
      List<InputPart> inputParts = formulaire.get("file");
      Photo photo = new Photo();
      for (InputPart ip : inputParts)
      {
          MultivaluedMap<String, String> headers = ip.getHeaders();
          String filename = getNomImage(headers);
          try
          {
              InputStream is = ip.getBody(InputStream.class,null);
              byte[] bytes = byteArray(is);
              ecrireImage(bytes,"/opt/jboss/wildfly/standalone/tmp/"+filename);
              photo.setUrl("/opt/jboss/wildfly/standalone/tmp/"+filename);
          }
          catch (IOException ioe)
          {
              ioe.printStackTrace();
          }
      }
      photo.setId("");
      photo.setDescr(desc);
      photo.setLatitude(latitude);
      photo.setLongitude(longitude);
      photo.setSerie(serie.getId());
      phm.save(photo);
      URI uri = uriInfo.getBaseUriBuilder().path("series/" + serie.getId()).build();
      return Response.status(200).location(uri).build();
  }
  private static byte[] byteArray(InputStream is) throws IOException
  {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      try
      {
          byte[] b = new byte[4096];
          int n = 0;
          while ((n = is.read(b)) != -1)
          {
              output.write(b, 0, n);
          }
          return output.toByteArray();
      }
      finally
      {
          output.close();
      }
  }

  private void ecrireImage(byte[] contenu, String filename) throws IOException
  {
      File file = new File(filename);

      FileOutputStream fop = new FileOutputStream(file);

      fop.write(contenu);
      fop.flush();
      fop.close();
  }

  private String getNomImage(MultivaluedMap<String, String> headers)
  {
      String[] contenuHeader = headers.getFirst("Content-Disposition").split(";");

      for (String filename : contenuHeader) {
          if ((filename.trim().startsWith("filename"))) {
              String[] name = filename.split("=");
              return name[1].trim().replaceAll("\"", "");
          }
      }

      return "inconnu";
  }
}
