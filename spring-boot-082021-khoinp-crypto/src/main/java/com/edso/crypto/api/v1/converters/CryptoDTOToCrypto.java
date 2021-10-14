package com.edso.crypto.api.v1.converters;

import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.domain.Crypto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CryptoDTOToCrypto implements Converter<CryptoDTO, Crypto> {
    @Override
    public Crypto convert(CryptoDTO source) {
        if(source == null) return null;
        Crypto crypto = new Crypto();
        crypto.setId(source.getId());
        crypto.setSymbol(source.getSymbol());
        crypto.setAddress(source.getAddress());
        crypto.setCreatedAt(source.getCreatedAt());
        crypto.setUpdatedAt(source.getUpdatedAt());
        crypto.setDeleted(source.isDeleted());
        crypto.setWhitelistCollateral(source.isWhitelistCollateral());
        crypto.setWhitelistSupply(source.isWhitelistSupply());
        crypto.setName(source.getName());
        crypto.setCoinGeckoId(source.getCoinGeckoId());
        return crypto;
    }
}
