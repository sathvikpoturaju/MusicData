package com.sathvik.MusicData.controller;

import com.sathvik.MusicData.dto.AlbumLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.dto.StoreNameLogoUrl;
import com.sathvik.MusicData.exception.MusicDataException;
import com.sathvik.MusicData.service.AlbumLinksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sathvik.MusicData.constants.ValidationMessages.ALBUM_ID_INVALID;
import static com.sathvik.MusicData.constants.ValidationMessages.ALBUM_ID_MANDATORY;

@RestController
@RequestMapping(value = "/album-links")
@AllArgsConstructor
@Validated
@ResponseBody
@CrossOrigin
public class AlbumLinksController {
    private AlbumLinksService albumLinksService;

    @PostMapping(value = "/save-album-links-for-song")
    public AlbumLinksDTO saveAlbumLinksForSong(@RequestBody @Valid AlbumLinksDTO albumLinksDTO) throws MusicDataException {
        return this.albumLinksService.saveAlbumLinksForSong(albumLinksDTO);
    }

    @GetMapping(value = "/get-album-links-for-song/{id}")
    public AlbumLinksDTO getAlbumLinksForSongById(
            @NotNull(message = ALBUM_ID_MANDATORY)
            @NotBlank(message = ALBUM_ID_INVALID)
            @PathVariable(value = "id") String id
    ) throws MusicDataException {
        return this.albumLinksService.getAlbumLinksForSongById(id);
    }

    @GetMapping(value = "/get-album-links-for-all-songs")
    public List<AlbumLinksDTO> getAlbumLinksForAllSongs() throws MusicDataException {
        return this.albumLinksService.getAlbumLinksForAllSongs();
    }

    @PutMapping(value = "/update-album-links-for-song/{id}")
    public String updateAlbumLinksForSongById(
            @NotNull(message = ALBUM_ID_MANDATORY)
            @NotBlank(message = ALBUM_ID_INVALID)
            @PathVariable(value = "id") String id,
            @RequestBody @Valid AlbumLinksDTO albumLinksDTO
    ) throws MusicDataException {
        return this.albumLinksService.updateAlbumLinksForSongById(id, albumLinksDTO);
    }

    @PutMapping(value = "/add-album-link-for-song/{id}")
    public String addAlbumLinkForSongById(
            @NotNull(message = ALBUM_ID_MANDATORY)
            @NotBlank(message = ALBUM_ID_INVALID)
            @PathVariable(value = "id") String id,
            @RequestBody @Valid LinkListDTO linkListDTO
    ) throws MusicDataException {
        return this.albumLinksService.addAlbumLinkForSongById(id, linkListDTO);
    }

    @DeleteMapping(value = "/delete-album-links-for-song/{id}")
    public String deleteAlbumLinksForSongById(
            @NotNull(message = ALBUM_ID_MANDATORY)
            @NotBlank(message = ALBUM_ID_INVALID)
            @PathVariable(value = "id") String id
    ) throws MusicDataException {
        return this.albumLinksService.deleteAlbumLinksForSongById(id);
    }

    @PutMapping(value = "/update-store-logo-url")
    public String updateStoreLogoUrl(@RequestBody @Valid StoreNameLogoUrl storeNameLogoUrl) {
        return this.albumLinksService.updateStoreLogoUrl(storeNameLogoUrl);
    }
}