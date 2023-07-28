package com.romans.tvtrucker.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Set<FavoriteShow> favoriteShows;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Set<WatchedEpisode> watchedEpisodes;

    public void addFavoriteShow(int showId) {
        FavoriteShow favoriteShow = new FavoriteShow(this.getId(), showId);
        this.favoriteShows.add(favoriteShow);
    }

    public void removeFavoriteShow(int showId) {
        this.favoriteShows.removeIf(e -> e.getShowId() == showId);

    }

    public void addWatchedEpisode(int showId, int seasonNr, int episodeNr) {
        WatchedEpisode watchedEpisode = new WatchedEpisode(this.getId(), showId, seasonNr, episodeNr);
        this.watchedEpisodes.add(watchedEpisode);
    }

    public void removeWatchedEpisode(int showId, int seasonNr, int episodeNr) {
        this.getWatchedEpisodes().removeIf(e -> e.getShowId() == showId
                && e.getSeasonNr() == seasonNr
                && e.getEpisodeNr() == episodeNr);
    }

}
