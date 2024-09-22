package com.sathvik.MusicData.service;

import com.sathvik.MusicData.dto.ArtistLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.exception.MusicDataException;

import java.util.List;

public interface ArtistLinksService {
    ArtistLinksDTO saveArtistLinks(ArtistLinksDTO artistLinksDTO) throws MusicDataException;
    ArtistLinksDTO getArtistLinksById(String id) throws MusicDataException;
    List<ArtistLinksDTO> getArtistLinksForAllArtist() throws MusicDataException;
    String updateArtistLinksById(String id, ArtistLinksDTO artistLinksDTO) throws MusicDataException;
    String addArtistLinkById(String id, LinkListDTO linkListDTO) throws MusicDataException;
    String deleteArtistLinksById(String id) throws MusicDataException;
}