package com.spotify.codificacion.services.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spotify.codificacion.services.SpotifyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl implements SpotifyService{

    private final RestTemplate restTemplate;

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    @Value("${spotify.token-url}")
    private String tokenUrl;

    @Value("${spotify.api-base-url}")
    private String apiBaseUrl;


    public Map<String, Object> getPlaylistConCanciones(String playlistId) {
        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // 1. Obtener detalles de la playlist (nombre, descripción)
        String playlistUrl = apiBaseUrl + "/playlists/" + playlistId;
        ResponseEntity<Map> playlistResponse = restTemplate.exchange(playlistUrl, HttpMethod.GET, entity, Map.class);

        String nombre = "";
        String descripcion = "";
        List<Map<String, Object>> canciones = new ArrayList<>();

        if (playlistResponse.getStatusCode() == HttpStatus.OK && playlistResponse.getBody() != null) {
            Map<String, Object> playlistBody = playlistResponse.getBody();
            nombre = (String) playlistBody.get("name");
            descripcion = (String) playlistBody.get("description");

            // 2. Obtener lista de canciones
            Map<String, Object> tracksMap = (Map<String, Object>) playlistBody.get("tracks");
            String tracksUrl = (String) tracksMap.get("href");

            ResponseEntity<Map> tracksResponse = restTemplate.exchange(tracksUrl, HttpMethod.GET, entity, Map.class);
            if (tracksResponse.getStatusCode() == HttpStatus.OK && tracksResponse.getBody() != null) {
                List<Map<String, Object>> items = (List<Map<String, Object>>) tracksResponse.getBody().get("items");
                for (Map<String, Object> item : items) {
                    Map track = (Map) item.get("track");

                    String titulo = (String) track.get("name");
                    String album = ((Map) track.get("album")).get("name").toString();
                    String anno = ((Map) track.get("album")).get("release_date").toString().substring(0, 4);

                    List<Map<String, Object>> artists = (List<Map<String, Object>>) track.get("artists");
                    String artista = artists.get(0).get("name").toString();

                    Map<String, Object> cancion = new LinkedHashMap<>();
                    cancion.put("titulo", titulo);
                    cancion.put("album", album);
                    cancion.put("anno", anno);
                    cancion.put("artista", artista);

                    canciones.add(cancion);
                }
            }
        }

        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("nombre", nombre);
        resultado.put("descripcion", descripcion);
        resultado.put("canciones", canciones);

        return resultado;
    }

    public String getAccessToken() {
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map body = response.getBody();
            if (body != null && body.containsKey("access_token")) {
                return body.get("access_token").toString();
            }
        }
        throw new RuntimeException("No se pudo obtener token de acceso de Spotify");
    }
}
