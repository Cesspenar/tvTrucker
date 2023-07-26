package com.romans.TvTrucker.service;

import com.romans.TvTrucker.DTO.Episode;
import com.romans.TvTrucker.DTO.Show;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TvMazeService {

    private static final String TVMAZE = "https://api.tvmaze.com/";

    public Show getShow(int showId) {
        String url = TVMAZE + "shows/" + showId;
        return new RestTemplate().getForObject(url, Show.class);
    }

    public List<Episode> getAllShowEpisodes(int showId) {
        String url = TVMAZE + "shows/" + showId + "/episodes";
        ResponseEntity<Episode[]> response = new RestTemplate()
                .getForEntity(url, Episode[].class);
        return Arrays.asList(response.getBody());
    }

    public Episode getEpisode(int showId, int seasonId, int episodeId) {
        String url = TVMAZE + "shows/" + showId + "/episodebynumber?season=" + seasonId + "&number=" + episodeId;
        System.out.println(url);
        return new RestTemplate().getForObject(url, Episode.class);
    }


}
