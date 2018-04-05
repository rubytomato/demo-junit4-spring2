package com.example.demo.web.controller;

import com.example.demo.domain.DatasourceConfig;
import com.example.demo.domain.entity.Memo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = {DatasourceConfig.class})
public class MemoControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    final private MediaType contentType = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));

    @Ignore
    @Test
    public void getOne() {
        Memo expected = new Memo(1L, "memo shopping", "memo1 description", false, LocalDateTime.of(2018, 1, 4, 12, 1, 0));

        ResponseEntity<Memo> result = testRestTemplate.getForEntity("/memo/{id}", Memo.class, expected.getId());
        System.out.println(result.toString());

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(result.getBody()).isEqualTo(expected);

        System.out.println(result.getBody());
    }

    @Ignore
    @Test
    public void pagination() {
        ResponseEntity<Memo[]> result = testRestTemplate.getForEntity("/memo/list?page={page}&size={size}", Memo[].class, 0, 3);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(result.getBody()).hasSize(3);

        Stream.of(result.getBody()).forEach(System.out::println);
    }

    @Ignore
    @Test
    public void store() {
        Memo expected = Memo.of("test title", "test description");

        ResponseEntity<String> result = testRestTemplate.postForEntity("/memo", expected, String.class);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(contentType);
        assertThat(result.getBody()).isEqualTo("success");

        System.out.println(result.getBody());
    }

    @Ignore
    @Test
    public void remove() {
        testRestTemplate.delete("/memo/{id}", 17L);

        ResponseEntity<Memo> result = testRestTemplate.getForEntity("/memo/{id}", Memo.class, 17L);
        System.out.println(result.toString());
    }

}
