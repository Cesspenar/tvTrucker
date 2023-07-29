package com.romans.tvtrucker.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Episode {
    private int id;
    private int season;
    private int number;
    private String name;
    private Date airdate;
    private int runtime;
    private String summary;
}
