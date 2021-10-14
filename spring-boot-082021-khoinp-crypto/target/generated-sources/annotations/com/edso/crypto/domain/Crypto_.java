package com.edso.crypto.domain;

import com.edso.crypto.api.v1.model.CryptoRateHistory;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Crypto.class)
public abstract class Crypto_ {

	public static volatile SingularAttribute<Crypto, String> symbol;
	public static volatile SingularAttribute<Crypto, Timestamp> createdAt;
	public static volatile SingularAttribute<Crypto, String> address;
	public static volatile SingularAttribute<Crypto, Boolean> isDeleted;
	public static volatile ListAttribute<Crypto, CryptoRateHistory> historyList;
	public static volatile SingularAttribute<Crypto, String> coinGeckoId;
	public static volatile SingularAttribute<Crypto, Boolean> whitelistCollateral;
	public static volatile SingularAttribute<Crypto, Boolean> whitelistSupply;
	public static volatile SingularAttribute<Crypto, String> name;
	public static volatile SingularAttribute<Crypto, Long> id;
	public static volatile SingularAttribute<Crypto, Timestamp> updatedAt;

	public static final String SYMBOL = "symbol";
	public static final String CREATED_AT = "createdAt";
	public static final String ADDRESS = "address";
	public static final String IS_DELETED = "isDeleted";
	public static final String HISTORY_LIST = "historyList";
	public static final String COIN_GECKO_ID = "coinGeckoId";
	public static final String WHITELIST_COLLATERAL = "whitelistCollateral";
	public static final String WHITELIST_SUPPLY = "whitelistSupply";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String UPDATED_AT = "updatedAt";

}

