package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BasicData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "created")
    @JsonFormat(pattern = "dd.MM.yyyy HH.mm.ss")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated")
    @JsonFormat(pattern = "dd.MM.yyyy HH.mm.ss")
    private Date lastModifiedDate;
}
