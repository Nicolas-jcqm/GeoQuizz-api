# GeoQuizz-api

## Authors
Nicolas Jacquemin-Thibaud Grepin-Lucas Marquan-Quentin Parmentier

Pour installer le serveur, il faut se placer dans le dossier DockerLPRO/WIldflyadmin et lancer la commande 

docker build -t lpro/wildfly-admin .

Dans le dossier DockerLPRO/Postgres, la commande : 

docker build -t lpro/pg11 .

Puis lancer dans DockerLPRO : docker-compose up -d 

Vous pouvez accèder aux routes de l'api grace à : -ipenhautdedocker-:8080/geoquizzapi/api/..........

Le détail des routes se trouve dans le Readme.md du Github de l'APi 

La création des tables et leur contenu se trouve dans le dossier insert à la racine de l'api 

Vous pouvez accèder à postgre par la commande docker run -it --rm --net=dockerlpro_lpronet lpro/pg11 psql -h db -U td1

Le mot de passe est td1-docker 

Puis executer les scripts tables.sql et data.sql du dossier insert du projet

docker exec -it wildflyProd bash -> Pour accèder au container Wildfly 

cp -r wildfly/standalone/deployments/Nancy /wildfly/welcome-content -> permet de copier les images où nous pouvons les lire avec le front-end 


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
