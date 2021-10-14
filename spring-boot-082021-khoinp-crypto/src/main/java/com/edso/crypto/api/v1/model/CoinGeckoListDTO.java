package com.edso.crypto.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CoinGeckoListDTO {
    private List<CoinGeckoDTO> list;

    public List<CoinGeckoDTO> getList() {
        return list;
    }
}
