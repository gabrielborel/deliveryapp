package gabrielborel.com.br.deliveryapp.controllers;

import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderInputDto;
import gabrielborel.com.br.deliveryapp.services.DeliveryOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryOrderService;

    @Autowired
    public DeliveryOrderController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping("/delivery-order")
    @ResponseBody
    public ResponseEntity<?> createDeliveryOrder(@RequestBody @Valid CreateDeliveryOrderHttpRequestBodyDto body) {
        try {
            var createDeliveryOrderInput = CreateDeliveryOrderInputDto.fromHttpRequestBodyDto(body);
            var deliveryOrder =  this.deliveryOrderService.createDeliveryOrder(createDeliveryOrderInput);
            return new ResponseEntity<>(deliveryOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delivery-order")
    @ResponseBody
    public ResponseEntity<?> getDeliveryOrders() {
        var deliveryOrders = this.deliveryOrderService.getDeliveryOrders();
        return new ResponseEntity<>(deliveryOrders, HttpStatus.OK);
    }
}
