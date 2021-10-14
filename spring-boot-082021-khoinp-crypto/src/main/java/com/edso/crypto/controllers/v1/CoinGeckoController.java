package com.edso.crypto.controllers.v1;

import com.edso.crypto.api.v1.model.CoinGeckoDTO;
import com.edso.crypto.api.v1.model.CoinGeckoListDTO;
import com.edso.crypto.api.v1.model.CryptoRateHistory;
import com.edso.crypto.services.CoinGeckoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("coin_gecko")
public class CoinGeckoController {
    private final CoinGeckoService coinGeckoService;

    public CoinGeckoController(CoinGeckoService coinGeckoService) {
        this.coinGeckoService = coinGeckoService;
    }

    @Operation(summary = "Get current price versus USD of a currency by symbol")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Double getCurrentPriceVsUsd(@RequestParam String symbol){
        return coinGeckoService.getCurrentPriceVsUsd(symbol);
    }

    @Operation(summary = "Exchange from an amount of a currency to another -  by currencies' symbols")
    @GetMapping("/exchange")
    @ResponseStatus(HttpStatus.OK)
    public Long getCurrentPriceVsUsdOfTwoCoins(@RequestParam String fromSymbol,
                                               @RequestParam String toSymbol,
                                               @RequestParam Long fromAmount){
        return coinGeckoService.getToAmount(fromSymbol, toSymbol, fromAmount);
    }

    @Operation(summary = "Save all current_prices to db")
    @GetMapping("/saveHistory")
    @ResponseStatus(HttpStatus.OK)
    public List<CryptoRateHistory> saveHistory(){
        return coinGeckoService.saveAllHistory();
    }

    @Operation(summary = "get all coins")
    @GetMapping("/getAllCoins")
    @ResponseStatus(HttpStatus.OK)
    public CoinGeckoDTO[] getAllCoins(){
        return coinGeckoService.getAllCoins();
    }


    @Operation(summary = "Get price versus USD of a currency by symbol at a specific timestamp")
    @GetMapping("/getPriceAtSpecificTime")
    @ResponseStatus(HttpStatus.OK)
    public Double getPriceVsUsdAtSpecificTime(@RequestParam String symbol,
                                              @RequestParam Long timeAt){
        return coinGeckoService.getCurrentPriceVsUsdAtSpecificTime(symbol, timeAt);
    }

}
