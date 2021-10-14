package com.edso.crypto.services;

import com.edso.crypto.api.v1.model.CoinGeckoDTO;
import com.edso.crypto.api.v1.model.CoinGeckoListDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinGeckoServiceImplTest {
    @Autowired
    CoinGeckoService coinGeckoService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getCurrentPriceVsUsd() {
        Double currentPriceVsUsd = coinGeckoService.getCurrentPriceVsUsd("btc");
        assertTrue(currentPriceVsUsd > 40000);
    }
}