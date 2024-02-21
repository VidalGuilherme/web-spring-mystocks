package br.com.vidaldev.mystocks.modules.apiconsumer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteListStockDto(
     String stock,
     String name,
     double close,
     double change,
     int volume,
     double market_cap,
     String logo,
     String sector,
     String type
) {
}
