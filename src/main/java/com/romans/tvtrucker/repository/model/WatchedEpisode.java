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
@Table(name = "WATCHED_EPISODES",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "showId", "seasonNr", "episodeNr"})})
public class WatchedEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nullable
    private Integer userId;
    private int showId;
    private int seasonNr;
    private int episodeNr;

    public WatchedEpisode(@Nullable Integer userId, int showId, int seasonNr, int episodeNr) {
        this.userId = userId;
        this.showId = showId;
        this.seasonNr = seasonNr;
        this.episodeNr = episodeNr;
    }
}
