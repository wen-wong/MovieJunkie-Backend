# MovieJunkie Backend

:wave: Welcome to the Movie Junkie Backend repository where we provide a platform for users to find, review and share their thoughts in the world of cinema!

## :mag: Getting Started
Please have the following software:
- Java 17
- Maven 3.\*.\*
- Postgres 15

**NOTE: If you do not have maven installed locally, you may use `./mvwn` instead of `mvn`**

1. Clone the repository
```
git clone https://github.com/wen-wong/MovieJunkie-Backend.git
```
2. Create a folder under `src/main/`
```
mkdir ./src/main/resources
```
3. Create a file called `application.properties` and paste the following code
```
cd ./src/main/resources
touch application.properties
```
***Inside of application.properties***
```
server.port=${PORT:1234}                                                # Port Number to connect the local backend
spring.datasource.url=jdbc:postgresql://localhost:<DB-PORT>/<DB-NAME>   # URL to the database, e.g., localhost:8080/movie-junkie
spring.datasource.username=<DB-USERNAME>                                # Username assigned to the database
spring.datasource.password=<DB-PASSWORD>                                # Password assigned to the database
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect    # Driver to allows the project to understand Postgres
```
**NOTE: Fill in all `<>` in the code above**
4. Build the maven project and install the project files
```
./mvnw clean install
```
5. Format all java files
```
mvn spring-javaformat:apply
```
6. Run all acceptance test
```
mvn test
```
7. Run the project locally
```
mvn spring-boot:run
```

## :books: Tech Stack

- **Java (Version 17)**: Programming Language where the repository is written in
- **Maven (Version 4.0.0)**: Automated build tool to manage this project
- **Spring Boot (Version 3.0.2)**: Application Framework to build the project
- **Hibernate**: Object-Relational Mapping tool to map the domain model of the relational database
- **Postgres (Version 14)**: Relational Database to persist all data of the project
- **Google Cloud Provider (Version 3.3.0)**: Host the project's database
- **Cucumber (Version 7.1.1)**: Software to perform acceptance testing
- **JUnit**: Software to execute the Gherkin scenarios

## :bookmark_tabs: Project Structure
Below is a summarized version of the repository's project structure:
1. `controller`: all API endpoints will reside in this folder
2. `dto`: all data transfer object files to convert a JSON to a Java Object
3. `model`: all Java Classes used throughout the project
4. `repository`: all CRUD methods to persist with the relational database
5. `service`: all business logic will reside in this folder
6. `acceptance`: acceptance test suite resides in this folder
7. `stepDefinitions`: all step definitions
8. `features`: all gherkin scenarios reside in this folder

```
*
|_ src
    |_ main
        |_ java
            |_ ca.mcgill.ecse428.moviejunkie
                |_ controller                           # 1
                |_ dto                                  # 2
                |_ model                                # 3
                |_ repository                           # 4
                |_ service                              # 5
                |_ MovieJunkieApplication.java
        |_ resources
            |_ application.properties
    |_ test
        |_ java
            |_ ca.mcgill.ecse428.moviejunkie
                |_ acceptance                           # 6
                    |_ stepDefinitions                  # 7
                |_ MovieJunkieApplicationTests.java
        |_ resources
            |_ features                                 # 8
|_ pom.xml
```

## :safety_pin: Plugins

- **Java Spring Format**: Java linter to ensure that the project maintains a consistent style
