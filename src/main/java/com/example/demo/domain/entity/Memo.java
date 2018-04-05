package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "memo")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "Memo.demo1", procedureName = "procdemo1", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "inArg", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outArg", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "varcharArg", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "dateArg", type = LocalDateTime.class)
    })
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "done", nullable = false)
    private Boolean done;
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public static Memo of(String title, String description) {
        return Memo.of(null, title, description);
    }

    public static Memo of(Long id, String title, String description) {
        return Memo.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }

    @PrePersist
    private void prePersist() {
        done = false;
        updated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }

}
