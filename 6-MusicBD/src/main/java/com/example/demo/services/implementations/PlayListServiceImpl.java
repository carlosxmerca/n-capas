package com.example.demo.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.playlists.SavePlayListDTO;
import com.example.demo.models.dtos.playlists.UpdatePlayListDTO;
import com.example.demo.models.entities.Playlist;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.PlayListRepository;
import com.example.demo.services.PlaylistService;

@Service
public class PlayListServiceImpl implements PlaylistService {
	
	@Autowired
	private PlayListRepository playlistRepository;

	@Override
	public void save(SavePlayListDTO playlistInfo, User user) throws Exception {
		Playlist playlist = new Playlist(
				playlistInfo.getTitle(),
				playlistInfo.getDescription(),
				user
				);
				
		playlistRepository.save(playlist);
	}

	@Override
	public void update(UpdatePlayListDTO playlistInfo) throws Exception {
		UUID _code = UUID.fromString(playlistInfo.getCode());
		Playlist playlist = playlistRepository.findById(_code).orElse(null);
		
		if (playlist == null) return;
		
		playlist.setTitle(playlistInfo.getTitle());
		playlist.setDescription(playlistInfo.getDescription());
		
		playlistRepository.save(playlist);
	}

	@Override
	public void deleteOneById(String code) throws Exception {
		UUID _code = UUID.fromString(code);
		playlistRepository.deleteById(_code);
	}

	@Override
	public Playlist findOneById(String code) {
		try {
			UUID _code = UUID.fromString(code);
			return playlistRepository.findById(_code).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Playlist> findAll() {
		return playlistRepository.findAll();
	}

}
