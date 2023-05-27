package com.example.demo.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.songs.SaveSongDTO;
import com.example.demo.models.dtos.songs.UpdateSongDTO;
import com.example.demo.models.entities.Song;
import com.example.demo.repositories.SongRepository;
import com.example.demo.services.SongService;

@Service
public class SongServiceImpl implements SongService {
	
	@Autowired
	private SongRepository songRepository;

	@Override
	public void save(SaveSongDTO songInfo) throws Exception {
		Song song = new Song(
				songInfo.getTitle(),
				songInfo.getDuration()
				);
		
		songRepository.save(song);
	}

	@Override
	public void update(UpdateSongDTO songInfo) throws Exception {
		UUID _code = UUID.fromString(songInfo.getCode());
		Song song = songRepository.findById(_code).orElse(null);
		
		if (song == null) return;
		
		song.setTitle(songInfo.getTitle());
		song.setDuration(songInfo.getDuration());
		
		songRepository.save(song);
	}

	@Override
	public void deleteOneById(String code) throws Exception {
		UUID _code = UUID.fromString(code);
		songRepository.deleteById(_code);
	}

	@Override
	public Song findOneById(String code) {
		try {
			UUID _code = UUID.fromString(code);
			return songRepository.findById(_code).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Song findOneByTitle(String name) {
		return songRepository.findOneByTitle(name);
	}

	@Override
	public List<Song> findAll() {
		return songRepository.findAll();
	}
	
}
