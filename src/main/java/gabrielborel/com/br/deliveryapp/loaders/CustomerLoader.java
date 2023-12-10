package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class CustomerLoader {
    private static CustomerService service;

    @Autowired
    public CustomerLoader(CustomerService customerService) {
        CustomerLoader.service = customerService;
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CustomerLoader.class.getResource("/customers.csv")).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Address address = new Address(values[3], values[4], values[5], values[6], values[7], values[8]);
                service.createCustomer(new Customer(values[0], address, values[1], values[2]));
            }
        } catch (IOException e) {
            System.out.println("error when loading customers: " + e.getMessage());
        }
    }
}