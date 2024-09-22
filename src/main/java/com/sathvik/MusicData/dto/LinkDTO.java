package com.sathvik.MusicData.dto;

import com.sathvik.MusicData.enums.UrlType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.sathvik.MusicData.constants.ValidationMessages.*;

@Data
public class LinkDTO {
    @NotNull(message = STORE_NAME_MANDATORY)
    private String storeName;

    @NotNull(message = STORE_LOGO_URL_MANDATORY)
    private String storeLogoUrl;

    @NotNull(message = ALBUM_OR_ARTIST_URL_MANDATORY)
    private String url;

    private String albumUrl;
    private String artistUrl;

    public void initUrl(UrlType urlType) {
        switch (urlType) {
            case ARTIST_URL -> this.artistUrl = this.url;
            case ALBUM_URL -> this.albumUrl = this.url;
            default -> {}
        }
    }
}