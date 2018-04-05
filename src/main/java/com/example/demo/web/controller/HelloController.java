package com.example.demo.web.controller;

import com.example.demo.external.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "hello")
@Slf4j
public class HelloController {

    final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(path = "world", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greeting() {
        return helloService.greeting();
    }

    @GetMapping(path = "exception", produces = MediaType.TEXT_PLAIN_VALUE)
    public String exception() {
        return helloService.goodbye();
    }

}
