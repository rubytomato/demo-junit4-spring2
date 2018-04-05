package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    @Procedure(outputParameterName = "outArg")
    Integer demo1(@Param("inArg") Integer inArg, @Param("varcharArg") String vArg, @Param("dateArg") LocalDateTime dArg);
}
