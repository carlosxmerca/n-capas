package com.example.demo.models.dtos.response;

import java.util.List;

import com.example.demo.models.entities.Playlist;
import com.example.demo.models.entities.Song;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayListWithSongsDTO {
	private Playlist playlist;
	private List<Song> songs;
}
