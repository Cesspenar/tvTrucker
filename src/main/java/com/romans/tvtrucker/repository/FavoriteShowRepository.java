package com.romans.tvtrucker.repository;

import com.romans.tvtrucker.repository.model.FavoriteShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteShowRepository extends JpaRepository<FavoriteShow, Integer> {
}
