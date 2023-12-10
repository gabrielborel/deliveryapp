package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeliveryOrderService {
    private final Map<String, DeliveryOrder> deliveryOrders = new HashMap<>();

    public void createDeliveryOrder(DeliveryOrder deliveryOrder) {
        deliveryOrders.put(deliveryOrder.getId(), deliveryOrder);
    }

    public Collection<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrders.values();
    }
}
