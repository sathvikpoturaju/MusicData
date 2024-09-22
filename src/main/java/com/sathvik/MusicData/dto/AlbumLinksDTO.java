package com.sathvik.MusicData.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static com.sathvik.MusicData.constants.ValidationMessages.*;

@Data
public class AlbumLinksDTO {
    private String id;
    
    @NotNull(message = ALBUM_NAME_MANDATORY)
    private String albumName;

    @NotNull(message = COVER_ART_URL_MANDATORY)
    private String coverArtUrl;

    @NotNull(message = ARTIST_NAME_MANDATORY)
    private String artistName;

    @NotNull(message = LINKS_MANDATORY)
    @NotEmpty(message = LINKS_EMPTY)
    @Valid
    private List<LinkDTO> links;
}