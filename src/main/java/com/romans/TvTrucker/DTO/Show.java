package com.romans.TvTrucker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private String summary;
}
