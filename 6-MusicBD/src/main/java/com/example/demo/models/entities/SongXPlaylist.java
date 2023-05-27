package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songsxplaylists")
public class SongXPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private UUID code;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "song_code", nullable = true)
    private Song song;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playlist_code", nullable = true)
    private Playlist playList;
    
    @Column(name = "date_added", nullable = true)
    private Timestamp date_added;

	public SongXPlaylist(Song song, Playlist playList) {
		super();
		this.song = song;
		this.playList = playList;
	}
    
}
