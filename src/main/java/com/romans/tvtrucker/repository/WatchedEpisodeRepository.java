package com.romans.tvtrucker.repository;

import com.romans.tvtrucker.repository.model.WatchedEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, Integer> {
}
