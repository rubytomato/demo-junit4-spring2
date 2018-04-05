package com.example.demo.domain.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemoTests {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void persist() {
        Memo expected = Memo.of("test title", "test description");
        Memo actual = entityManager.persistFlushFind(expected);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void remove() {
        Memo expected = Memo.of("test title", "test description");
        Memo actual = entityManager.persistFlushFind(expected);

        entityManager.remove(actual);
        entityManager.flush();
    }

}
