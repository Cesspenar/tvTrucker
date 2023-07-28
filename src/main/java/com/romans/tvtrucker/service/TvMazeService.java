package com.romans.tvtrucker.service;

import com.romans.tvtrucker.dto.Episode;
import com.romans.tvtrucker.dto.Show;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TvMazeService {

    private static final String TVMAZE = "https://api.tvmaze.com/";
    private static final String SHOWS = TVMAZE + "shows/";

    public Show getShow(int showId) {
        String url = SHOWS + showId;
        return new RestTemplate().getForObject(url, Show.class);
    }

    public List<Episode> getAllShowEpisodes(int showId) {
        String url = SHOWS + showId + "/episodes";
        ResponseEntity<Episode[]> response = new RestTemplate()
                .getForEntity(url, Episode[].class);
        return Arrays.asList(response.getBody());
    }

    public Episode getEpisode(int showId, int seasonId, int episodeId) {
        String url = SHOWS + showId + "/episodebynumber?season=" + seasonId + "&number=" + episodeId;
        return new RestTemplate().getForObject(url, Episode.class);
    }


}
