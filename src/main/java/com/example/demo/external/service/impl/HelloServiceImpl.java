package com.example.demo.external.service.impl;

import com.example.demo.external.ExternalException;
import com.example.demo.external.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting() {
        return "Hello World!";
    }

    @Override
    public String goodbye() {
        int rand = new Random().nextInt(10);
        if (rand % 2 == 0) {
            throw new ExternalException("no goodbye");
        }
        return "Hello GoodBye!";
    }

}
