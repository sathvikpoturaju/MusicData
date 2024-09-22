package com.sathvik.MusicData.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static com.sathvik.MusicData.constants.ValidationMessages.*;

@Data
public class ArtistLinksDTO {
    private String id;

    @NotNull(message = ARTIST_NAME_MANDATORY)
    private String artistName;

    @NotNull(message = DESCRIPTION_MANDATORY)
    private String description;

    @NotNull(message = PROFILE_PICTURE_URL_MANDATORY)
    private String profilePictureUrl;

    @NotNull(message = BANNER_URL_MANDATORY)
    private String bannerUrl;

    @NotNull(message = LINKS_MANDATORY)
    @NotEmpty(message = LINKS_EMPTY)
    @Valid
    private List<LinkDTO> links;
}
