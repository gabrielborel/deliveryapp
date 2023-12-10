package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import gabrielborel.com.br.deliveryapp.repositories.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    public DeliveryOrderService(DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }


    public void createDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrderRepository.save(deliveryOrder);
    }

    public List<DeliveryOrder> getDeliveryOrders() {
        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
        this.deliveryOrderRepository.findAll().forEach(deliveryOrders::add);
        return deliveryOrders;
    }
}
