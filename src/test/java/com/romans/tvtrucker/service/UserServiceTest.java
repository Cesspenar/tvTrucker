package com.romans.tvtrucker.service;

import com.romans.tvtrucker.repository.UserRepository;
import com.romans.tvtrucker.repository.model.User;
import com.romans.tvtrucker.repository.model.WatchedEpisode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private User user;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(repository);
    }

    @Test
    void canSaveUser() {
        userService.saveUser(user);

        verify(repository).save(user);
    }

    @Test
    void canDeleteUserById() {
        userService.deleteUserById(1);

        verify(repository).deleteById(1);
    }

    @Test
    void canGetUserById() {
        userService.getUserById(1);

        verify(repository).findById(1);
    }

    @Test
    void canMarkShowAsFavorite() {
        int userId = 1;
        int showId = 2;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.markShowAsFavorite(userId, showId);

        verify(user).addFavoriteShow(showId);
        verify(repository).save(user);
    }

    @Test
    void canUnmarkShowAsFavorite() {
        int userId = 1;
        int showId = 2;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.unmarkShowAsFavorite(userId, showId);

        verify(user).removeFavoriteShow(showId);
        verify(repository).save(user);
    }

    @Test
    void canGetUsersFavoriteShows() {
        int userId = 1;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.usersFavoriteShows(userId);

        verify(user).getFavoriteShows();
    }

    @Test
    void canGetUsersWatchedEpisodesForShow() {
        int userId = 1;
        int showId = 2;
        Set<WatchedEpisode> watchedEpisodes = new HashSet<>(Arrays.asList(
                new WatchedEpisode(userId, showId, 10, 11)
                , new WatchedEpisode(userId, showId + 1, 10, 11)
                , new WatchedEpisode(userId, showId + 2, 12, 13)
        ));
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(user.getWatchedEpisodes()).thenReturn(watchedEpisodes);

        Set<WatchedEpisode> result = userService.usersWatchedEpisodesForShow(userId, showId);

        verify(user).getWatchedEpisodes();
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getShowId())
                .isEqualTo(showId);
    }

    @Test
    void canMarkEpisodeAsWatched() {
        int userId = 1;
        int showId = 2;
        int seasonNr = 3;
        int episodeNr = 4;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.markEpisodeAsWatched(userId, showId, seasonNr, episodeNr);

        verify(user).addWatchedEpisode(showId, seasonNr, episodeNr);
        verify(repository).save(user);
    }

    @Test
    void canUnmarkEpisodeAsWatched() {
        int userId = 1;
        int showId = 2;
        int seasonNr = 3;
        int episodeNr = 4;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.unmarkEpisodeAsWatched(userId, showId, seasonNr, episodeNr);

        verify(user).removeWatchedEpisode(showId, seasonNr, episodeNr);
        verify(repository).save(user);
    }
}