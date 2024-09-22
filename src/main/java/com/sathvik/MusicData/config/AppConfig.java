package com.sathvik.MusicData.config;

import com.sathvik.MusicData.repository.AppConfigParamRepository;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppConfig {
    private final AppConfigParamRepository appConfigParamRepository;
    private final Map<String, String> appCoonfigMap;

    public AppConfig(AppConfigParamRepository appConfigParamRepository) {
        this.appConfigParamRepository = appConfigParamRepository;
        this.appCoonfigMap = this.initSystemParamMap();
    }

    private Map<String, String> initSystemParamMap() {
        return this.appConfigParamRepository
                .findAll()
                .stream()
                .filter(acp ->
                        Objects.nonNull(acp) &&
                        Objects.nonNull(acp.getKey()) &&
                        Objects.nonNull(acp.getValue()) &&
                        !acp.getKey().isBlank() &&
                        !acp.getValue().isBlank()
                )
                .collect(Collectors.toMap(
                        acp -> acp.getKey().trim(),
                        acp -> acp.getValue().trim(),
                        (a, b) -> a
                ));
    }

    public Map<String, String> getAppCoonfigMap() {
        return this.appCoonfigMap;
    }
}