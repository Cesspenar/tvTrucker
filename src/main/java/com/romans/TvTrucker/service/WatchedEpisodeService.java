package com.romans.TvTrucker.service;

import com.romans.TvTrucker.repository.WatchedEpisodeRepository;
import com.romans.TvTrucker.repository.model.User;
import com.romans.TvTrucker.repository.model.WatchedEpisode;
import com.romans.TvTrucker.repository.model.WatchedEpisodeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchedEpisodeService {

    @Autowired
    private WatchedEpisodeRepository repository;

    @Autowired
    private UserService userService;

    public void markEpisodeAsWatched(int userId, int showId, int seasonNumber, int episodeNumber) {
        User user = userService.getUserById(userId).get();
        WatchedEpisodeId watchedEpisodeId = new WatchedEpisodeId(userId, showId, seasonNumber, episodeNumber);
        WatchedEpisode watchedEpisode = new WatchedEpisode(watchedEpisodeId, user);
        repository.save(watchedEpisode);
    }

    public void unmarkEpisodeAsWatched(int userId, int showId, int seasonNumber, int episodeNumber) {
        repository.deleteById(new WatchedEpisodeId(userId, showId, seasonNumber, episodeNumber));
    }
}
