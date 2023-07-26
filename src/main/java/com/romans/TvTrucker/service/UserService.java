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

    public Set<FavoriteShow> usersFavoriteShows(int userId) {
        User user = this.getUserById(userId).get();
        System.out.println(user.getWatchedEpisodes());
        System.out.println(user.getFavoriteShows());
        return user.getFavoriteShows();
    }

    public Set<WatchedEpisode> usersWatchedEpisodes(int userId, int showId) {
        User user = this.getUserById(userId).get();
        return user.getWatchedEpisodes().stream()
                .filter(e -> e.getWatchedEpisodeID().getShowId() == showId)
                .collect(Collectors.toSet());
    }
}
