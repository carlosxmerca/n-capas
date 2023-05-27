package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.songs.SaveSongDTO;
import com.example.demo.models.dtos.songs.UpdateSongDTO;
import com.example.demo.utils.RequestErrorHandler;

@RestController
@RequestMapping("/songs")
@CrossOrigin("*")
public class SongController {
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("")
	public ResponseEntity<?> getSongs() {
		return new ResponseEntity<>("songs", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSong() {
		return new ResponseEntity<>("song", HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createSong(
			@ModelAttribute SaveSongDTO data, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateSong(
			@ModelAttribute UpdateSongDTO data, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSong() {
		return new ResponseEntity<>("deleted song", HttpStatus.OK);
	}
}
