package gabrielborel.com.br.deliveryapp.controllers;

import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderHttpRequestBodyDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderInputDto;
import gabrielborel.com.br.deliveryapp.services.DeliveryOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

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
    public ResponseEntity<?> createDeliveryOrder(@RequestBody @Valid CreateDeliveryOrderHttpRequestBodyDto body, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            }

            var createDeliveryOrderInput = CreateDeliveryOrderInputDto.fromHttpRequestBodyDto(body);
            var deliveryOrder =  this.deliveryOrderService.createDeliveryOrder(createDeliveryOrderInput);
            return new ResponseEntity<>(deliveryOrder, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/delivery-order")
    @ResponseBody
    public ResponseEntity<?> getDeliveryOrders() {
        var deliveryOrders = this.deliveryOrderService.getDeliveryOrders();
        return ResponseEntity.ok(deliveryOrders);
    }

    @GetMapping("/delivery-order/{id}")
    @ResponseBody
    public ResponseEntity<?> getDeliveryOrderById(@PathVariable int id) {
        try {
            var deliveryOrder = this.deliveryOrderService.getDeliveryOrderById(id);
            return ResponseEntity.ok(deliveryOrder);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delivery-order/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteDeliveryOrderById(@PathVariable int id) {
        try {
            this.deliveryOrderService.deleteDeliveryOrderById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/delivery-order/{id}/start")
    @ResponseBody
    public ResponseEntity<?> startDeliveryOrder(@PathVariable int id) {
        try {
            var deliveryOrder = this.deliveryOrderService.startDeliveryOrder(id);
            return ResponseEntity.ok(deliveryOrder);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/delivery-order/{id}/complete")
    @ResponseBody
    public ResponseEntity<?> completeDeliveryOrder(@PathVariable int id) {
        try {
            var deliveryOrder = this.deliveryOrderService.completeDeliveryOrder(id);
            return ResponseEntity.ok(deliveryOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/delivery-order/{id}/cancel")
    @ResponseBody
    public ResponseEntity<?> cancelDeliveryOrder(@PathVariable int id) {
        try {
            var deliveryOrder = this.deliveryOrderService.cancelDeliveryOrder(id);
            return ResponseEntity.ok(deliveryOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
