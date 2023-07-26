package com.romans.TvTrucker.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteShowId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "showId")
    private int showId;
}
