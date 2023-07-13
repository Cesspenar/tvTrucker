package com.romans.TvTrucker.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ANIMAL")
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    public Animal(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

}
