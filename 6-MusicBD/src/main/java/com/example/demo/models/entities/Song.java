package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "songs")
public class Song {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID code;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;
    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}
