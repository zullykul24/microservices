package com.edso.crypto.api.v1.model;

import com.edso.crypto.domain.Crypto;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CryptoRateHistory.class)
public abstract class CryptoRateHistory_ {

	public static volatile SingularAttribute<CryptoRateHistory, Timestamp> savedTime;
	public static volatile SingularAttribute<CryptoRateHistory, Long> id;
	public static volatile SingularAttribute<CryptoRateHistory, Double> current_price;
	public static volatile SingularAttribute<CryptoRateHistory, Crypto> crypto;

	public static final String SAVED_TIME = "savedTime";
	public static final String ID = "id";
	public static final String CURRENT_PRICE = "current_price";
	public static final String CRYPTO = "crypto";

}

