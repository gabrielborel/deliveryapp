package gabrielborel.com.br.deliveryapp.repositories;

import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.models.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer> {
    Iterable<DeliveryOrder> findAllBySeller(Seller seller);
    Iterable<DeliveryOrder> findAllByCustomer(Customer customer);
    Iterable<DeliveryOrder> findAllByDeliveryman(Deliveryman deliveryman);
}
