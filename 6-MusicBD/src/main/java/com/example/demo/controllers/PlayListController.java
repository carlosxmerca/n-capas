package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play-lists")
@CrossOrigin("*")
public class PlayListController {
	
	@GetMapping("")
	public ResponseEntity<?> getPlayLists() {
		return new ResponseEntity<>("playlists", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPlayList() {
		return new ResponseEntity<>("playlist", HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createPlayList() {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updatePlayList() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> deletePlayList() {
		return new ResponseEntity<>("deleted play list", HttpStatus.OK);
	}

}
