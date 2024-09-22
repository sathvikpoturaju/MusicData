package com.sathvik.MusicData.service.impl;

import com.sathvik.MusicData.dto.ArtistLinksDTO;
import com.sathvik.MusicData.dto.LinkListDTO;
import com.sathvik.MusicData.entity.ArtistLinks;
import com.sathvik.MusicData.enums.UrlType;
import com.sathvik.MusicData.exception.MusicDataException;
import com.sathvik.MusicData.mapper.ArtistLinksMapper;
import com.sathvik.MusicData.mapper.LinkMapper;
import com.sathvik.MusicData.repository.ArtistLinksRepository;
import com.sathvik.MusicData.service.ArtistLinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.sathvik.MusicData.constants.ResponseMessages.*;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ArtistLinksServiceImpl implements ArtistLinksService {
    private ArtistLinksRepository artistLinksRepository;
    private ArtistLinksMapper artistLinksMapper;
    private LinkMapper linkMapper;

    @Override
    public ArtistLinksDTO saveArtistLinks(ArtistLinksDTO artistLinksDTO) throws MusicDataException {
        Optional<ArtistLinks> artistLinksOpt = this.artistLinksRepository.findByArtistName(artistLinksDTO.getArtistName());

        if (artistLinksOpt.isPresent()) {
            throw new MusicDataException(ARTIST_NAME_EXISTS);
        }

        artistLinksDTO
                .getLinks()
                .forEach(link -> link.initUrl(UrlType.ARTIST_URL));
        ArtistLinks artistLinks = this.artistLinksMapper.toEntity(artistLinksDTO);
        artistLinks.setId(null);
        artistLinks = this.artistLinksRepository.save(artistLinks);

        return this.artistLinksMapper.toDTO(artistLinks);
    }

    @Override
    public ArtistLinksDTO getArtistLinksById(String id) throws MusicDataException {
        ArtistLinks artistLinks = this.artistLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ARTIST_ID_NOT_FOUND));

        return this.artistLinksMapper.toDTO(artistLinks);
    }

    @Override
    public List<ArtistLinksDTO> getArtistLinksForAllArtist() throws MusicDataException {
        List<ArtistLinks> artistLinksList = this.artistLinksRepository.findAll();

        if (artistLinksList.isEmpty()) {
            throw new MusicDataException(NO_ARTISTS_FOUND);
        }

        return artistLinksList
                .stream()
                .map(artistLinksMapper::toDTO)
                .toList();
    }

    @Override
    public String updateArtistLinksById(String id, ArtistLinksDTO artistLinksDTO) throws MusicDataException {
        if (!this.artistLinksRepository.existsById(id)) {
            throw new MusicDataException(ARTIST_ID_NOT_FOUND);
        }

        artistLinksDTO
                .getLinks()
                .forEach(link -> link.initUrl(UrlType.ARTIST_URL));
        ArtistLinks artistLinks = this.artistLinksMapper.toEntity(artistLinksDTO);
        artistLinks.setId(id);
        this.artistLinksRepository.save(artistLinks);

        return ARTIST_LINKS_UPDATE_SUCCESS;
    }

    @Override
    public String addArtistLinkById(String id, LinkListDTO linkListDTO) throws MusicDataException {
        ArtistLinks artistLinks = this.artistLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ARTIST_ID_NOT_FOUND));
        artistLinks
                .getLinks()
                .addAll(
                        linkListDTO
                                .getLinks()
                                .stream()
                                .peek(link -> link.initUrl(UrlType.ARTIST_URL))
                                .map(linkMapper::toEntity)
                                .toList()
                );

        this.artistLinksRepository.save(artistLinks);
        return ARTIST_LINKS_UPDATE_SUCCESS;
    }

    @Override
    public String deleteArtistLinksById(String id) throws MusicDataException {
        ArtistLinks artistLinks = this.artistLinksRepository
                .findById(id)
                .orElseThrow(() -> new MusicDataException(ARTIST_ID_NOT_FOUND));

        this.artistLinksRepository.delete(artistLinks);
        return ARTIST_LINKS_DELETE_SUCCESS;
    }
}