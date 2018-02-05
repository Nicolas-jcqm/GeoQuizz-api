# GeoQuizz-api

## Authors
Nicolas Jacquemin-Thibaud Grepin-Lucas Marquan-Quentin Parmentier

##Routes Player : path = serverIp:port/GeoQuizz/ApiPlayer/

@GET
- path/series --> liste des series disponibles

@POST
- path/partie/{idserie}?params --> création de la partie

@GET
- path/partie/{id} --> récupération d'une partie en cours
