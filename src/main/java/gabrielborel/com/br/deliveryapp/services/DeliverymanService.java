package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.repositories.DeliverymanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeliverymanService {
    private final DeliverymanRepository deliverymanRepository;

    @Autowired
    public DeliverymanService(DeliverymanRepository deliverymanRepository) {
        this.deliverymanRepository = deliverymanRepository;
    }

    public void createDeliveryman(Deliveryman deliveryman) {
        this.deliverymanRepository.save(deliveryman);
    }

    public List<Deliveryman> getDeliverymen()
    {
        List<Deliveryman> deliverymen = new ArrayList<>();
        this.deliverymanRepository.findAll().forEach(deliverymen::add);
        return deliverymen;
    }
}
