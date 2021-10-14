package com.edso.crypto.api.v1.model;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDTO implements Comparable<CryptoDTO>{

    @Hidden
    private Long id;

    @NotBlank
    private String symbol;

    @NotBlank
    private String address;

    @Hidden
    private Timestamp createdAt = null;

    @Hidden
    private Timestamp updatedAt = null;


    private boolean isDeleted = false;
    private boolean whitelistCollateral = false;
    private boolean whitelistSupply = false;

    @Nullable
    private String name;

    @NotBlank
    private String coinGeckoId;

    @Hidden
    private String cryptoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CryptoDTO cryptoDTO = (CryptoDTO) o;

        return symbol != null ? symbol.equals(cryptoDTO.symbol) : cryptoDTO.symbol == null;
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }

    @Override
    public int compareTo(CryptoDTO o) {
        return  this.symbol.compareTo(o.getSymbol());
    }
}
