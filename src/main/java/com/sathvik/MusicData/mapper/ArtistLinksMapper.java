package com.sathvik.MusicData.mapper;

import com.sathvik.MusicData.dto.ArtistLinksDTO;
import com.sathvik.MusicData.dto.LinkDTO;
import com.sathvik.MusicData.entity.ArtistLinks;
import com.sathvik.MusicData.entity.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ArtistLinksMapper {
    private LinkMapper linkMapper;

    public ArtistLinksDTO toDTO(ArtistLinks artistLinks) {
        if (Objects.isNull(artistLinks)) {
            return null;
        }

        ArtistLinksDTO artistLinksDTO = new ArtistLinksDTO();
        artistLinksDTO.setId(artistLinks.getId());
        artistLinksDTO.setArtistName(artistLinks.getArtistName());
        artistLinksDTO.setDescription(artistLinks.getDescription());
        artistLinksDTO.setProfilePictureUrl(artistLinks.getProfilePictureUrl());
        artistLinksDTO.setBannerUrl(artistLinks.getBannerUrl());

        if (Objects.nonNull(artistLinks.getLinks())) {
            List<LinkDTO> linkDTOs = artistLinks
                    .getLinks()
                    .stream()
                    .map(linkMapper::toDTO)
                    .collect(Collectors.toList());
            artistLinksDTO.setLinks(linkDTOs);
        }

        return artistLinksDTO;
    }

    public ArtistLinks toEntity(ArtistLinksDTO artistLinksDTO) {
        if (Objects.isNull(artistLinksDTO)) {
            return null;
        }

        ArtistLinks artistLinks = new ArtistLinks();
        artistLinks.setId(artistLinksDTO.getId());
        artistLinks.setArtistName(artistLinksDTO.getArtistName());
        artistLinks.setDescription(artistLinksDTO.getDescription());
        artistLinks.setProfilePictureUrl(artistLinksDTO.getProfilePictureUrl());
        artistLinks.setBannerUrl(artistLinksDTO.getBannerUrl());

        if (Objects.nonNull(artistLinksDTO.getLinks())) {
            List<Link> links = artistLinksDTO
                    .getLinks()
                    .stream()
                    .map(linkMapper::toEntity)
                    .collect(Collectors.toList());
            artistLinks.setLinks(links);
        }

        return artistLinks;
    }
}