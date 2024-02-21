package br.com.vidaldev.mystocks.domain.apiconsumer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AvailableResultDto(
        List<String> stocks
) {
}
