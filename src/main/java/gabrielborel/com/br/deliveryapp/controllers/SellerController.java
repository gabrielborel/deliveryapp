package gabrielborel.com.br.deliveryapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.*;
import gabrielborel.com.br.deliveryapp.services.AddressService;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RestController
public class SellerController {
    private final SellerService sellerService;
    private final AddressService addressService;

    public SellerController(SellerService sellerService, AddressService addressService) {
        this.sellerService = sellerService;
        this.addressService = addressService;
    }

    @PostMapping("/seller")
    @ResponseBody
    public ResponseEntity<SellerOutputDto> createSeller(@RequestBody @Valid CreateSellerHttpRequestBodyDto body) throws JsonProcessingException {
        var createSellerDto = CreateSellerInputDto.fromRequestBody(body);
        var seller = sellerService.createSeller(createSellerDto);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    @GetMapping("/seller")
    @ResponseBody
    public ResponseEntity<List<SellerOutputDto>> getSellers() {
        var sellers = sellerService.getSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> getSeller(@PathVariable int id) {
        try {
            var seller = sellerService.getSellerById(id);
            return new ResponseEntity<>(seller, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("seller not found",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteSeller(@PathVariable int id) {
        try {
            sellerService.deleteSellerById(id);
            return new ResponseEntity<>("seller deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("seller not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> updateSeller(@PathVariable int id, @RequestBody UpdateSellerHttpRequestBodyDto body) throws JsonProcessingException {
        try {
            var updateSellerDto = UpdateSellerInputDto.fromRequestBody(body);
                var seller = sellerService.updateSeller(id, updateSellerDto);
            return new ResponseEntity<>(seller, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("seller not found",HttpStatus.BAD_REQUEST);
        }
    }
}
