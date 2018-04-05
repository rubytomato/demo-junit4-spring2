package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(statements = {
        "INSERT INTO memo (id, title, description, done, updated) VALUES (11, 'test title 1', 'test description', false, CURRENT_TIMESTAMP)",
        "INSERT INTO memo (id, title, description, done, updated) VALUES (12, 'test title 2', 'test description', true, CURRENT_TIMESTAMP)",
        "INSERT INTO memo (id, title, description, done, updated) VALUES (13, 'test title 3', 'test description', false, CURRENT_TIMESTAMP)",
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = {"DELETE FROM memo WHERE id IN (11,12,13)"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MemoRepositoryIntegrationTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void findById() {
        Memo expected = testEntityManager.find(Memo.class, 11L);
        testEntityManager.flush();

        assertThatCode(() -> {
            Optional<Memo> memo = memoRepository.findById(expected.getId());
            Memo actual = memo.orElseThrow(RuntimeException::new);
            assertThat(actual).isEqualTo(expected);
            System.out.println(actual);
        })
                .doesNotThrowAnyException();
    }

    @Test
    public void remove() {
        Memo expected = testEntityManager.find(Memo.class, 11L);
        testEntityManager.flush();

        memoRepository.deleteById(expected.getId());

        Memo actual = testEntityManager.find(Memo.class, 11L);
        assertThat(actual).isNull();
    }

    @Test
    public void callDemo1() {
        Integer intArg = 1;
        Integer outArg = null;
        String varcharArg = "demo";
        LocalDateTime dateArg = LocalDateTime.now();

        System.out.println("result : " + outArg + " : varcharArg : " + varcharArg + " : dateArg : " + dateArg);

        outArg = memoRepository.demo1(intArg, varcharArg, dateArg);

        System.out.println("result : " + outArg + " : varcharArg : " + varcharArg + " : dateArg : " + dateArg);
    }

}
