#Micro Service RabbitMQ Example

Implementation de sender et reciever en spring boot et Rabbit MQ.

####Prerequis : Java 11 et docker 
####rabbitmq-sender : 

- Contient un endpoint /client qui accepte des http put pour crée des clients voici un exemple de json accepter par ce service rest: 
```
{
   "name" : "java",
   "lastName": "duke",
   "birthDate": "2021-07-14",
   "address" : {
      "street": "123 Cafe",
      "city": "Java land",
      "province": "QC",
      "postalCode": "J1J 2D3",
      "country": "Canada"
   }
}
```
- A chaque appel du endpoint client en put l'objet passer en paramètre est poster dans une qeue RabbitMQ.

####rabbitmq-sender :

- Contient un endpoint /client qui accepter des HTTP GET qui retourne les clients enregister dans la base de données hsqldb en memoire
- Un listener sur la qeue RabbitMQ a chaque reception d'un message avec un client il est sauvegarder à l'aide du JPA dans base hsqldb

##Étapes pour lancer le projet : 
1. Builder et crée l'image docker du projet rabbitmq-reciver : 
    
    a. Ce placer dans le dossier rabbitmq-reciver
    
    b. Lancer la commande maven suivante :
   
    `mvnw clean package`
    
    c. Construire l'image docker avec ligne de commande suivante :
   
    `docker build --tag=rabbitmq-reciver:latest .`
2. Builder et crée l'image docker du projet rabbitmq-sender :
   
    a. Ce placer dans le dossier rabbitmq-sender
    
    b. Lancer la commande maven suivante :
   
    `mvnw clean package`
    
    c. Construire l'image docker avec ligne de commande suivante :
    
    `docker build --tag=rabbitmq-sender:latest .`

3. Lancer le docker compose:
   
    a. Ce placer dans le dossier racine
   
    b. lancer la commande suivante:
    `docker compose up`
4. Lancer les curl suivant : 
    
    a. crée un client :
   ```
   curl --location --request PUT 'localhost:8082/client' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "name" : "java",
      "lastName": "duke",
      "birthDate": "2021-07-14",
      "address" : {
      "street": "123 Cafe",
      "city": "Java land",
      "province": "QC",
      "postalCode": "J1J 2D3",
      "country": "Canada"
      }
      }'
   ```

    b. récupérer la liste des clients :

   `curl --location --request GET 'localhost:8081/client'`
