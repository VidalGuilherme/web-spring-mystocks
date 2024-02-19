package br.com.vidaldev.mystocks.modules.apiconsumer.interfaces;

import br.com.vidaldev.mystocks.modules.apiconsumer.dtos.AvailableResultDto;
import br.com.vidaldev.mystocks.modules.apiconsumer.dtos.QuoteListResultDto;
import br.com.vidaldev.mystocks.modules.apiconsumer.dtos.QuoteResultItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IApiConsumer {
    QuoteResultItemDto getStockByTicker(String ticker);
    AvailableResultDto getStocksAvailables();
    QuoteListResultDto get10StocksMostMarketCap();
    QuoteListResultDto getStocksBySector(String sector);
}
