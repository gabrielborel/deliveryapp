package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeliverymanService {
    private final Map<String, Deliveryman> deliverymen = new HashMap<>();

    public void createDeliveryman(Deliveryman deliveryman) {
        deliverymen.put(deliveryman.getId(), deliveryman);
    }

    public Collection<Deliveryman> getDeliverymen() {
        return deliverymen.values();
    }
}
