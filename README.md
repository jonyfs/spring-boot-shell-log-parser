# Spring Shell Log Access Parser Sample

## What is this?


This example shows how to use the Spring Shell tool to parse an access log file. It is also possible to perform queries with totalization of accesses by ip, with a threshold and date period.


## Used Tools

*   [SPRING INITIALIZR](https://start.spring.io)

*   [Spring Boot](http://projects.spring.io/spring-boot)

*   [Spring Shell](https://projects.spring.io/spring-shell)

*   [Spring Data JPA](https://projects.spring.io/spring-data-jpa)

*   [Maven](https://maven.apache.org)



## Starting...

mvn spring-boot:run

After start, type help to list all commands.

### Commands

        clean: clear all data rows on database
        count: count data rows on database
        load: Load data file. Example: load path-to-log-file
        parse: Parse the log file and search data. Example: parse path-to-log-file start-date(in yyyy-MM-dd.HH.mm.ss format) duration(hourly or daily) threshold(greather than 0)
        query: Search data from database. Example: query start-date(in yyyy-MM-dd.HH.mm.ss format) duration(hourly or daily) threshold(greather than 0)
