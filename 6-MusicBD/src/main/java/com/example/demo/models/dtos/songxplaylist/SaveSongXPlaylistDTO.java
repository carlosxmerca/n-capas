package com.example.demo.models.dtos.songxplaylist;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveSongXPlaylistDTO {
	@NotBlank(message = "song code is required")
	private String songCode;
	@NotBlank(message = "playlist code is required")
	private String playlistCode;
}
