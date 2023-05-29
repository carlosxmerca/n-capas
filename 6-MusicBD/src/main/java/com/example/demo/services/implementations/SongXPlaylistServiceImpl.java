package com.example.demo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.entities.Playlist;
import com.example.demo.models.entities.Song;
import com.example.demo.models.entities.SongXPlaylist;
import com.example.demo.repositories.SongxPlayListRepository;
import com.example.demo.services.SongXPlaylistService;

@Service
public class SongXPlaylistServiceImpl implements SongXPlaylistService {
	
	@Autowired
	private SongxPlayListRepository repository;

	@Override
	public void save(Song song, Playlist playlist) throws Exception {
		SongXPlaylist relation = new SongXPlaylist(song, playlist);
		repository.save(relation);
	}

	@Override
	public void delete(String songCode, String PlaylistCode) throws Exception {
		// TODO : implement delete song from play-list service
	}

}
