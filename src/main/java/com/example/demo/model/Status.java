package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    private Long id;

    public enum Code{
        TO_DO,
        IN_PROGRESS,
    }
}
