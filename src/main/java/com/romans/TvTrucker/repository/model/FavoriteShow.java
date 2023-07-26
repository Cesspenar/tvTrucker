package com.romans.TvTrucker.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "FAVORITE_SHOWS")
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteShow {

    @EmbeddedId
    private FavoriteShowId favoriteShowId;

    @ManyToOne
    @MapsId("userId")
    private User user;
}
