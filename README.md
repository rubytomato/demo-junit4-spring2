# Spring Boot 2.0.1 Rest API application

環境

* Java 1.8.0
* Spring Boot 2.0.1
* Maven 3.5.2

## compile

```text
mvn clean package
```

## run

### executable jar

```text
java -jar target\demo-junit4-spring2.0.0.2-SNAPSHOT.jar
```

```text
java -jar -Dspring.profiles.active=dev target\demo-junit4-spring2-0.0.2-SNAPSHOT.jar
```

### spring boot maven plugin

```text
mvn spring-boot:run
```

```text
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
