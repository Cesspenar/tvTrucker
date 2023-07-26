package com.romans.TvTrucker.repository;

import com.romans.TvTrucker.repository.model.WatchedEpisode;
import com.romans.TvTrucker.repository.model.WatchedEpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, WatchedEpisodeId> {
}
