package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sample")
@Getter
@Setter
public class Sample {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long statusId;

    @Column
    private Long typeId;



}
