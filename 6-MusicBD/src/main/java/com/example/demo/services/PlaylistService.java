package com.example.demo.services;

import com.example.demo.models.dtos.playlists.SavePlayListDTO;
import com.example.demo.models.dtos.playlists.UpdatePlayListDTO;
import com.example.demo.models.entities.Song;

import java.util.List;

public interface PlaylistService {
    void save(SavePlayListDTO playlistInfo) throws Exception;
    void update(UpdatePlayListDTO playlistInfo) throws Exception;
    void deleteOneById(String code) throws Exception;
    Song findOneById(String code);
    List<Song> findAll();
}
