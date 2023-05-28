package com.example.demo.services;

import com.example.demo.models.entities.Playlist;
import com.example.demo.models.entities.Song;

public interface SongXPlaylistService {
	void save(Song song, Playlist playlist) throws Exception;
}
