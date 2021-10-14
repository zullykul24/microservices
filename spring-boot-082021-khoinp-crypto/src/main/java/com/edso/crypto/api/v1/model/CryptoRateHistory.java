package com.edso.crypto.api.v1.model;

import com.edso.crypto.domain.Crypto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crypto_rate_history")
@Entity
public class CryptoRateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    private Double current_price;
    private Timestamp savedTime;

    public CryptoRateHistory(Crypto crypto, Double current_price, Timestamp savedTime){
        this.crypto = crypto;
        this.current_price = current_price;
        this.savedTime = savedTime;
    }
}
