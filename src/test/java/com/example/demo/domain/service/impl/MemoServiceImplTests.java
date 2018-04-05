package com.example.demo.domain.service.impl;

import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.repository.MemoRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

public class MemoServiceImplTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private MemoRepository memoRepository;
    @InjectMocks
    private MemoServiceImpl sut;

    @Test
    public void findById() {
        Memo expected = Memo.of(1L,"test title", "test description");
        Mockito.when(memoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expected));

        Memo actual = sut.findById(1L).orElse(null);

        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findById_NotFound() {
        Mockito.when(memoRepository.findById(anyLong())).thenReturn(Optional.empty());

        Memo actual = sut.findById(9999L).orElse(null);

        assertThat(actual).as("actualは検索できない").isNull();
    }

    @Test
    public void findAll() {
        Pageable page = PageRequest.of(0, 5);
        List<Memo> expected = Arrays.asList(
            Memo.of(1L,"test title 1", "test description 1"),
            Memo.of(2L,"test title 2", "test description 2"),
            Memo.of(3L,"test title 3", "test description 3")
        );

        Mockito.when(memoRepository.findAll(eq(page))).thenReturn(new PageImpl<>(expected, page, 3));

        Page<Memo> actual = sut.findAll(page);

        assertThat(actual.getContent()).as("actualは必ず検索できる").isNotNull();
        assertThat(actual.getContent()).hasSize(3);
    }

}
