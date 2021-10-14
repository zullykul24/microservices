package com.edso.crypto.specifications;

import com.edso.crypto.domain.Crypto;
import com.edso.crypto.domain.Crypto_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public final class CryptoSpecification {

    /**
     * Lấy ra user có id chỉ định
     * @param
     * @return
     */
    public static Specification<Crypto> hasId(long id) {
        return (root, query, cb) -> cb.equal(root.get(Crypto_.ID), id);
    }


    public static Specification<Crypto> hasSymbol(String symbol) {
        return (root, query, cb) -> cb.like(root.get(Crypto_.SYMBOL), "%"+symbol+"%");
    }

    public static Specification<Crypto> hasAddress(String address) {
        return (root, query, cb) -> cb.like(root.get(Crypto_.ADDRESS), "%"+address+"%");
    }

    public static Specification<Crypto> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get(Crypto_.NAME), "%"+name+"%");
    }
}