package br.com.vidaldev.mystocks.domain.apiconsumer.interfaces;

import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.AvailableResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteListResultDto;
import br.com.vidaldev.mystocks.domain.apiconsumer.dtos.QuoteResultItemDto;

public interface IApiConsumer {
    QuoteResultItemDto getStockByTicker(String ticker);
    AvailableResultDto getStocksAvailables();
    QuoteListResultDto get10StocksMostMarketCap();
    QuoteListResultDto getStocksBySector(String sector);
}
