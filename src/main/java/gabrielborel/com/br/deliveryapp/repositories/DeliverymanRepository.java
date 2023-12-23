package gabrielborel.com.br.deliveryapp.repositories;

import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends CrudRepository<Deliveryman, Integer> {
}
