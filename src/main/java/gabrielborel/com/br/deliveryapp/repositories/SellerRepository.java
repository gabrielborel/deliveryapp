package gabrielborel.com.br.deliveryapp.repositories;

import gabrielborel.com.br.deliveryapp.models.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
}
