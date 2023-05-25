package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "user_code")
    private UUID userCode;

    public Playlist(String title, String description, UUID userCode) {
        super();
        this.title = title;
        this.description = description;
        this.userCode = userCode;
    }
}
