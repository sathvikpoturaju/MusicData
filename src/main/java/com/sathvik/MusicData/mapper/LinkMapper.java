package com.sathvik.MusicData.mapper;

import com.sathvik.MusicData.dto.LinkDTO;
import com.sathvik.MusicData.entity.Link;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LinkMapper {
    public LinkDTO toDTO(Link link) {
        if (Objects.isNull(link)) {
            return null;
        }

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setStoreName(link.getStoreName());
        linkDTO.setStoreLogoUrl(link.getStoreLogoUrl());
        linkDTO.setAlbumUrl(link.getAlbumUrl());
        linkDTO.setArtistUrl(link.getArtistUrl());

        return linkDTO;
    }

    public Link toEntity(LinkDTO linkDTO) {
        if (Objects.isNull(linkDTO)) {
            return null;
        }

        Link link = new Link();
        link.setStoreName(linkDTO.getStoreName());
        link.setStoreLogoUrl(linkDTO.getStoreLogoUrl());
        link.setAlbumUrl(linkDTO.getAlbumUrl());
        link.setArtistUrl(linkDTO.getArtistUrl());

        return link;
    }
}