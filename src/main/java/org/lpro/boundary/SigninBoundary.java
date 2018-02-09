@Path("/signin")
@Api(value="Connexion")
public class SigninBoundary {
  @Inject
  private KeyManagement km;
  @Inject
  private UtilisateurManager um;
  @Context
  UriInfo uriInfo;

  @POST
  public Response signinUtilisateur(JsonObject u){
    if(u.getString("mail") == null || u.getString("password") == null ){
      return Response.status(Response.Status.UNAUTHORIZED).entity(
                    Json.createObjectBuilder()
                            .add("error", "probléme de json")
                            .build()
            ).build();
    }else{
      String mail=u.getString("mail");
      String password=u.getString("password");
      password=PasswordManagement.digestPassword(password);
      Utilisateur utilisateur=this.um.findUser(mail);
      if(utilisateur == null){
        return Response.status(Response.Status.UNAUTHORIZED).entity(
                      Json.createObjectBuilder()
                              .add("error", "un compte avec ce mail n'existe pas")
                              .build()
              ).build();
      }else{
        if(!mail.equals(utilisateur.getMail()) || !BCrypt.checkpw(password, utilisateur.getPassword())){
          return Response.status(Respone.Status.UNAUTHORIZED).entity(Json.createObjectBuilder)().add("error","probléme d'authentification");
        }else{
          String token=this.signinToken(mail);
          return Response.ok(Json.createObjectBuilder().add("token",token).add("success","Vous etes connecté ").build()).build();
        }
      }
    }

  }





  private String signinToken(String mail){
     Key key = km.generateKey();
     String jwtToken = Jwts.builder()
             .setSubject(mail)
             .setIssuer(uriInfo.getAbsolutePath().toString())
             .setIssuedAt(new Date())
             .setExpiration(LocalDateTime.now().plusHours(2L).atZone(ZoneId.systemDefault()).toInstant());
             .signWith(SignatureAlgorithm.HS512, key)
             .compact();
      return jwtToken;
 }


}
