package gabrielborel.com.br.deliveryapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.*;
import gabrielborel.com.br.deliveryapp.services.AddressService;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seller.getId())
                .toUri();
        return ResponseEntity.created(location).body(seller);
    }

    @GetMapping("/seller")
    @ResponseBody
    public ResponseEntity<List<SellerOutputDto>> getSellers() {
        var sellers = sellerService.getSellers();
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> getSeller(@PathVariable int id) {
        try {
            var seller = sellerService.getSellerById(id);
            return ResponseEntity.ok(seller);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteSeller(@PathVariable int id) {
        try {
            sellerService.deleteSellerById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/seller/{id}")
    @ResponseBody
    public ResponseEntity<?> updateSeller(@PathVariable int id, @RequestBody UpdateSellerHttpRequestBodyDto body, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            }

            var updateSellerDto = UpdateSellerInputDto.fromRequestBody(body);
            var seller = sellerService.updateSeller(id, updateSellerDto);
            return new ResponseEntity<>(seller, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/seller/{id}/delivery-orders")
    @ResponseBody
    public ResponseEntity<?> getSellerDeliveryOrders(@PathVariable int id) {
        try {
            var deliveryOrders = sellerService.getSellerDeliveryOrders(id);
            return ResponseEntity.ok(deliveryOrders);
        } catch(NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
