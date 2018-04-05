package com.example.demo.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class
})
public class ExternalTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalTestApplication.class, args);
    }
}
