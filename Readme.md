# Configuration

Application listens to 8080 port by default.

It requires PostgresSQL DB running on http://localhost:5432 with **_postgres_** as default DB name, **_postgres_** as username and password.

Hibernate's auto DDL generation is set to **validate** and DB will be initialized by FlyWay.
It's migration scripts can be found at default location: **resources/db/migration**

After the table creations 2 **users** will be created with ids: -1,-2 and also 2 **notes** with ids:-1,-2 
Security in application is Basic Authentication:

User(id: -1) has **email:** aaramg@gmail.com and **password:** password

User(id: -2) has **email:** aaramg1@gmail.com and **password:** password

## Test Environment
Integration Tests are using H2 in-memory DB, so no external config is needed for testing.  
FlyWay will be turned off on test profile and Hibernate's Auto DDL generation will be set on **create-drop**

# API Documentation
Endpoint documentation can be found at **http://localhost:8080/swagger-ui.html**
