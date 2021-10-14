package com.edso.crypto.repositories;

import com.edso.crypto.api.v1.model.CryptoRateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Repository
public interface CryptoRateHistoryRepository extends JpaRepository<CryptoRateHistory, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE from crypto_rate_history where TIMESTAMPDIFF(SECOND,saved_time,NOW()) > ?1",
            nativeQuery = true)
    void deleteOldHistoryRecords(int expireTimeBySecond);

    @Query(value = "SELECT current_price FROM crypto_rate_history inner join crypto " +
            "WHERE crypto.symbol = ?1 and UNIX_TIMESTAMP(saved_time) <= ?2 " +
            "ORDER BY saved_time desc limit 1",
            nativeQuery = true)
    Double getCurrentPriceVsUsdByTime(String symbol, Long timeAt);

}
