# GeoQuizz-api

## Authors
Nicolas Jacquemin-Thibaud Grepin-Lucas Marquan-Quentin Parmentier

##Routes Player : path = serverIp:port/GeoQuizz/ApiPlayer/
 
-@GET
 - path/series --> liste des series disponibles
 
-@POST
 - path/partie/{idserie}?params --> création de la partie
 
 @GET
 - path/partie/{id} --> récupération d'une partie en cours

##Routes BackOffice : path= serverIp:port/GeoQuizz/BackOffice

//Gerer les users

@GET
 - path/user/{id}
 
@POST
 - path/user --> Permet de créer un user (admin)
 
@POST
 - path/user/login --> Permet de login un user
 
@DELETE
 - path/user/{id} --> Permet de deconnecter un user
 
 //Gerer les series
 
@POST
 - path/series --> Permet de créer un serie
