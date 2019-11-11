# Design 

This file details the architecture of the project. The technologies used are mentioned in the [READ.md](READ). 

## Global architecture

Provided that we start from SRC/MAIN, here is the global architecture of projetTransports : 

### I - java

1. [controller](src/main/java/controlleur) – Mapper methods, conversion from concrete objects to DTOs and vice versa ; and endpoints

2. [dao](src/main/java/dao) – Data Access Objects

3. [dto](src/main/java/dto) – Data Transfer Objects

4. [m2info](src/main/java/m2info) – Contains : 

* an executable that populates the database (JpaTest.java) ; 
* a class that emulates the behaviour of the EntityManager, provided [here](https://github.com/barais/tpjpa2019sir/blob/master/src/main/java/jpa/EntityManagerHelper.java)(EntityManagerHelper.java)

5. [model](src/main/java/model) – Concrete classes (Citizen, MeansOfTransportation, Journey). A diagram of this model is available [here](M2INFO_20192020_Projet-TAAGLI_SECHES_Transport.jpg).

6. [rest](src/main/java/rest) – Rest Application

### II - resources

Contains the configurations of:
* the logging mecanism ([log4J.properties](src/main/resources/log4j.properties)) ;
* the database’s persistance units ([persistence.xml](src/main/resources/META-INF/persistence.xml)) ;
* the servlet([web.xml](src/main/resources/WEB-INF/web.xml)).
