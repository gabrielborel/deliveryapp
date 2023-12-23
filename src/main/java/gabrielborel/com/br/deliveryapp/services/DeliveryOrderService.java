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
        this.deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderOutputDto.fromModel(deliveryOrder);
    }

    public List<DeliveryOrderOutputDto> getDeliveryOrders() {
        return DeliveryOrderOutputDto.fromModelList(this.deliveryOrderRepository.findAll());
    }
}
