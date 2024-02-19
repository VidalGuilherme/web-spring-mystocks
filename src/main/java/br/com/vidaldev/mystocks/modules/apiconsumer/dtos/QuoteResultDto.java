package br.com.vidaldev.mystocks.modules.apiconsumer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteResultDto(
        List<QuoteResultItemDto> results
) {
}
