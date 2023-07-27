package com.romans.TvTrucker.repository;

import com.romans.TvTrucker.repository.model.FavoriteShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteShowRepository extends JpaRepository<FavoriteShow, Integer> {
}
