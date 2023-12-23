package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.models.Seller;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.DeliveryOrderOutputDto;
import gabrielborel.com.br.deliveryapp.repositories.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final SellerService sellerService;
    private final CustomerService customerService;
    private final DeliverymanService deliverymanService;

    @Autowired
    public DeliveryOrderService(DeliveryOrderRepository deliveryOrderRepository, SellerService sellerService, CustomerService customerService, DeliverymanService deliverymanService) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.sellerService = sellerService;
        this.customerService = customerService;
        this.deliverymanService = deliverymanService;
    }

    public DeliveryOrderOutputDto createDeliveryOrder(CreateDeliveryOrderInputDto deliveryOrderInput) {
        var seller = Seller.fromOutputDto(this.sellerService.getSellerById(deliveryOrderInput.getSellerId()));
        var customer = Customer.fromOutputDto(this.customerService.getCustomerById(deliveryOrderInput.getCustomerId()));
        var deliveryman = Deliveryman.fromOutputDto(this.deliverymanService.getDeliverymanById(deliveryOrderInput.getDeliverymanId()));

        var deliveryOrder = new DeliveryOrder(seller, customer, deliveryman);
        seller.addDeliveryOrder(deliveryOrder);
        customer.addDeliveryOrder(deliveryOrder);
        deliveryman.addDeliveryOrder(deliveryOrder);

        this.deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderOutputDto.fromModel(deliveryOrder);
    }

    public List<DeliveryOrderOutputDto> getDeliveryOrders() {
        return DeliveryOrderOutputDto.fromModelList(this.deliveryOrderRepository.findAll());
    }

    public DeliveryOrderOutputDto getDeliveryOrderById(int id) {
        return DeliveryOrderOutputDto.fromModel(this.deliveryOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("delivery order not found")));
    }

    public void deleteDeliveryOrderById(int id) {
        var deliveryOrder = this.deliveryOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("delivery order not found"));
        this.deliveryOrderRepository.delete(deliveryOrder);
    }

    public DeliveryOrderOutputDto startDeliveryOrder(int id) {
        var deliveryOrder = this.deliveryOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("delivery order not found"));
        deliveryOrder.startDelivery();
        this.deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderOutputDto.fromModel(deliveryOrder);
    }

    public DeliveryOrderOutputDto cancelDeliveryOrder(int id) {
        var deliveryOrder = this.deliveryOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("delivery order not found"));
        deliveryOrder.cancelDelivery();
        this.deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderOutputDto.fromModel(deliveryOrder);
    }

    public DeliveryOrderOutputDto completeDeliveryOrder(int id) {
        var deliveryOrder = this.deliveryOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("delivery order not found"));
        deliveryOrder.completeDelivery();
        this.deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderOutputDto.fromModel(deliveryOrder);
    }
}
