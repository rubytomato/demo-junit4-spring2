package com.example.demo.domain.service.impl;

import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.repository.MemoRepository;
import com.example.demo.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class MemoServiceImpl implements MemoService {

    private MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Memo> findById(Long id) {
        log.info("MemoServiceImpl findById IN : " + id);
        return memoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Memo> findAll(Pageable page) {
        log.info("MemoServiceImpl findAll IN");
        return memoRepository.findAll(page);
    }

    @Transactional
    @Override
    public void store(Memo memo) {
        log.info("MemoServiceImpl store IN : " + memo);
        memoRepository.save(memo);
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        log.info("MemoServiceImpl removeById IN : " + id);
        memoRepository.deleteById(id);
    }

}

