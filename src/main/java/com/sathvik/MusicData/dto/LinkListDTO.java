package com.sathvik.MusicData.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static com.sathvik.MusicData.constants.ValidationMessages.LINKS_EMPTY;
import static com.sathvik.MusicData.constants.ValidationMessages.LINKS_MANDATORY;

@Data
public class LinkListDTO {
    @NotNull(message = LINKS_MANDATORY)
    @NotEmpty(message = LINKS_EMPTY)
    @Valid
    private List<LinkDTO> links;
}
