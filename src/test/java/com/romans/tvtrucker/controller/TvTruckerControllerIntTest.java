package com.romans.tvtrucker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romans.tvtrucker.dto.Episode;
import com.romans.tvtrucker.dto.Show;
import com.romans.tvtrucker.repository.UserRepository;
import com.romans.tvtrucker.repository.model.FavoriteShow;
import com.romans.tvtrucker.repository.model.User;
import com.romans.tvtrucker.repository.model.WatchedEpisode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TvTruckerControllerIntTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void canAddUser() throws Exception {
        String email = "pinky@gmail.com";
        String password = "andTheBrain";
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
        ).andExpect(status().isOk());

        verify(userRepository).save(argument.capture());
        assertEquals(email, argument.getValue().getEmail());
        assertEquals(password, argument.getValue().getPassword());
    }

    @Test
    void canDeleteUser() throws Exception {
        int userId = 1;

        mockMvc.perform(delete("/deleteUser/" + userId))
                .andExpect(status().isOk());

        verify(userRepository).deleteById(userId);
    }

    @Test
    void canMarkShowAsFavorite() throws Exception {
        int userId = 1;
        int showId = 2;
        User user = new User();
        user.setId(userId);
        user.setFavoriteShows(new HashSet<>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(post("/favoriteShow/" + userId + "/" + showId))
                .andExpect(status().isOk());

        verify(userRepository).save(argument.capture());
        assertEquals(userId, argument.getValue().getId());
        assertEquals(showId, argument.getValue().getFavoriteShows().iterator().next().getShowId());
    }

    @Test
    void canUnmarkShowAsFavorite() throws Exception {
        int userId = 1;
        int showIdToBeUnmarked = 2;
        int showIdShouldStay = 3;
        User user = new User();
        user.setId(userId);
        user.setFavoriteShows(new HashSet<>(Arrays.asList(
                new FavoriteShow(userId, showIdToBeUnmarked)
                , new FavoriteShow(userId, showIdShouldStay)
        )));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(delete("/favoriteShow/" + userId + "/" + showIdToBeUnmarked))
                .andExpect(status().isOk());

        verify(userRepository).save(argument.capture());
        assertEquals(userId, argument.getValue().getId());
        assertEquals(showIdShouldStay, argument.getValue().getFavoriteShows().iterator().next().getShowId());
        assertEquals(1, user.getFavoriteShows().size());
    }

    @Test
    void canMarkEpisodeAsWatched() throws Exception {
        int userId = 1;
        int showId = 2;
        int seasonNr = 3;
        int episodeNr = 4;
        User user = new User();
        user.setId(userId);
        user.setWatchedEpisodes(new HashSet<>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(post("/watchedEpisode/" + userId + "/" + showId + "/" + seasonNr + "/" + episodeNr))
                .andExpect(status().isOk());

        verify(userRepository).save(argument.capture());
        assertAll(
                () -> assertEquals(userId, argument.getValue().getId()),
                () -> assertEquals(showId, argument.getValue().getWatchedEpisodes().iterator().next().getShowId()),
                () -> assertEquals(seasonNr, argument.getValue().getWatchedEpisodes().iterator().next().getSeasonNr()),
                () -> assertEquals(episodeNr, argument.getValue().getWatchedEpisodes().iterator().next().getEpisodeNr())
        );
    }

    @Test
    void canUnmarkEpisodeAsWatched() throws Exception {
        int userId = 1;
        int showId = 2;
        int seasonNr = 3;
        int episodeNrToUnmark = 4;
        int episodeNrToStay = 5;
        User user = new User();
        user.setId(userId);
        user.setWatchedEpisodes(new HashSet<>(Arrays.asList(
                new WatchedEpisode(userId, showId, seasonNr, episodeNrToStay),
                new WatchedEpisode(userId, showId, seasonNr, episodeNrToUnmark)
        )));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(delete("/watchedEpisode/" + userId + "/" + showId + "/" + seasonNr + "/" + episodeNrToUnmark))
                .andExpect(status().isOk());

        verify(userRepository).save(argument.capture());
        assertAll(
                () -> assertEquals(userId, argument.getValue().getId()),
                () -> assertEquals(episodeNrToStay, argument.getValue().getWatchedEpisodes().iterator().next().getEpisodeNr()),
                () -> assertEquals(1, argument.getValue().getWatchedEpisodes().size())
        );
    }

    @Test
    void getFavoriteShows() throws Exception {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFavoriteShows(new HashSet<>(Arrays.asList(
                new FavoriteShow(userId, 2),
                new FavoriteShow(userId, 3)
        )));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        String responseJson = mockMvc.perform(get("/favoriteShows/" + userId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        HashSet<FavoriteShow> result = new ObjectMapper().readValue(responseJson, new TypeReference<HashSet<FavoriteShow>>() {});

        assertEquals(user.getFavoriteShows(), result);
    }

    @Test
    void canGetWatchedEpisodesForShow() throws Exception {
        int userId = 1;
        int showIdToGet = 2;
        int showIdToIgnore = 3;
        User user = new User();
        user.setId(userId);
        user.setWatchedEpisodes(new HashSet<>(Arrays.asList(
                new WatchedEpisode(userId, showIdToGet, 1, 2),
                new WatchedEpisode(userId, showIdToIgnore, 2, 3)
        )));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        String responseJson = mockMvc.perform(get("/watchedEpisodes/" + userId + "/" + showIdToGet))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        HashSet<WatchedEpisode> result = new ObjectMapper().readValue(responseJson, new TypeReference<HashSet<WatchedEpisode>>() {});

        assertEquals(1, result.size());
        WatchedEpisode resultEpisode = result.iterator().next();
        assertEquals(userId, resultEpisode.getUserId());
        assertEquals(showIdToGet, resultEpisode.getShowId());
    }

    @Test
    void canGetShow() throws Exception {
        final int SHOW_ID = 1;
        Show expectedShow = new Show(
                1,
                "Under the Dome",
                "English",
                Arrays.asList("Drama", "Science-Fiction", "Thriller"),
                "Ended",
                60,
                "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>"
        );

         String responseJson = mockMvc.perform(get("/getShow/" + SHOW_ID))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Show  returnedShow = new ObjectMapper().readValue(responseJson, Show.class);

        assertEquals(expectedShow, returnedShow);
    }

    @Test
    void canGetEpisode() throws Exception {
        final int SHOW_ID = 1;
        final int SEASON_NR = 2;
        final int EPISODE_NR = 3;
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Episode.json");
        Episode expectedEpisode = new ObjectMapper().readValue(in, Episode.class);

        String responseJson = mockMvc.perform(get("/getEpisode/" + SHOW_ID + "/" + SEASON_NR + "/" + EPISODE_NR))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Episode returnedEpisode = new ObjectMapper().readValue(responseJson, Episode.class);

        assertEquals(expectedEpisode, returnedEpisode);
    }
}