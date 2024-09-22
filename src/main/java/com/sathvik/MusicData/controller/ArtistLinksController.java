package com.sathvik.MusicData.controller;

import com.sathvik.MusicData.dto.ArtistLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.exception.MusicDataException;
import com.sathvik.MusicData.service.ArtistLinksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sathvik.MusicData.constants.ValidationMessages.*;

@RestController
@RequestMapping(value = "/artist-links")
@AllArgsConstructor
@Validated
@ResponseBody
@CrossOrigin
public class ArtistLinksController {
    private ArtistLinksService artistLinksService;

    @PostMapping(value = "/save-artist-links")
    public ArtistLinksDTO saveArtistLinks(@RequestBody @Valid ArtistLinksDTO artistLinksDTO) throws MusicDataException {
        return this.artistLinksService.saveArtistLinks(artistLinksDTO);
    }

    @GetMapping(value = "/get-artist-links/{id}")
    public ArtistLinksDTO getArtistLinksById(
            @NotNull(message = ARTIST_ID_MANDATORY)
            @NotBlank(message = ARTIST_ID_INVALID)
            @PathVariable(value = "id") String id
    ) throws MusicDataException {
        return this.artistLinksService.getArtistLinksById(id);
    }

    @GetMapping(value = "/get-all-artist-links")
    public List<ArtistLinksDTO> getArtistLinksForAllArtist() throws MusicDataException {
        return this.artistLinksService.getArtistLinksForAllArtist();
    }

    @PutMapping(value = "/update-artist-links/{id}")
    public String updateArtistLinksById(
            @NotNull(message = ARTIST_ID_MANDATORY)
            @NotBlank(message = ARTIST_ID_INVALID)
            @PathVariable(value = "id") String id,
            @RequestBody @Valid ArtistLinksDTO artistLinksDTO
    ) throws MusicDataException {
        return this.artistLinksService.updateArtistLinksById(id, artistLinksDTO);
    }

    @PutMapping(value = "add-artist-link/{id}")
    public String addArtistLinkById(
            @NotNull(message = ARTIST_ID_MANDATORY)
            @NotBlank(message = ARTIST_ID_INVALID)
            @PathVariable(value = "id") String id,
            @RequestBody @Valid LinkListDTO linkListDTO
    ) throws MusicDataException {
        return this.artistLinksService.addArtistLinkById(id, linkListDTO);
    }

    @DeleteMapping(value = "/delete-artist-links/{id}")
    public String deleteArtistLinksById(
            @NotNull(message = ARTIST_ID_MANDATORY)
            @NotBlank(message = ARTIST_ID_INVALID)
            @PathVariable(value = "id") String id
    ) throws MusicDataException {
        return this.artistLinksService.deleteArtistLinksById(id);
    }
}