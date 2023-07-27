package com.romans.TvTrucker.repository;

import com.romans.TvTrucker.repository.model.WatchedEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, Integer> {
}
