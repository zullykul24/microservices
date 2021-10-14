package com.edso.crypto.domain;

import com.edso.crypto.api.v1.model.CryptoRateHistory;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isDeleted;
    private boolean whitelistCollateral;
    private boolean whitelistSupply;
    private String name;
    private String coinGeckoId;

    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL)
    private List<CryptoRateHistory> historyList;



}
