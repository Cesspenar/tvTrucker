package com.romans.TvTrucker.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WatchedEpisodeId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "showId")
    private int showId;

    @Column(name = "seasonNumber")
    private int seasonNumber;

    @Column(name = "episodeNumber")
    private int episodeNumber;
}
