package com.sathvik.MusicData.mapper;

import com.sathvik.MusicData.dto.AlbumLinksDTO;
import com.sathvik.MusicData.dto.LinkDTO;
import com.sathvik.MusicData.entity.AlbumLinks;
import com.sathvik.MusicData.entity.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AlbumLinksMapper {
    private LinkMapper linkMapper;

    public AlbumLinksDTO toDTO(AlbumLinks albumLinks) {
        if (Objects.isNull(albumLinks)) {
            return null;
        }

        AlbumLinksDTO albumLinksDTO = new AlbumLinksDTO();
        albumLinksDTO.setId(albumLinks.getId());
        albumLinksDTO.setAlbumName(albumLinks.getAlbumName());
        albumLinksDTO.setCoverArtUrl(albumLinks.getCoverArtUrl());
        albumLinksDTO.setArtistName(albumLinks.getArtistName());

        if (Objects.nonNull(albumLinks.getLinks())) {
            List<LinkDTO> linkDTOs = albumLinks
                    .getLinks()
                    .stream()
                    .map(linkMapper::toDTO)
                    .collect(Collectors.toList());
            albumLinksDTO.setLinks(linkDTOs);
        }

        return albumLinksDTO;
    }

    public AlbumLinks toEntity(AlbumLinksDTO albumLinksDTO) {
        if (Objects.isNull(albumLinksDTO)) {
            return null;
        }

        AlbumLinks albumLinks = new AlbumLinks();
        albumLinks.setId(albumLinksDTO.getId());
        albumLinks.setAlbumName(albumLinksDTO.getAlbumName());
        albumLinks.setCoverArtUrl(albumLinksDTO.getCoverArtUrl());
        albumLinks.setArtistName(albumLinksDTO.getArtistName());

        if (Objects.nonNull(albumLinksDTO.getLinks())) {
            List<Link> links = albumLinksDTO
                    .getLinks()
                    .stream()
                    .map(linkMapper::toEntity)
                    .collect(Collectors.toList());
            albumLinks.setLinks(links);
        }

        return albumLinks;
    }
}