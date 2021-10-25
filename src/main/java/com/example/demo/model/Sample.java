package com.example.demo.model;

import com.example.demo.model.type.StatusType;
import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sample")
public class Sample {

    @Id
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusType status;

}
