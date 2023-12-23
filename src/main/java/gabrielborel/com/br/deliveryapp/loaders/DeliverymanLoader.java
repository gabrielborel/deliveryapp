package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.CreateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.services.DeliverymanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class DeliverymanLoader {
    private static DeliverymanService service;

    @Autowired
    public DeliverymanLoader(DeliverymanService deliverymanService) {
        DeliverymanLoader.service = deliverymanService;
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CustomerLoader.class.getResource("/deliverymen.csv")).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                var createDeliverymanInput = new CreateDeliverymanInputDto(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4]
                );
                service.createDeliveryman(createDeliverymanInput);
            }
        } catch (IOException e) {
            System.out.println("error when loading deliverymen: " + e.getMessage());
        }
    }
}
