package com.romans.tvtrucker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Episode {
    private int id;
    private int season;
    private int number;
    private String name;
    private Date airdate;
    private int runtime;
    private String summary;
}
