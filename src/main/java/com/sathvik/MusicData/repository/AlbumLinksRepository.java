package com.sathvik.MusicData.repository;

import com.sathvik.MusicData.entity.AlbumLinks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlbumLinksRepository extends MongoRepository<AlbumLinks, String> {
    Optional<AlbumLinks> findByAlbumName(String albumName);
}