package com.edso.crypto.controllers.v1;

import com.edso.crypto.api.v1.model.CryptoDTO;
import com.edso.crypto.api.v1.model.CryptoListDTO;
import com.edso.crypto.services.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(CryptoController.BASE_URL)
public class CryptoController {
    private final CryptoService cryptoService;
    public static final String BASE_URL = "/api/v1/cryptos/";

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Operation(summary = "Get all cryptos")
    @GetMapping({"{page}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoListDTO getAllCryptos(@PathVariable Long page){
        return new CryptoListDTO(cryptoService.getAllCryptos(page));
    }


    @Operation(summary = "Get crypto by id")
    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the crypto"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Crypto not found")
    })
    public CryptoDTO getCryptoById(@Parameter(description = "id of crypto to be searched") @RequestParam Long id){
        return cryptoService.getCryptoById(id);
    }

    @Operation(summary = "Create new crypto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptoDTO createNewCrypto(@RequestBody CryptoDTO cryptoDTO){
        return cryptoService.createNewCrypto(cryptoDTO);
    }

    @Operation(summary = "Update a crypto by id")
    @PutMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoDTO updateCryptoById(@PathVariable Long id, @RequestBody CryptoDTO cryptoDTO){
        return cryptoService.saveCryptoByDTO(id, cryptoDTO);
    }

//    @DeleteMapping({"{id}"})
//    public ResponseEntity<Void> deleteCryptoById(@PathVariable Long id){
//        cryptoService.deleteCryptoById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    //delete is just a put-mapping
    @Operation(summary = "Delete a crypto by id")
    @PutMapping({"{id}/delete"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoDTO deleteCryptoById(@PathVariable Long id){
        return cryptoService.deleteCryptoById(id);
    }

    @Operation(summary = "Find by symbol")
    @GetMapping({"findBySymbol/{symbol}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoDTO getCryptoBySymbol(@PathVariable String symbol){
        return cryptoService.getCryptoBySymbol(symbol);

    }

    @Operation(summary = "Find by address")
    @GetMapping({"findByAddress/{address}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoListDTO getCryptoByAddress(@PathVariable String address){
        return new CryptoListDTO(cryptoService.getCryptoByAddress(address));
    }

    @Operation(summary = "Find by name")
    @GetMapping({"findByName/{name}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoListDTO getCryptoByName(@PathVariable String name){
        return new CryptoListDTO(cryptoService.getCryptoByName(name));
    }

    @Operation(summary = "Find by multi props")
    @GetMapping({"findByProps/{page}"})
    @ResponseStatus(HttpStatus.OK)
    public CryptoListDTO getCryptoByProps(@RequestParam(required = false) String symbol,
                                          @RequestParam(required = false) String address,
                                          @RequestParam(required = false) String name,
                                          @PathVariable Long page){
        return new CryptoListDTO(cryptoService.getCryptoBySymbolAddressName(symbol, address, name, page));
    }

}
