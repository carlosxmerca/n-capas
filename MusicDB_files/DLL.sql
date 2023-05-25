-- CREATE DATABASE MusicDB;

-- users
CREATE TABLE users (
    code UUID NOT NULL DEFAULT gen_random_uuid(),
    username VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    pass VARCHAR NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (code)
);

-- playlists
CREATE TABLE playlists (
    code UUID NOT NULL DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    description VARCHAR,
    user_code UUID NOT NULL,
    CONSTRAINT playlist_pk PRIMARY KEY (code),
    FOREIGN KEY (user_code) REFERENCES users (code) ON UPDATE CASCADE
);

-- songs
CREATE TABLE songs (
    code UUID NOT NULL DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    duration INT NOT NULL,
    CONSTRAINT song_pk PRIMARY KEY (code)
);

-- songsxplaylists
CREATE TABLE songsxplaylists (
    code UUID NOT NULL DEFAULT gen_random_uuid(),
    song_code UUID NOT NULL,
    playlist_code UUID NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT song_x_playlist_pk PRIMARY KEY (code),
    FOREIGN KEY (song_code) REFERENCES songs (code) ON UPDATE CASCADE,
    FOREIGN KEY (playlist_code) REFERENCES playlists (code) ON UPDATE CASCADE
);
