package com.example.demo.external.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloServiceImplIntegrationTests {

    @Autowired
    private HelloServiceImpl sut;

    @Test
    public void greeting() {
        String actual = sut.greeting();
        assertThat(actual).isEqualTo("Hello World!");
    }

}
