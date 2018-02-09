# GeoQuizz-api

## Authors
Nicolas Jacquemin-Thibaud Grepin-Lucas Marquan-Quentin Parmentier

## Routes Player : path = serverIp:port/geoquizzapi/api
 ### Parties:
			GET: /parties : retourne toutes les parties
			POST: /parties :crée une partie demande un Json contenant : « idSerie » « joueur » « nbPhotos » « id » « token » « status » « score » 
			PUT: /parties modifie le score de la partie :  -token : String
										          - Json score : « score »
			PUT: /parties/status modifie le statut d'une partie : Json contenant : "token":String "estEnCours": boolean
#### Series: 
			GET: /series : retourne toutes les series
			GET:/series/{id}/parties: retourne toutes les parties d’une série 
			GET:/series/{id} : retourne la série
			GET: /series/{id}/photos: récupère aléatoirement un nombre de photos pour la partie : param: « photo »  
			POST:/series/{id}/parties crée une partie pour la série 
