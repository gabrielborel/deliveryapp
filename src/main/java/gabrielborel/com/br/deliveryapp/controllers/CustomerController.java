package gabrielborel.com.br.deliveryapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CreateCustomerHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CreateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.UpdateCustomerHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.UpdateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.UpdateSellerHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.UpdateSellerInputDto;
import gabrielborel.com.br.deliveryapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    @ResponseBody
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerHttpRequestBodyDto body) throws JsonProcessingException {
        var createCustomerDto = CreateCustomerInputDto.fromRequestBody(body);
        var customer = customerService.createCustomer(createCustomerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    @ResponseBody
    public ResponseEntity<?> getCustomers() {
        var customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    @ResponseBody
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        try {
            var customer = customerService.getCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("customer not found",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/customer/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>("customer deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("customer not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/customer/{id}")
    @ResponseBody
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody UpdateCustomerHttpRequestBodyDto body) throws JsonProcessingException {
        try {
            var updateCustomerInput = UpdateCustomerInputDto.fromRequestBody(body);
            var customer = customerService.updateCustomer(id, updateCustomerInput);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("customer not found",HttpStatus.BAD_REQUEST);
        }
    }
}
