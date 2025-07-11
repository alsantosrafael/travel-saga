## Travel Saga Orchestration Project

### Intro
Welcome! In this project I am building a travel management system employing the Saga Orchestration Architectural Pattern.
 
### Architecture
This project consists of a collection of distributed systems that communicate either through kafka messaging or HTTP Rest requests with each other. 
The main goal of this project is to improve my skills in technologies such as Java, Kotlin, Spring JPA, Docker, Docker-Compose, Kafka and Software Architecture.
The services part of this system are: **orchestrator**, **flight-booking**, **hotel-booking**, **car-booking**, **payment** and **notification**.


### Technologies
    - Kotlin (JVM 2.2.0)
    - Java 21
    - Spring Boot 3.5.3
    - Apache Kafka
    - Spring JPA + PostgreSQL (H2 for now)
    - Docker
    - Docker-Compose

### How to Run

Go to https://airportdb.io and create an account. Create an API token so you can run calls to the DB.
Once the token is created, update `airport.db.api.token=` inside `flight-booking` project with your token
Run the following command on the root folder to build the applications
```shell
./gradlew clean generateAvroJava build -x test
```

Secondly, run the docker compose command to make all containers go up
```shell
docker-compose up --build
```

To stop the app, make sure to run
```shell
docker compose down -v --remove-orphans
```