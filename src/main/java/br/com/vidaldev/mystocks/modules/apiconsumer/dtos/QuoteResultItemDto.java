package br.com.vidaldev.mystocks.modules.apiconsumer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteResultItemDto(
         String currency,
         double twoHundredDayAverage,
         double twoHundredDayAverageChange,
         double twoHundredDayAverageChangePercent,
         long marketCap,
         String shortName,
         String longName,
         double regularMarketChange,
         double regularMarketChangePercent,
         String regularMarketTime,
         double regularMarketPrice,
         double regularMarketDayHigh,
         String regularMarketDayRange,
         double regularMarketDayLow,
         int regularMarketVolume,
         double regularMarketPreviousClose,
         double regularMarketOpen,
         int averageDailyVolume3Month,
         int averageDailyVolume10Day,
         double fiftyTwoWeekLowChange,
         double fiftyTwoWeekLowChangePercent,
         String fiftyTwoWeekRange,
         double fiftyTwoWeekHighChange,
         double fiftyTwoWeekHighChangePercent,
         double fiftyTwoWeekLow,
         double fiftyTwoWeekHigh,
         String symbol,
         double priceEarnings,
         double earningsPerShare,
         String logourl
) {
}
