package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    private Long id;

    private Code code;

    public enum Code{
        TO_DO,
        IN_PROGRESS,
    }
}
