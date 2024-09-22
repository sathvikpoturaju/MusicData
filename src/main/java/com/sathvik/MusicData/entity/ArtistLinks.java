package com.sathvik.MusicData.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "artist_links")
public class ArtistLinks {
    @Id
    private String id;

    @Field(name = "artistName")
    private String artistName;

    @Field(name = "description")
    private String description;

    @Field(name = "profilePictureUrl")
    private String profilePictureUrl;

    @Field(name = "bannerUrl")
    private String bannerUrl;

    @Field(name = "links")
    private List<Link> links;

    @CreatedDate
    @Field(name = "creationDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Field(name = "modifiedDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modifiedDate;
}