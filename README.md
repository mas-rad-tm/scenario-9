

# Scénario 9
Configuration centralisée des services

Ce scénario se base sur un services applicatifs: 
* **personnes-services**: le service de gestion des personnes
* **rentes-services**: le service de gestion des personnes

## Variante n° 1
### Prérequis
* Maven version 3.5.0
* JDK version 1.8
* base de données IBM DB2 (*par défaut, utilisation d'une base de données H2 embarquée*)

#### Service personnes
* Récupération du repository
* Se rendre dans le dossier `/personnes-services`
* lancer la commande `mvn clean install`
* Se rendre dans le dossier `/personnes-service-application`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:9010/personnes-service`. 

##### Paramétrages
###### Parmétrage du port
Le port par déaut de l'application est le **9010**. Il est possible de paramétrer un autre port d'écoute pour 
l'application. Il suffit pour cela d'ajouter la varaiable **PORT** à la commande de démarrage de l'application:
> **mvn -DPORT=1234 spring-boot:run** (*remplacez 1234 par le numéro de port désiré*)


###### Utilisation d'une base de données physique
Par défaut c'est une base de donnée `H2` embarquée qui est utilisée. Il est possible d'utiliser une base de données physique. 
Un profil spring `db2` est paramétré pour l'utilisation d'une base de données IBM DB2. 

* Ouvrir le fichier `/personnes-service/personnes-service-application/src/main/resources/config/application-db2.yml`
* Adapter les paramètres en fonction de la base de données voulues
* Ouvrir le fichier `/personnes-service/personnes-service-application/src/main/resources/config/application.yml`
* Modifier `h2`  par `db2` pour la clé `spring.profiles.active`
* Démarrer l'application 

###### Paramétrage des logs applicatifs
Les logs applicatifs sont générés dans un répertoire déinis. Suivant le système d'exploitation, il y aura lieu de configurer le chemin d'écriture des fichiers de logs:

* Ouvrir le fichier `/personnes-service/personnes-service-application/src/main/resources/logback-spring.xml`
* Modifier la valeur de la propriété `logging.path`
* Redémarrer l'application

#### Eureka discovery
* Récupération du repository
* Se rendre dans le dossier `/eureka-discovery-server`
* lancer la commande `mvn clean install`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:8300`. 

#### ZuulApiGateway
* Récupération du repository
* Se rendre dans le dossier `/zuul-api-gateway`
* lancer la commande `mvn clean install`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:8020/gateway`. 

#### ConfigServer
* Récupération du repository
* Se rendre dans le dossier `/config-server`
* lancer la commande `mvn clean install`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:8100/config`. 

##### Paramétrages
###### Parmétrage du port
Le port par déaut de l'application est le **8010**. Il est possible de paramétrer un autre port d'écoute pour 
l'application. Il suffit pour cela d'ajouter la varaiable **PORT** à la commande de démarrage de l'application:
> **mvn -DPORT=1234 spring-boot:run** (*remplacez 1234 par le numéro de port désiré*)

### Fonctionnalités et tests
L'application est une application Spring en mode `embeded`. Au lieu de déployer une archive web (.war) dans un container applicatif JEE, c'est l'application qui va embrquer un container applicatif, dans notre cas il s'agit de Apache Tomcat.

#### API Rest
L'application embarque une documentation des API disponible à cette url:
> {url application}/swagger-ui.html

Cette documentation est basé sur l'outil `Swagger`, fournissant une documentation, mais également une interface permettant de tester les différentes API.
