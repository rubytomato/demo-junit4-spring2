package com.example.demo.web.controller;

import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "memo")
@Slf4j
public class MemoController {

    final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Memo> id(@PathVariable("id") Long id) {
        log.info("MemoController id IN");
        Optional<Memo> memo = memoService.findById(id);
        if (memo.isPresent()) {
            return ResponseEntity.ok(memo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Memo>> list(Pageable page) {
        log.info("MemoController list IN");
        Page<Memo> memos = memoService.findAll(page);
        return ResponseEntity.ok(memos.getContent());
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String store(@RequestBody Memo memo) {
        log.info("MemoController store IN : " + memo);
        memoService.store(memo);
        return "success";
    }

    @DeleteMapping(path = "{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String remove(@PathVariable("id") Long id) {
        log.info("MemoController remove IN : " + id);
        memoService.removeById(id);
        return "success";
    }

}
