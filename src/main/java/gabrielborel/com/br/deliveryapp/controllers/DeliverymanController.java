package gabrielborel.com.br.deliveryapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.CreateDeliverymanHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.CreateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.UpdateDeliverymanHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.UpdateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.services.DeliverymanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RestController
public class DeliverymanController {
    private final DeliverymanService deliverymanService;

    @Autowired
    public DeliverymanController(DeliverymanService deliverymanService) {
        this.deliverymanService = deliverymanService;
    }

    @PostMapping("/deliveryman")
    @ResponseBody
    public ResponseEntity<?> createDeliveryman(@RequestBody @Valid CreateDeliverymanHttpRequestBodyDto body) throws JsonProcessingException {
        var createDeliverymanDto = CreateDeliverymanInputDto.fromRequestBody(body);
        var deliveryman = deliverymanService.createDeliveryman(createDeliverymanDto);
        return new ResponseEntity<>(deliveryman, HttpStatus.CREATED);
    }

    @GetMapping("/deliveryman")
    @ResponseBody
    public ResponseEntity<?> getDeliverymans() {
        var deliverymen = deliverymanService.getDeliverymen();
        return new ResponseEntity<>(deliverymen, HttpStatus.OK);
    }

    @GetMapping("/deliveryman/{id}")
    @ResponseBody
    public ResponseEntity<?> getDeliveryman(@PathVariable int id) {
        try {
            var deliveryman = deliverymanService.getDeliverymanById(id);
            return new ResponseEntity<>(deliveryman, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("deliveryman not found",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deliveryman/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteDeliveryman(@PathVariable int id) {
        try {
            deliverymanService.deleteDeliverymanById(id);
            return new ResponseEntity<>("deliveryman deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("deliveryman not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deliveryman/{id}")
    @ResponseBody
    public ResponseEntity<?> updateDeliveryman(@PathVariable int id, @RequestBody UpdateDeliverymanHttpRequestBodyDto body) throws JsonProcessingException {
        try {
            var updateDeliverymanInput = UpdateDeliverymanInputDto.fromRequestBody(body);
            var deliveryman = deliverymanService.updateDeliveryman(id, updateDeliverymanInput);
            return new ResponseEntity<>(deliveryman, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("deliveryman not found",HttpStatus.BAD_REQUEST);
        }
    }
}
