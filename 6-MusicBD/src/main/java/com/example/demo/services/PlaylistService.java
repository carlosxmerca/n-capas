package com.example.demo.services;

import com.example.demo.models.dtos.playlists.SavePlayListDTO;
import com.example.demo.models.dtos.playlists.UpdatePlayListDTO;
import com.example.demo.models.entities.Playlist;
import com.example.demo.models.entities.User;

import java.util.List;

public interface PlaylistService {
    void save(SavePlayListDTO playlistInfo, User user) throws Exception;
    void update(UpdatePlayListDTO playlistInfo) throws Exception;
    void deleteOneById(String code) throws Exception;
    Playlist findOneById(String code);
    List<Playlist> findAll();
}
