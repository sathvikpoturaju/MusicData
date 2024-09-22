package com.sathvik.MusicData.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.sathvik.MusicData.constants.ValidationMessages.STORE_LOGO_URL_MANDATORY;
import static com.sathvik.MusicData.constants.ValidationMessages.STORE_NAME_MANDATORY;

@Data
public class StoreNameLogoUrl {
    @NotNull(message = STORE_NAME_MANDATORY)
    private String storeName;
    @NotNull(message = STORE_LOGO_URL_MANDATORY)
    private String storeLogoUrl;
}