package com.edso.crypto.services;

import com.edso.crypto.api.v1.converters.CryptoDTOToCrypto;
import com.edso.crypto.api.v1.model.CoinGeckoDTO;
import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.api.v1.model.CryptoRateHistory;
import com.edso.crypto.repositories.CryptoRateHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EnableScheduling
@Service
public class CoinGeckoServiceImpl implements CoinGeckoService {
    private final RestTemplate restTemplate;
    private final CryptoService cryptoService;
    private final CryptoRateHistoryRepository cryptoRateHistoryRepository;
    private static final int EXPIRE_TIME_BY_SECOND = 3600; //3600s
    private final CryptoDTOToCrypto cryptoDTOToCrypto;


    private static final String GET_BY_IDS_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=";

    public CoinGeckoServiceImpl(RestTemplate restTemplate, CryptoService cryptoService,
                                CryptoRateHistoryRepository cryptoRateHistoryRepository,
                                CryptoDTOToCrypto cryptoDTOToCrypto) {
        this.restTemplate = restTemplate;
        this.cryptoService = cryptoService;
        this.cryptoRateHistoryRepository = cryptoRateHistoryRepository;
        this.cryptoDTOToCrypto = cryptoDTOToCrypto;
    }

    @Override
    public Double getCurrentPriceVsUsd(String symbol) {
        String id = cryptoService.getCryptoBySymbol(symbol).getCoinGeckoId();
        CoinGeckoDTO[] list = restTemplate
                .getForObject(GET_BY_IDS_URL + id,
                        CoinGeckoDTO[].class);
        if(list.length == 0) throw new ResourceNotFoundException();

        return list[0].getCurrent_price();
    }

    @Override
    public Double getCurrentPriceVsUsdAtSpecificTime(String symbol, Long timeAt) {
        Double price =  cryptoRateHistoryRepository.getCurrentPriceVsUsdByTime(symbol, timeAt);
        if(price == null) throw new ResourceNotFoundException();
        return price;
    }

    @Override
    public CoinGeckoDTO[] getAllCoins() {
        List<String> idList = cryptoService.getAllCoinGeckoIds();
        String ids = "";
        for(String id:idList){ //separate ids with commas
            ids = ids + id + ",";
        }

        //remove last character
        //remove last "," here
        ids = StringUtils.chop(ids);

        CoinGeckoDTO[] list = restTemplate.getForObject(GET_BY_IDS_URL + ids, CoinGeckoDTO[].class);
        if(list.length == 0) throw new ResourceNotFoundException();



        return list;

    }

    @Scheduled(fixedDelay = 60000)
    @Override
    public List<CryptoRateHistory> saveAllHistory() {
        CoinGeckoDTO[] coins = getAllCoins();

        List<CryptoRateHistory> list = Arrays.stream(coins)
                .map(coinGeckoDTO -> {
                    CryptoDTO cryptoDTO = cryptoService.getCryptoBySymbol(coinGeckoDTO.getSymbol());
                        return new CryptoRateHistory(
                                cryptoDTOToCrypto.convert(cryptoDTO),
                                coinGeckoDTO.getCurrent_price(),
                                new Timestamp(System.currentTimeMillis()));
                })
                .collect(Collectors.toList());

        return cryptoRateHistoryRepository.saveAll(list);
    }

    @Override
    public Long getToAmount(String fromSymbol, String toSymbol, Long fromAmount) {
        String fromId = cryptoService.getCryptoBySymbol(fromSymbol).getCoinGeckoId();
        String toId = cryptoService.getCryptoBySymbol(toSymbol).getCoinGeckoId();
        CoinGeckoDTO[] list = restTemplate
                .getForObject(GET_BY_IDS_URL + fromId + "," + toId, CoinGeckoDTO[].class);
        if(list.length == 0) throw new ResourceNotFoundException();

        String firstIdReturned = list[0].getId();

        boolean isOrdered = fromId.equalsIgnoreCase(firstIdReturned);


        Double firstCurrentPriceReturned = list[0].getCurrent_price();
        Double secondCurrentPriceReturned = list[1].getCurrent_price();

        Double toAmount;

        if(isOrdered){
            //check if first
            toAmount = fromAmount * firstCurrentPriceReturned / secondCurrentPriceReturned;
        } else {
            toAmount = fromAmount * secondCurrentPriceReturned / firstCurrentPriceReturned;
        }

        return toAmount.longValue();
    }

    @Scheduled(fixedDelay = 60000)
    @Override
    public void deleteOldRecords() {
        cryptoRateHistoryRepository.deleteOldHistoryRecords(EXPIRE_TIME_BY_SECOND);
    }
}
