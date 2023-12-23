package gabrielborel.com.br.deliveryapp.repositories;


import gabrielborel.com.br.deliveryapp.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
