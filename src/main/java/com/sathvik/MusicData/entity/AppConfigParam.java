package com.sathvik.MusicData.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "app_config_param")
public class AppConfigParam {
    @Id
    private String id;

    @Field(name = "key")
    private String key;

    @Field(name = "value")
    private String value;
}
