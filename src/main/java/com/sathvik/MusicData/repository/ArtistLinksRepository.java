package com.sathvik.MusicData.repository;

import com.sathvik.MusicData.entity.ArtistLinks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArtistLinksRepository extends MongoRepository<ArtistLinks, String> {
    Optional<ArtistLinks> findByArtistName(String artistName);
}