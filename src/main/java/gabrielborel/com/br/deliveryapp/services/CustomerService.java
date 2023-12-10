package gabrielborel.com.br.deliveryapp.services;

import gabrielborel.com.br.deliveryapp.models.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    private final Map<String, Customer> customers = new HashMap<>();

    public void createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }
}
