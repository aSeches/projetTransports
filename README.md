# projetTransports

This project has learning purposes in software architecture, as well as front-end and back-end development. 
It aims at simulating a choice between several means of public transportation to commute.

## Getting started 

Clone the project into your local repository. 

```
git clone https://github.com/aSeches/projetTransports
```

Open the project in your favourite IDE (preferably IntellJ or Eclipse).

### Prerequisites

* JDK 1.8
* Maven 
```
sudo apt install maven
```
Every other dependency (such as MapStruct or Undertow) should get imported automatically by your IDE. 

## Deployment 

Open a terminal.
Go in the local repository of the project.

```
cd Path/To/The/Local/Repo
```

HSQLDB is a lightweight relational database system client. Start the server : 

```
sh ./run-hsqldb-server.sh
```

Then start the client : 

```
java -jar hsqldb-2.3.4.jar
```

Once the HSQLDB client is running, you should modify the "Type" option to "HSQL Database Engine Server". 


Now that your local database is set, go back to your IDE and : 
* populate the base by running [JPATest.java](src/main/java/m2info/JpaTest.java); 
* run [RestServer.java](src/main/java/rest/RestServer.java) to enable the JPQL requests.

You can now request this database in HSQLDB ! 


## Built with
* [JPA/Hibernate](https://hibernate.org/) - Data persistency
* [Mapstruct](https://mapstruct.org/) - Automatic Java Beans Mapping
* [Maven](https://maven.apache.org/) - Dependency management

## Author 
* **Amaury Seches** - Student in software development - (https://github.com/aSeches)

