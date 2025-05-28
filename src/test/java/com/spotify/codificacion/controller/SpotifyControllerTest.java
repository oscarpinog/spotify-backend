package com.spotify.codificacion.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import com.spotify.codificacion.controller.SpotifyController;
import com.spotify.codificacion.services.SpotifyService;

public class SpotifyControllerTest {

    @Mock
    private SpotifyService spotifyService;

    @InjectMocks
    private SpotifyController spotifyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPlaylistConCanciones() {
        // Arrange
        String playlistId = "12345";

        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("id", playlistId);
        mockResponse.put("name", "Playlist de prueba");
        mockResponse.put("tracks", List.of("Cancion 1", "Cancion 2"));

        when(spotifyService.getPlaylistConCanciones(playlistId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Map<String, Object>> response = spotifyController.getPlaylistConCanciones(playlistId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());

        verify(spotifyService, times(1)).getPlaylistConCanciones(playlistId);
    }
}
