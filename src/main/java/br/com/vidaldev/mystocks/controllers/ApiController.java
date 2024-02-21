package br.com.vidaldev.mystocks.controllers;

import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.AvailableResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteListResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteResultItemDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.interfaces.IApiConsumer;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "bearer-key")
public class ApiController {

    @Autowired
    IApiConsumer consumer;

    @GetMapping("/stock/ticker/{ticker}")
    public QuoteResultItemDto getStockByTicker(@PathVariable String ticker){
        return consumer.getStockByTicker(ticker);
    }

    @GetMapping("/stock/availables")
    public AvailableResultDto getStocksAvailables(){
        return consumer.getStocksAvailables();
    }

    @GetMapping("/stock/top10")
    public QuoteListResultDto get10StocksMostMarketCap(){
        return consumer.get10StocksMostMarketCap();
    }

    @GetMapping("/stock/sector/{sector}")
    public QuoteListResultDto getStocksBySector(@PathVariable String sector){
        return consumer.getStocksBySector(sector);
    }

}
