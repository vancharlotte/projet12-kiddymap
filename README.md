# Projet12
Aidez la communauté en tant que développeur d'application Java

### Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`jdbc:postgresql://kiddymap-db:5432/db_kiddymap`)
*   un serveur de configuration _config-server_
*   un discovery server _eureka-server_
*   une api gateway _api-gateway_
*   un microservice qui gère les utilisateurs _microservice-profil_
*   un microservice qui gère les données de la carte _microservice-location_


### Lancement

```
    docker-compose up
```

### Arrêt

```
    docker-compose stop
```

### Remise à zero

```
    docker-compose stop
    docker-compose rm -v
    docker-compose up
```

### Description des API : Swagger

microservice-profil :
```
swagger.json: http://localhost:9002/v2/api-docs
swagger-ui: http://localhost:9002/swagger-ui/
```

microservice-location :
```
swagger.json: http://localhost:9003/v2/api-docs
swagger-ui: http://localhost:9003/swagger-ui/
```
