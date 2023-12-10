package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.Seller;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SellerLoader {
    private static SellerService service;

    @Autowired
    public SellerLoader(SellerService sellerService) {
        SellerLoader.service = sellerService;
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CustomerLoader.class.getResource("/sellers.csv")).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Address address = new Address(values[3], values[4], values[5], values[6], values[7], values[8]);
                service.createSeller(new Seller(values[0], values[1], address, values[2], values[4]));
            }
        } catch (IOException e) {
            System.out.println("error when loading sellers: " + e.getMessage());
        }
    }
}
