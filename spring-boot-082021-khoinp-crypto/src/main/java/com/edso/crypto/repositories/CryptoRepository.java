package com.edso.crypto.repositories;

import com.edso.crypto.domain.Crypto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long>, JpaSpecificationExecutor<Crypto>,
        PagingAndSortingRepository<Crypto, Long> {

    Page<Crypto> findAll(Pageable pageable);

    @Override
    Page<Crypto> findAll(Specification<Crypto> spec, Pageable pageable);
}
