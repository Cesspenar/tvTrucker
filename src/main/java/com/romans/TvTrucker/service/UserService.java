package com.romans.TvTrucker.service;

import com.romans.TvTrucker.repository.UserRepository;
import com.romans.TvTrucker.repository.model.FavoriteShow;
import com.romans.TvTrucker.repository.model.User;
import com.romans.TvTrucker.repository.model.WatchedEpisode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public void deleteUserById(int userId) {
        repository.deleteById(userId);
    }

    public Optional<User> getUserById(int userId) {
        return repository.findById(userId);
    }

    public void markShowAsFavorite(int userId, int showId) {
        User user = this.getUserById(userId).get();
        user.addFavoriteShow(showId);
        this.saveUser(user);
    }

    public void unmarkShowAsFavorite(int userId, int showId) {
        User user = this.getUserById(userId).get();
        user.removeFavoriteShow(showId);
        this.saveUser(user);
    }

    public Set<FavoriteShow> usersFavoriteShows(int userId) {
        User user = this.getUserById(userId).get();
        return user.getFavoriteShows();
    }

    public Set<WatchedEpisode> usersWatchedEpisodesForShow(int userId, int showId) {
        User user = this.getUserById(userId).get();
        return user.getWatchedEpisodes().stream()
                .filter(e -> e.getShowId() == showId)
                .collect(Collectors.toSet());
    }

    public void markEpisodeAsWatched(int userId, int showId, int seasonNumber, int episodeNumber) {
        User user = this.getUserById(userId).get();
        user.addWatchedEpisode(showId, seasonNumber, episodeNumber);
        this.saveUser(user);
    }

    public void unmarkEpisodeAsWatched(int userId, int showId, int seasonNumber, int episodeNumber) {
        User user = this.getUserById(userId).get();
        user.removeWatchedEpisode(showId, seasonNumber, episodeNumber);
        this.saveUser(user);
    }
}
