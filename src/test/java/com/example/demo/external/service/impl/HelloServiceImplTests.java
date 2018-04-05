package com.example.demo.external.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceImplTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private HelloServiceImpl sut;

    @Test
    public void greeting() {
        String actual = sut.greeting();
        assertThat(actual).isEqualTo("Hello World!");
    }

}
