package com.spotify.codificacion.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spotify.codificacion.services.SpotifyService;
import com.spotify.codificacion.services.impl.SpotifyServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;
    
    @GetMapping("/playlist/{id}")
    public ResponseEntity<Map<String, Object>> getPlaylistConCanciones(@PathVariable String id) {
    	Map<String, Object> artist = spotifyService.getPlaylistConCanciones(id);
        return ResponseEntity.ok(artist);
    }
    
//  @GetMapping("/artist/{id}")
//  public ResponseEntity<Map> getArtist(@PathVariable String id) {
//      Map artist = spotifyService.getArtistById(id);
//      return ResponseEntity.ok(artist);
//  }
}

