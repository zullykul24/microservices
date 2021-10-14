package com.edso.crypto.services;

import com.edso.crypto.api.v1.converters.CryptoDTOToCrypto;
import com.edso.crypto.api.v1.converters.CryptoToCryptoDTO;
import com.edso.crypto.api.v1.model.CoinGeckoDTO;
import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.controllers.v1.CryptoController;
import com.edso.crypto.domain.Crypto;
import com.edso.crypto.repositories.CryptoRepository;
import com.edso.crypto.specifications.CryptoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CryptoServiceImpl implements CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoToCryptoDTO cryptoToCryptoDTO;
    private final CryptoDTOToCrypto cryptoDTOToCrypto;


    public CryptoServiceImpl(CryptoRepository cryptoRepository, CryptoToCryptoDTO cryptoToCryptoDTO,
                             CryptoDTOToCrypto cryptoDTOToCrypto) {
        this.cryptoRepository = cryptoRepository;
        this.cryptoToCryptoDTO = cryptoToCryptoDTO;
        this.cryptoDTOToCrypto = cryptoDTOToCrypto;
    }

    @Override
    public List<CryptoDTO> getAllCryptos(Long page) {
        return cryptoRepository.findAll(PageRequest.of(Math.toIntExact(page - 1),5, Sort.by("symbol")))
                .stream().map(cryptoToCryptoDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CryptoDTO getCryptoBySymbol(String symbol) {
        //System.out.println("symbol: " + symbol);
        return cryptoRepository
                .findAll()
                .stream()
                .filter(crypto -> crypto.getSymbol().equalsIgnoreCase(symbol))
                .map(cryptoToCryptoDTO::convert)
                .findAny().orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public List<CryptoDTO> getCryptoByAddress(String address) {
        List<CryptoDTO> list = cryptoRepository
                .findAll(Sort.by("symbol"))
                .stream()
                .filter(crypto -> crypto.getAddress().equalsIgnoreCase(address))
                .map(cryptoToCryptoDTO::convert)
                .collect(Collectors.toList());
        if(list.isEmpty()) throw new ResourceNotFoundException();
        return list;
    }

    @Override
    public List<CryptoDTO> getCryptoByName(String name) {
      List<CryptoDTO> list =  cryptoRepository
                .findAll(Sort.by("symbol"))
                .stream()
                .filter(crypto -> crypto.getName().equalsIgnoreCase(name))
                .map(cryptoToCryptoDTO::convert)
                .collect(Collectors.toList());
      if(list.isEmpty()) throw new ResourceNotFoundException();
      return list;
    }



    @Override
    public CryptoDTO getCryptoById(Long id) {
        return cryptoRepository.findById(id)
                .map(cryptoToCryptoDTO::convert)
                .orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public CryptoDTO createNewCrypto(CryptoDTO cryptoDTO) {
        cryptoDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return saveAndReturnDTO(cryptoDTOToCrypto.convert(cryptoDTO));
    }

    @Override
    public CryptoDTO saveCryptoByDTO(Long id, CryptoDTO cryptoDTO) {
        if(!cryptoRepository.findById(id).isPresent()) throw new ResourceNotFoundException();
        Crypto crypto = cryptoDTOToCrypto.convert(cryptoDTO);
        crypto.setId(id);
        return saveAndReturnDTO(crypto);
    }

    @Override
    public CryptoDTO deleteCryptoById(Long id) {
        return cryptoRepository.findById(id)
                .map(crypto -> {
                    CryptoDTO cryptoDTO = cryptoToCryptoDTO.convert(crypto);
                    cryptoDTO.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    cryptoDTO.setDeleted(true);
                    return cryptoDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<String> getAllCoinGeckoIds() {
        List<CryptoDTO> list = cryptoRepository.findAll()
                .stream().map(cryptoToCryptoDTO::convert)
                .collect(Collectors.toList());
        return list.stream().map(CryptoDTO::getCoinGeckoId).collect(Collectors.toList());
    }


    private Page<Crypto> findAllByProps(String symbol, String address, String name, Long page){

        Specification condition = Specification.where(null);

        if(symbol != null){
            condition = condition.and(CryptoSpecification.hasSymbol(symbol));
        }

        if(address != null){
            condition = condition.and(CryptoSpecification.hasAddress(address));
        }

        if(name != null){
            condition = condition.and(CryptoSpecification.hasName(name));
        }

        return cryptoRepository
                .findAll(condition, PageRequest.of(Math.toIntExact(page - 1),5, Sort.by("symbol")));


    }

    @Override
    public List<CryptoDTO> getCryptoBySymbolAddressName(String symbol, String address,
                                                        String name, Long page) {
        List<CryptoDTO> list = findAllByProps(symbol, address, name, page)
                .stream()
                .map(cryptoToCryptoDTO::convert)
                .collect(Collectors.toList());

        Collections.sort(list);
        if(list.isEmpty()) throw new ResourceNotFoundException();
        return list;
    }

//    @Override
//    public void deleteCryptoById(Long id) {
//        cryptoRepository.deleteById(id);
//    }

    private CryptoDTO saveAndReturnDTO(Crypto crypto){
        Crypto savedCrypto = cryptoRepository.save(crypto);
        CryptoDTO returnDTO = cryptoToCryptoDTO.convert(savedCrypto);
        returnDTO.setCryptoUrl(CryptoController.BASE_URL + savedCrypto.getId());
        returnDTO.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return returnDTO;
    }
}
