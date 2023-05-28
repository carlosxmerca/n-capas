package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
@Table(name = "songs")
@ToString(exclude = {"songxplaylist"})
public class Song {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID code;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;
    
    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SongXPlaylist> songxplaylist;
    
    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}
