package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.dtos.customer.CreateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class CustomerLoader {
    private static CustomerService customerService;

    @Autowired
    public CustomerLoader(CustomerService customerService) {
        CustomerLoader.customerService = customerService;
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CustomerLoader.class.getResource("/customers.csv")).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                var createCustomerInput = new CreateCustomerInputDto(values[0], values[1], values[3], values[2]);
                customerService.createCustomer(createCustomerInput);
            }
        } catch (IOException e) {
            System.out.println("error when loading customers: " + e.getMessage());
        }
    }
}