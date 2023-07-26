package com.romans.TvTrucker.controller;

import com.romans.TvTrucker.DTO.Episode;
import com.romans.TvTrucker.DTO.Show;
import com.romans.TvTrucker.repository.FavoriteShowRepository;
import com.romans.TvTrucker.repository.model.FavoriteShow;
import com.romans.TvTrucker.repository.model.User;
import com.romans.TvTrucker.repository.model.WatchedEpisode;
import com.romans.TvTrucker.service.FavoriteShowService;
import com.romans.TvTrucker.service.TvMazeService;
import com.romans.TvTrucker.service.UserService;
import com.romans.TvTrucker.service.WatchedEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TvTruckerController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteShowService favoriteShowService;

    @Autowired
    private WatchedEpisodeService watchedEpisodeService;

    @Autowired
    private TvMazeService tvMazeService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) { userService.saveUser(user);}

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/favoriteShow/{userId}/{showId}")
    public void markShowAsFavorite(@PathVariable int userId, @PathVariable int showId) {
        favoriteShowService.markShowAsFavorite(userId, showId);
    }

    @DeleteMapping("/favoriteShow/{userId}/{showId}")
    public void unmarkShowAsFavorite(@PathVariable int userId, @PathVariable int showId) {
        favoriteShowService.unmarkShowAsFavorite(userId, showId);
    }

    @PostMapping("/watchedEpisode/{userId}/{showId}/{seasonNr}/{episodeNr}")
    public void markEpisodeAsWatched(@PathVariable int userId, @PathVariable int showId, @PathVariable int seasonNr, @PathVariable int episodeNr) {
        watchedEpisodeService.markEpisodeAsWatched(userId, showId, seasonNr, episodeNr);
    }

    @DeleteMapping("/watchedEpisode/{userId}/{showId}/{seasonNr}/{episodeNr}")
    public void unmarkEpisodeAsWatched(@PathVariable int userId, @PathVariable int showId, @PathVariable int seasonNr, @PathVariable int episodeNr) {
        watchedEpisodeService.unmarkEpisodeAsWatched(userId, showId, seasonNr, episodeNr);
    }

    @GetMapping("/favoriteShows/{userId}")
    public Set<FavoriteShow> getFavoriteShows(@PathVariable int userId) {
        return userService.usersFavoriteShows(userId);
    }

    @GetMapping("/watchedEpisodes/{userId}/{showId}")
    public Set<WatchedEpisode> getWatchedEpisodesForShow(@PathVariable int userId, @PathVariable int showId) {
        return userService.usersWatchedEpisodes(userId, showId);
    }

    @GetMapping("getShow/{showId}")
    public Show getShow(@PathVariable int showId) {
        return tvMazeService.getShow(showId);
    }

    @GetMapping("getEpisode/{showId}/{seasonNr}/{episodeNr}")
    public Episode getEpisode(@PathVariable int showId,@PathVariable int seasonNr,@PathVariable int episodeNr) {
        return tvMazeService.getEpisode(showId, seasonNr, episodeNr);
    }

}
