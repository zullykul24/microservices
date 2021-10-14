package com.edso.crypto.api.v1.converters;

import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.domain.Crypto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CryptoToCryptoDTO implements Converter<Crypto, CryptoDTO> {
    @Override
    public CryptoDTO convert(Crypto source) {
        if(source == null) return null;
        CryptoDTO cryptoDTO = new CryptoDTO();
        cryptoDTO.setId(source.getId());
        cryptoDTO.setSymbol(source.getSymbol());
        cryptoDTO.setAddress(source.getAddress());
        cryptoDTO.setCreatedAt(source.getCreatedAt());
        cryptoDTO.setUpdatedAt(source.getUpdatedAt());
        cryptoDTO.setDeleted(source.isDeleted());
        cryptoDTO.setWhitelistCollateral(source.isWhitelistCollateral());
        cryptoDTO.setWhitelistSupply(source.isWhitelistSupply());
        cryptoDTO.setName(source.getName());
        cryptoDTO.setCoinGeckoId(source.getCoinGeckoId());
        return cryptoDTO;
    }
}
