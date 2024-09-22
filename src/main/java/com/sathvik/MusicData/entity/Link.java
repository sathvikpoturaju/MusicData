package com.sathvik.MusicData.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Link {
    @Field(name = "storeName")
    private String storeName;

    @Field(name = "storeLogoUrl")
    private String storeLogoUrl;

    @Field(name = "albumUrl")
    private String albumUrl;

    @Field(name = "artistUrl")
    private String artistUrl;
}