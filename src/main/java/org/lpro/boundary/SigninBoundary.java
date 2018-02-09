package org.lpro.boundary;

import static com.sun.javafx.fxml.expression.Expression.add;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.Managers.UtilisateurManager;
import org.lpro.control.KeyManagement;
import org.lpro.control.PasswordManagement;
import org.lpro.entity.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

@Path("/signin")
@Api(value = "Connexion")
public class SigninBoundary {
  @Inject
  private KeyManagement km;
  @Inject
  private UtilisateurManager um;
  @Context
  UriInfo uriInfo;

  @POST
  public Response signinUtilisateur(
          JsonObject u, @Context UriInfo uriInfo){
    if(u.getString("mail") == null || u.getString("password") == null ){
      return Response.status(Response.Status.UNAUTHORIZED).entity(
                    Json.createObjectBuilder()
                            .add("error", "probléme de json")
                            .build()
            ).build();
    }else{
      String mail=u.getString("mail");
      String password=u.getString("password");
      Utilisateur utilisateur=this.um.findUtilisateur(mail);
      if(utilisateur == null){
        return Response.status(Response.Status.UNAUTHORIZED).entity(
                      Json.createObjectBuilder()
                              .add("error", "un compte avec ce mail n'existe pas")
                              .build()
              ).build();
      }else{
        if(!mail.equals(utilisateur.getMail()) || !BCrypt.checkpw(password, utilisateur.getPassword())){
          return Response.status(Response.Status.UNAUTHORIZED).entity(Json.createObjectBuilder().add("error","probléme d'authentification").build()).build();
        }else{
          String token=this.signinToken(mail);
          return Response.ok(Json.createObjectBuilder().add("token",token).add("userId",utilisateur.getId()).add("success","Vous etes connecté ").build()).build();
        }
      }
    }

  }

  private String signinToken(String mail){
     Key key = km.generateKey();
     JwtBuilder jwtToken = Jwts.builder()
             .setSubject(mail)
             .setIssuer(uriInfo.getAbsolutePath().toString())
             .setIssuedAt(new Date())
             .setExpiration(Date.from(LocalDateTime.now().plusHours(2L).atZone(ZoneId.systemDefault()).toInstant()))
             .signWith(SignatureAlgorithm.HS512, key);
             
      return jwtToken.compact();
 }


}
