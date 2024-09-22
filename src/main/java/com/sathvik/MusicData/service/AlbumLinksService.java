package com.sathvik.MusicData.service;

import com.sathvik.MusicData.dto.AlbumLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.dto.StoreNameLogoUrl;
import com.sathvik.MusicData.exception.MusicDataException;

import java.util.List;

public interface AlbumLinksService {
    AlbumLinksDTO saveAlbumLinksForSong(AlbumLinksDTO albumLinksDTO) throws MusicDataException;
    AlbumLinksDTO getAlbumLinksForSongById(String id) throws MusicDataException;
    List<AlbumLinksDTO> getAlbumLinksForAllSongs() throws MusicDataException;
    String updateAlbumLinksForSongById(String id, AlbumLinksDTO albumLinksDTO) throws MusicDataException;
    String addAlbumLinkForSongById(String id, LinkListDTO linkListDTO) throws MusicDataException;
    String deleteAlbumLinksForSongById(String id) throws MusicDataException;
    String updateStoreLogoUrl(StoreNameLogoUrl storeNameLogoUrl);
}