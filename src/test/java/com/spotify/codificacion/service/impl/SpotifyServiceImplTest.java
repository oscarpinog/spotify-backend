package com.spotify.codificacion.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.spotify.codificacion.services.impl.SpotifyServiceImpl;

public class SpotifyServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SpotifyServiceImpl spotifyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Inyectar valores de @Value manualmente
        spotifyService = new SpotifyServiceImpl(restTemplate);
        ReflectionTestUtils.setField(spotifyService, "clientId", "mock-client-id");
        ReflectionTestUtils.setField(spotifyService, "clientSecret", "mock-client-secret");
        ReflectionTestUtils.setField(spotifyService, "tokenUrl", "https://mock-token-url");
        ReflectionTestUtils.setField(spotifyService, "apiBaseUrl", "https://mock-api");
    }

    @Test
    public void testGetAccessToken() {
        Map<String, String> mockResponse = new HashMap<>();
        mockResponse.put("access_token", "mock-token");

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(), eq(Map.class)))
            .thenReturn(responseEntity);

        String token = spotifyService.getAccessToken();
        assertEquals("mock-token", token);
    }

    @Test
    public void testGetPlaylistConCanciones() {
        // 1. Mock del token
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("access_token", "mock-token");
        when(restTemplate.postForEntity(anyString(), any(), eq(Map.class)))
            .thenReturn(new ResponseEntity<>(tokenMap, HttpStatus.OK));

        // 2. Mock de la playlist
        Map<String, Object> playlistBody = new HashMap<>();
        playlistBody.put("name", "Mi Playlist");
        playlistBody.put("description", "Descripción");

        Map<String, Object> tracksMap = new HashMap<>();
        tracksMap.put("href", "https://mock-tracks-url");
        playlistBody.put("tracks", tracksMap);

        when(restTemplate.exchange(contains("/playlists/"), eq(HttpMethod.GET), any(), eq(Map.class)))
            .thenReturn(new ResponseEntity<>(playlistBody, HttpStatus.OK));

        // 3. Mock de las canciones
        Map<String, Object> track = new HashMap<>();
        track.put("name", "Canción 1");
        Map<String, Object> album = new HashMap<>();
        album.put("name", "Álbum 1");
        album.put("release_date", "2022-01-01");
        track.put("album", album);
        Map<String, Object> artist = new HashMap<>();
        artist.put("name", "Artista 1");
        track.put("artists", List.of(artist));

        Map<String, Object> item = new HashMap<>();
        item.put("track", track);
        Map<String, Object> tracksBody = new HashMap<>();
        tracksBody.put("items", List.of(item));

        when(restTemplate.exchange(eq("https://mock-tracks-url"), eq(HttpMethod.GET), any(), eq(Map.class)))
            .thenReturn(new ResponseEntity<>(tracksBody, HttpStatus.OK));

        // Ejecutar
        Map<String, Object> resultado = spotifyService.getPlaylistConCanciones("playlist123");

        // Verificar
        assertEquals("Mi Playlist", resultado.get("nombre"));
        assertEquals("Descripción", resultado.get("descripcion"));

        List<Map<String, Object>> canciones = (List<Map<String, Object>>) resultado.get("canciones");
        assertEquals(1, canciones.size());
        assertEquals("Canción 1", canciones.get(0).get("titulo"));
        assertEquals("Álbum 1", canciones.get(0).get("album"));
        assertEquals("2022", canciones.get(0).get("anno"));
        assertEquals("Artista 1", canciones.get(0).get("artista"));
    }

    @Test
    public void testGetAccessTokenFails() {
        ResponseEntity<Map> response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        when(restTemplate.postForEntity(anyString(), any(), eq(Map.class))).thenReturn(response);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            spotifyService.getAccessToken();
        });

        assertEquals("No se pudo obtener token de acceso de Spotify", thrown.getMessage());
    }
}
