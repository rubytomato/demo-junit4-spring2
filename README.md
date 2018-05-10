# Spring Boot 2.0 Rest API application

環境

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
java -jar target\demo.jar
```

```text
java -jar -Dspring.profiles.active=dev target\demo.jar
```

### spring boot maven plugin

```text
mvn spring-boot:run
```

```text
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
