package gabrielborel.com.br.deliveryapp.repositories;

import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Long> {
}
