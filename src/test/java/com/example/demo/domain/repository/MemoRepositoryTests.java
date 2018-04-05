package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemoRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void findById() {
        Memo expected = Memo.of("test title", "test description");
        testEntityManager.persistAndFlush(expected);

        assertThatCode(() -> {
            Optional<Memo> memo = memoRepository.findById(expected.getId());
            Memo actual = memo.orElseThrow(RuntimeException::new);
            assertThat(actual).isEqualTo(expected);
        })
        .doesNotThrowAnyException();
    }

    @Test
    public void findById_NotFound() {
        Optional<Memo> actual = memoRepository.findById(9999L);
        assertThat(actual.orElse(null)).isNull();
    }

    @Test
    public void save() {
        Memo expected = Memo.of("test title", "test description");
        memoRepository.saveAndFlush(expected);

        Memo actual = testEntityManager.find(Memo.class, expected.getId());
        assertThat(actual).isEqualTo(expected);
    }

}
