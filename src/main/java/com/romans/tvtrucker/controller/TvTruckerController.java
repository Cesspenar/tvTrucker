package com.romans.tvtrucker.controller;

import com.romans.tvtrucker.dto.Episode;
import com.romans.tvtrucker.dto.Show;
import com.romans.tvtrucker.repository.model.FavoriteShow;
import com.romans.tvtrucker.repository.model.User;
import com.romans.tvtrucker.repository.model.WatchedEpisode;
import com.romans.tvtrucker.service.TvMazeService;
import com.romans.tvtrucker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TvTruckerController {

    @Autowired
    private UserService userService;

    @Autowired
    private TvMazeService tvMazeService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/favoriteShow/{userId}/{showId}")
    public void markShowAsFavorite(@PathVariable int userId, @PathVariable int showId) {
        userService.markShowAsFavorite(userId, showId);
    }

    @DeleteMapping("/favoriteShow/{userId}/{showId}")
    public void unmarkShowAsFavorite(@PathVariable int userId, @PathVariable int showId) {
        userService.unmarkShowAsFavorite(userId, showId);
    }

    @PostMapping("/watchedEpisode/{userId}/{showId}/{seasonNr}/{episodeNr}")
    public void markEpisodeAsWatched(@PathVariable int userId, @PathVariable int showId, @PathVariable int seasonNr, @PathVariable int episodeNr) {
        userService.markEpisodeAsWatched(userId, showId, seasonNr, episodeNr);
    }

    @DeleteMapping("/watchedEpisode/{userId}/{showId}/{seasonNr}/{episodeNr}")
    public void unmarkEpisodeAsWatched(@PathVariable int userId, @PathVariable int showId, @PathVariable int seasonNr, @PathVariable int episodeNr) {
        userService.unmarkEpisodeAsWatched(userId, showId, seasonNr, episodeNr);
    }

    @GetMapping("/favoriteShows/{userId}")
    public Set<FavoriteShow> getFavoriteShows(@PathVariable int userId) {
        return userService.usersFavoriteShows(userId);
    }

    @GetMapping("/watchedEpisodes/{userId}/{showId}")
    public Set<WatchedEpisode> getWatchedEpisodesForShow(@PathVariable int userId, @PathVariable int showId) {
        return userService.usersWatchedEpisodesForShow(userId, showId);
    }

    @GetMapping("getShow/{showId}")
    public Show getShow(@PathVariable int showId) {
        return tvMazeService.getShow(showId);
    }

    @GetMapping("getEpisode/{showId}/{seasonNr}/{episodeNr}")
    public Episode getEpisode(@PathVariable int showId, @PathVariable int seasonNr, @PathVariable int episodeNr) {
        return tvMazeService.getEpisode(showId, seasonNr, episodeNr);
    }

}
