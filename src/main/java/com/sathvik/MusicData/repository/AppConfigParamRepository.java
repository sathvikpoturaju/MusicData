package com.sathvik.MusicData.repository;

import com.sathvik.MusicData.entity.AppConfigParam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppConfigParamRepository extends MongoRepository<AppConfigParam, String> {
}