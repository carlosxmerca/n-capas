package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "songsxplaylists")
public class SongXPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private UUID code;

    private UUID playlist_code;
    private Timestamp date_added;

    public SongXPlaylist(UUID playlist_code, Timestamp date_added) {
        this.playlist_code = playlist_code;
        this.date_added = date_added;
    }
}
