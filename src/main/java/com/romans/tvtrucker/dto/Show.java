package com.romans.tvtrucker.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Show {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private String summary;
}
