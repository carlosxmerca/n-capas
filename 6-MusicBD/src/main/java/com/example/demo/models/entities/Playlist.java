package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
@Table(name = "playlists")
@ToString(exclude = {"songxplaylist"})
public class Playlist {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_code", nullable = true)
    private User user;
    
    @OneToMany(mappedBy = "playList", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SongXPlaylist> songxplaylist;

    public Playlist(String title, String description, User user) {
        super();
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
