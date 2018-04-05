package com.example.demo.web.controller;

import com.example.demo.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class MemoToJsonTests {

    @Autowired
    private JacksonTester<Memo> json;

    @Test
    public void testSerialize() throws Exception {
        Memo memo = new Memo(1L, "test title", "test description", false, LocalDateTime.of(2018, 3, 20, 23, 53, 49));
        JsonContent<Memo> actual = json.write(memo);
        assertThat(actual).hasJsonPathStringValue("@.title");
        assertThat(actual).extractingJsonPathStringValue("@.title").isEqualTo("test title");
        System.out.println(actual.getJson());
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\":1, \"title\":\"test title\", \"description\":\"test description\", \"done\":false, \"updated\":\"2018-03-19T02:03:38.287\"}";
        ObjectContent<Memo> actual = this.json.parse(content);
        System.out.println(actual.getObject().toString());
    }

}
