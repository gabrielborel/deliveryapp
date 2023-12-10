package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Seller;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class SellerService {
    private final Map<String, Seller> sellers = new HashMap<>();

    public void createSeller(Seller seller) {
        sellers.put(seller.getId(), seller);
    }

    public Collection<Seller> getSellers() {
        return sellers.values();
    }
}
