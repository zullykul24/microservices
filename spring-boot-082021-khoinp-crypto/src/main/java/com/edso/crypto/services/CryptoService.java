package com.edso.crypto.services;

import com.edso.crypto.api.v1.model.CryptoDTO;


import java.util.List;

public interface CryptoService {
    List<CryptoDTO> getAllCryptos(Long page);

    CryptoDTO getCryptoBySymbol(String symbol);

    List<CryptoDTO> getCryptoByAddress(String address);

    List<CryptoDTO> getCryptoByName(String name);

    List<CryptoDTO> getCryptoBySymbolAddressName(String symbol, String address,
                                                 String name, Long page);

    CryptoDTO getCryptoById(Long id);

    CryptoDTO createNewCrypto(CryptoDTO cryptoDTO);

    CryptoDTO saveCryptoByDTO(Long id, CryptoDTO cryptoDTO);

    CryptoDTO deleteCryptoById(Long id);

    List<String> getAllCoinGeckoIds();

    //

}
