package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.response.MessageDTO;
import com.example.demo.models.dtos.songs.SaveSongDTO;
import com.example.demo.models.dtos.songs.UpdateSongDTO;
import com.example.demo.models.entities.Song;
import com.example.demo.services.SongService;
import com.example.demo.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songs")
@CrossOrigin("*")
public class SongController {
	
	@Autowired
	private SongService songService;
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("")
	public ResponseEntity<?> getSongs() {
		List<Song> songs = songService.findAll();
		return new ResponseEntity<>(songs, HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<?> getSongByTitle(@PathVariable String title) {
		Song song = songService.findOneByTitle(title);
		if (song == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(song, HttpStatus.OK);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getSongByCode(@PathVariable String code) {
		Song song = songService.findOneById(code);
		if (song == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(song, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createSong(
			@ModelAttribute @Valid SaveSongDTO data, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			songService.save(data);
			return new ResponseEntity<>(new MessageDTO("song created"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateSong(
			@ModelAttribute @Valid UpdateSongDTO data, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		Song song = songService.findOneById(data.getCode());
		if (song == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		try {
			songService.update(data);
			return new ResponseEntity<>(new MessageDTO("song updated"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteSong(@PathVariable String code) {
		Song song = songService.findOneById(code);
		if (song == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		try {
			songService.deleteOneById(code);
			return new ResponseEntity<>(new MessageDTO("deleted song"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
