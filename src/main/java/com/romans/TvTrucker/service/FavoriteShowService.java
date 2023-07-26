package com.romans.TvTrucker.service;

import com.romans.TvTrucker.repository.FavoriteShowRepository;
import com.romans.TvTrucker.repository.model.FavoriteShow;
import com.romans.TvTrucker.repository.model.FavoriteShowId;
import com.romans.TvTrucker.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteShowService {

    @Autowired
    private FavoriteShowRepository repository;

    @Autowired
    private UserService userService;

    public void markShowAsFavorite(int userId, int showId) {
        User user = userService.getUserById(userId).get();
        FavoriteShowId favoriteShowId = new FavoriteShowId(userId, showId);
        FavoriteShow favoriteShow = new FavoriteShow(favoriteShowId, user);
        repository.save(favoriteShow);
    }

    public void unmarkShowAsFavorite(int userId, int showId) {
        repository.deleteById(new FavoriteShowId(userId, showId));
    }

}
