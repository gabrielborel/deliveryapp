package gabrielborel.com.br.deliveryapp.repositories;

import gabrielborel.com.br.deliveryapp.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
