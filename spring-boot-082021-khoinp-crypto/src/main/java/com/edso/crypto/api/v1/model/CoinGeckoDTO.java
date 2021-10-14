package com.edso.crypto.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinGeckoDTO implements Serializable {
    private String id;
    private String symbol;
    private Double current_price;

    private final static long serialVersionUID = 270727596527329664L;
}
