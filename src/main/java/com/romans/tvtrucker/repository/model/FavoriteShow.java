package com.romans.tvtrucker.repository.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FAVORITE_SHOWS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "showId"})})
public class FavoriteShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nullable
    private Integer userId;
    private int showId;

    public FavoriteShow(int userId, int showId) {
        this.userId = userId;
        this.showId = showId;
    }
}
