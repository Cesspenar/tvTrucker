package com.romans.tvtrucker.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFavoriteShows(new HashSet<>());
        user.setWatchedEpisodes(new HashSet<>());
    }

    @Test
    void canAddFavoriteShow() {
        int showId = 1;

        user.addFavoriteShow(showId);

        assertThat(user.getFavoriteShows()).hasSize(1);
        assertThat(user.getFavoriteShows().iterator().next().getShowId())
                .isEqualTo(showId);
    }

    @Test
    void removeFavoriteShow() {
        int toBeRemovedshowId = 1;
        int shouldStayShowId = 2;
        user.setFavoriteShows(new HashSet<>(Arrays.asList(
                new FavoriteShow(user.getId(), shouldStayShowId)
                , new FavoriteShow(user.getId(), toBeRemovedshowId)

        )));

        user.removeFavoriteShow(toBeRemovedshowId);

        assertThat(user.getFavoriteShows()).hasSize(1);
        assertThat(user.getFavoriteShows().iterator().next().getShowId())
                .isEqualTo(shouldStayShowId);
    }

    @Test
    void addWatchedEpisode() {
        int showId = 1;
        int seasonNr = 2;
        int episodeNr = 3;

        user.addWatchedEpisode(showId, seasonNr, episodeNr);

        assertThat(user.getWatchedEpisodes()).hasSize(1);
        assertThat(user.getWatchedEpisodes().iterator().next())
                .returns(showId, from(WatchedEpisode::getShowId))
                .returns(seasonNr, from(WatchedEpisode::getSeasonNr))
                .returns(episodeNr, from(WatchedEpisode::getEpisodeNr));
    }

    @Test
    void removeWatchedEpisode() {
        int showId = 1;
        int seasonNr = 2;
        int toBeRemovedEpisodeNr = 5;
        int shouldStayEpisodeNr = 6;
        user.setWatchedEpisodes(new HashSet<>(Arrays.asList(
                new WatchedEpisode(user.getId(), showId, seasonNr, toBeRemovedEpisodeNr)
                , new WatchedEpisode(user.getId(), showId, seasonNr, shouldStayEpisodeNr)


        )));

        user.removeWatchedEpisode(showId, seasonNr, toBeRemovedEpisodeNr);

        assertThat(user.getWatchedEpisodes()).hasSize(1);
        assertThat(user.getWatchedEpisodes().iterator().next().getEpisodeNr())
                .isEqualTo(shouldStayEpisodeNr);
    }
}