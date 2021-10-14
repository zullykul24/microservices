package com.edso.crypto.boostrap;

import com.edso.crypto.domain.Crypto;
import com.edso.crypto.repositories.CryptoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Boostrap implements CommandLineRunner {
    private final CryptoRepository cryptoRepository;

    public Boostrap(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCryptos();
    }

    private void loadCryptos() {
        Crypto xrp = new Crypto();
        xrp.setId(6L);
        xrp.setAddress("0x11xrp");
        xrp.setSymbol("xrp");
        xrp.setName("xrp");
        xrp.setCoinGeckoId("ripple");

        Crypto eth = new Crypto();
        eth.setId(3L);
        eth.setAddress("0x11eth");
        eth.setSymbol("eth");
        eth.setName("Ethereum");
        eth.setCoinGeckoId("ethereum");

        cryptoRepository.save(xrp);
        cryptoRepository.save(eth);
    }
}
