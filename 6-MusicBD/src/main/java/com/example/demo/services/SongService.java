package com.example.demo.services;

import com.example.demo.models.dtos.songs.SaveSongDTO;
import com.example.demo.models.dtos.songs.UpdateSongDTO;
import com.example.demo.models.entities.Song;

import java.util.List;

public interface SongService {
    void save(SaveSongDTO songInfo) throws Exception;
    void update(UpdateSongDTO songInfo) throws Exception;
    void deleteOneById(String code) throws Exception;
    Song findOneById(String code);
    List<Song> findAll();
}
