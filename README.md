## Structure des modules

Il existe trois [modules maven](https://maven.apache.org/guides/mini/guide-multiple-modules.html) dans le projet :

* `external-service-api` : ce projet offre un API codé avec Spring Boot.
* `game-api` : Un jeu à base de tours
* `application` : permet de démarrer les 2 API simultanément.

## Les technologies utilisées

* [Jetty](https://www.eclipse.org/jetty/)
* [Jersey](https://jersey.github.io/)
* [Jackson](https://www.baeldung.com/jackson)

## Intégration Docker

Un Dockerfile est fournis pour rouler le code sur les mêmes images docker qui ont été utilisées lors du développement.

Pour ce faire:

```bash
docker build -t application-glo4002 .
docker run -p 8080:8080 -p 8181:8181 application-glo4002
```

## Démarrer le projet

Les deux serveurs (GamerServer ou VetServer) peuvent être démarrés soit individuellement ou simultanément. 

```bash
mvn clean install
mvn exec:java -pl application
```
