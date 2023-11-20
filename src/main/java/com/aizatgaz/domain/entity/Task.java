package com.aizatgaz.domain.entity;

import com.aizatgaz.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "springmvc_crud", name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "INT")
    @Enumerated
    private Status status;

}
