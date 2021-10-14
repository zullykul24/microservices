package com.edso.crypto.services;

import com.edso.crypto.api.v1.model.CoinGeckoDTO;
import com.edso.crypto.api.v1.model.CoinGeckoListDTO;
import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.api.v1.model.CryptoRateHistory;

import java.util.List;

public interface CoinGeckoService {
    Double getCurrentPriceVsUsd(String symbol);

    Double getCurrentPriceVsUsdAtSpecificTime(String symbol, Long timeAt);

    CoinGeckoDTO[] getAllCoins();

    List<CryptoRateHistory> saveAllHistory();

    Long getToAmount(String fromSymbol, String toSymbol, Long fromAmount);

    void deleteOldRecords();
}
