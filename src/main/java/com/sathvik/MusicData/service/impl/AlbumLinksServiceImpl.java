package com.sathvik.MusicData.service.impl;

import com.sathvik.MusicData.dto.AlbumLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.dto.StoreNameLogoUrl;
import com.sathvik.MusicData.entity.AlbumLinks;
import com.sathvik.MusicData.enums.UrlType;
import com.sathvik.MusicData.exception.MusicDataException;
import com.sathvik.MusicData.mapper.AlbumLinksMapper;
import com.sathvik.MusicData.mapper.LinkMapper;
import com.sathvik.MusicData.repository.AlbumLinksRepository;
import com.sathvik.MusicData.service.AlbumLinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.sathvik.MusicData.constants.ResponseMessages.*;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AlbumLinksServiceImpl implements AlbumLinksService {
    private AlbumLinksRepository albumLinksRepository;
    private AlbumLinksMapper albumLinksMapper;
    private LinkMapper linkMapper;
    private MongoTemplate mongoTemplate;

    @Override
    public AlbumLinksDTO saveAlbumLinksForSong(AlbumLinksDTO albumLinksDTO) throws MusicDataException {
        Optional<AlbumLinks> albumLinksOpt = this.albumLinksRepository.findByAlbumName(albumLinksDTO.getAlbumName());

        if (albumLinksOpt.isPresent()) {
            throw new MusicDataException(ALBUM_NAME_EXISTS);
        }

        albumLinksDTO
                .getLinks()
                .forEach(link -> link.initUrl(UrlType.ALBUM_URL));
        AlbumLinks albumLinks = this.albumLinksMapper.toEntity(albumLinksDTO);
        albumLinks.setId(null);
        albumLinks = this.albumLinksRepository.save(albumLinks);

        return this.albumLinksMapper.toDTO(albumLinks);
    }

    @Override
    public AlbumLinksDTO getAlbumLinksForSongById(String id) throws MusicDataException {
        AlbumLinks albumLinks = this.albumLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ALBUM_ID_NOT_FOUND));

        return this.albumLinksMapper.toDTO(albumLinks);
    }

    @Override
    public List<AlbumLinksDTO> getAlbumLinksForAllSongs() throws MusicDataException {
        List<AlbumLinks> albumLinksList = this.albumLinksRepository.findAll();

        if (albumLinksList.isEmpty()) {
            throw new MusicDataException(NO_ALBUMS_FOUND);
        }

        return albumLinksList
                .stream()
                .map(albumLinksMapper::toDTO)
                .toList();
    }

    @Override
    public String updateAlbumLinksForSongById(String id, AlbumLinksDTO albumLinksDTO) throws MusicDataException {
        if (!this.albumLinksRepository.existsById(id)) {
            throw new MusicDataException(ALBUM_ID_NOT_FOUND);
        }

        albumLinksDTO
                .getLinks()
                .forEach(link -> link.initUrl(UrlType.ALBUM_URL));
        AlbumLinks albumLinks = this.albumLinksMapper.toEntity(albumLinksDTO);
        albumLinks.setId(id);
        this.albumLinksRepository.save(albumLinks);

        return ALBUM_LINKS_UPDATE_SUCCESS;
    }

    @Override
    public String addAlbumLinkForSongById(String id, LinkListDTO linkListDTO) throws MusicDataException {
        AlbumLinks albumLinks = this.albumLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ALBUM_ID_NOT_FOUND));
        albumLinks
                .getLinks()
                .addAll(
                        linkListDTO
                                .getLinks()
                                .stream()
                                .peek(link -> link.initUrl(UrlType.ALBUM_URL))
                                .map(linkMapper::toEntity)
                                .toList()
                );

        this.albumLinksRepository.save(albumLinks);
        return ALBUM_LINKS_UPDATE_SUCCESS;
    }

    @Override
    public String deleteAlbumLinksForSongById(String id) throws MusicDataException {
        AlbumLinks albumLinks = this.albumLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ALBUM_ID_NOT_FOUND));

        this.albumLinksRepository.delete(albumLinks);
        return ALBUM_LINKS_DELETE_SUCCESS;
    }

    @Override
    public String updateStoreLogoUrl(StoreNameLogoUrl storeNameLogoUrl) {
        List<AlbumLinks> albumLinksList = albumLinksRepository.findAll();

        albumLinksList
                .stream()
                .filter(albumLinks -> Objects.nonNull(albumLinks.getLinks()))
                .forEach(albumLinks -> {
                    albumLinks
                            .getLinks()
                            .forEach(link -> {
                                if (link.getStoreName().equals(storeNameLogoUrl.getStoreName())) {
                                    link.setStoreLogoUrl(storeNameLogoUrl.getStoreLogoUrl());
                                }
                            });
                });

        albumLinksRepository.saveAll(albumLinksList);
        return ALBUM_LINKS_UPDATE_SUCCESS;
    }

    @Override
    public AlbumLinksDTO getAlbumLinksForSongByAlbumName(String albumName) throws MusicDataException {
        AlbumLinks albumLinks = this.albumLinksRepository
                .findByAlbumName(albumName)
                .orElseThrow(() -> new MusicDataException(ALBUM_NAME_NOT_FOUND));
        return this.albumLinksMapper.toDTO(albumLinks);
    }
}
