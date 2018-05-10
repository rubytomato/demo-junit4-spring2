# Spring Boot 2.0 Rest API application

Development environment

* Java 1.8.0
* Spring Boot 2.0.2
* Maven 3.5.2

## compile

```text
mvn clean package
```

## run

### executable jar

```text
java -jar .\target\demo.jar
```

Specify a profile

```text
java -jar -Dspring.profiles.active=dev .\target\demo.jar
```

### spring boot maven plugin

```text
mvn spring-boot:run
```

Specify a profile

```text
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
