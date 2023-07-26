package com.romans.TvTrucker.repository;

import com.romans.TvTrucker.repository.model.FavoriteShow;
import com.romans.TvTrucker.repository.model.FavoriteShowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteShowRepository extends JpaRepository<FavoriteShow, FavoriteShowId> {
/*    public FavoriteShow findById(int userId, int showId);

    public FavoriteShow deleteByIdFavoriteShowId(int userId, int showId);*/
}
