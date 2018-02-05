# GeoQuizz-api

## Authors
Nicolas Jacquemin-Thibaud Grepin-Lucas Marquan-Quentin Parmentier

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
