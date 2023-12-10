package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Seller;
import gabrielborel.com.br.deliveryapp.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public void createSeller(Seller seller) {
        this.sellerRepository.save(seller);
    }

    public List<Seller> getSellers() {
        List<Seller> sellers = new ArrayList<>();
        this.sellerRepository.findAll().forEach(sellers::add);
        return sellers;
    }
}
