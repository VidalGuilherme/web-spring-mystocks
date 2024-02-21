package br.com.vidaldev.mystocks.domain.apiconsumer.services;

import br.com.vidaldev.mystocks.domain.apiconsumer.interfaces.IApiConsumer;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.AvailableResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteListResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteResultItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BrapiApiConsumer implements IApiConsumer {

    @Value(value = "${brapiapi.apikey}")
    private String API_KEY;

    @Value(value = "${brapiapi.url}")
    private String BASE_URL;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public QuoteResultItemDto getStockByTicker(String ticker) {
        String json = getData(BASE_URL+"/quote/"+ticker);
        try {
            QuoteResultDto resultDto = mapper.readValue(json, QuoteResultDto.class);

            return resultDto.results().getFirst();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public AvailableResultDto getStocksAvailables() {
        String json = getData(BASE_URL+"/available");
        try {
            return mapper.readValue(json, AvailableResultDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public QuoteListResultDto get10StocksMostMarketCap() {
        String json = getData(BASE_URL+"/quote/list?sortBy=market_cap_basic&sortOrder=desc&type=stock&limit=10");
        try {
            return mapper.readValue(json, QuoteListResultDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public QuoteListResultDto getStocksBySector(String sector) {
        String json = getData(BASE_URL+"/quote/list?sector="+sector);
        try {
            return mapper.readValue(json, QuoteListResultDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getData(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+API_KEY)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == HttpStatus.NOT_FOUND.value()){
                throw new EntityNotFoundException("Api consumer not found exception");
            }

            if(response.statusCode() == HttpStatus.BAD_REQUEST.value()){
                throw new EntityNotFoundException("Api consumer bad request");
            }

            if(response.statusCode() != HttpStatus.OK.value()){
                throw new RuntimeException("Api consumer error");
            }

        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        return response.body();
    }

}
