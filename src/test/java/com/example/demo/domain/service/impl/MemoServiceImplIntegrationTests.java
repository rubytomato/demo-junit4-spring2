package com.example.demo.domain.service.impl;

import com.example.demo.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
        "logging.level.com.example.demo.domain= DEBUG"
})
@Transactional
public class MemoServiceImplIntegrationTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MemoServiceImpl sut;

    @Test
    @Sql(statements = {"INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'test title', 'test description', 0, '2018-04-01 00:00:00')"})
    public void findById() {
        Memo expected = entityManager.find(Memo.class, 1L);
        entityManager.flush();
        entityManager.clear();
        System.out.println(expected);

        Optional<Memo> memo = sut.findById(expected.getId());
        Memo actual = memo.orElseThrow(RuntimeException::new);
        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findById_NotFound() {
        Optional<Memo> actual = sut.findById(9999L);
        assertThat(actual.orElse(null)).isNull();
    }

    @Test
    @Sql(scripts = {"classpath:import.sql"})
    public void findAll() {
        Pageable page = PageRequest.of(0, 3);
        Page<Memo> actual = sut.findAll(page);

        assertThat(actual).isNotNull();
        assertThat(actual.getContent()).hasSize(3);
    }

    @Test
    public void store() {
        Memo expected = Memo.of("test title", "test description");
        sut.store(expected);
        System.out.println(expected);

        entityManager.flush();
        entityManager.clear();

        Memo actual = entityManager.find(Memo.class, expected.getId());
        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Sql(statements = {"INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'test title', 'test description', 0, '2018-04-01 00:00:00')"})
    public void removeById() {
        Memo expected = entityManager.find(Memo.class, 1L);
        entityManager.flush();
        entityManager.clear();

        assertThatCode(() -> {
            sut.removeById(expected.getId());
            Memo actual = entityManager.find(Memo.class, 1L);
            assertThat(actual).isNull();
        }).doesNotThrowAnyException();
    }

    @Test
    public void callDemo1() {
        Integer inArg = 1;
        String varcharArg = "demo";
        LocalDateTime dateArg = LocalDateTime.now();

        StoredProcedureQuery demo1 = entityManager.createStoredProcedureQuery("procdemo1")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.INOUT)
                .registerStoredProcedureParameter(4, LocalDateTime.class, ParameterMode.INOUT);

        demo1.setParameter(1, inArg)
                .setParameter(3, varcharArg)
                .setParameter(4, dateArg);

        demo1.execute();

        Integer resOutArg = (Integer) demo1.getOutputParameterValue(2);
        String resVarcharArg = (String) demo1.getOutputParameterValue(3);
        LocalDateTime resDateArg = (LocalDateTime) demo1.getOutputParameterValue(4);
        System.out.println("outArg : " + resOutArg + " : varcharArg : " + resVarcharArg + " : dateArg : " + resDateArg);
    }

    @Test
    public void callDemo1WithName() {
        Integer inArg = 1;
        String varcharArg = "demo";
        LocalDateTime dateArg = LocalDateTime.now();

        StoredProcedureQuery demo1 = entityManager.createNamedStoredProcedureQuery("Memo.demo1");

        demo1.setParameter("inArg", inArg)
                .setParameter("varcharArg", varcharArg)
                .setParameter("dateArg", dateArg);

        demo1.execute();

        Integer resOutArg = (Integer) demo1.getOutputParameterValue("outArg");
        String resVarcharArg = (String) demo1.getOutputParameterValue("varcharArg");
        LocalDateTime resDateArg = (LocalDateTime) demo1.getOutputParameterValue("dateArg");
        System.out.println("outArg : " + resOutArg + " : varcharArg : " + resVarcharArg + " : dateArg : " + resDateArg);
    }

    @Test
    @Sql(statements = {"INSERT INTO memo (id, title, description, done, updated) VALUES (11, 'test title', 'test description', 0, '2018-04-01 00:00:00')"})
    public void callDemo2() {
        StoredProcedureQuery demo2 = entityManager.createNamedStoredProcedureQuery("Memo.demo2");

        demo2.setParameter("id", 11L)
                .setParameter("description", "test");

        demo2.execute();

        Memo memo = entityManager.find(Memo.class, 11L);
        System.out.println(memo.toString());
    }

}
