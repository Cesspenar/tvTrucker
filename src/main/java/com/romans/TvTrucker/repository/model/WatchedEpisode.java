package com.romans.TvTrucker.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "WATCHED_EPISODES")
@AllArgsConstructor
@NoArgsConstructor
public class WatchedEpisode {

    @EmbeddedId
    private WatchedEpisodeId watchedEpisodeID;

    @ManyToOne
    @MapsId("userId")
    private User user;
}
