package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.CreateSellerInputDto;
import gabrielborel.com.br.deliveryapp.services.AddressService;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class SellerLoader {
    private static SellerService sellerService;

    @Autowired
    public SellerLoader(SellerService sellerService) {
        SellerLoader.sellerService = sellerService;
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CustomerLoader.class.getResource("/sellers.csv")).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                var createSellerDto = new CreateSellerInputDto(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4]
                );

                sellerService.createSeller(createSellerDto);
            }
        } catch (IOException e) {
            System.out.println("error when loading sellers: " + e.getMessage());
        }
    }
}
