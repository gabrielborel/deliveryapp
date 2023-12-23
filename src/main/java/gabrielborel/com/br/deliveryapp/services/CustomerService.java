package gabrielborel.com.br.deliveryapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CreateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CustomerOutputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.UpdateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.DeliveryOrderOutputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.SellerOutputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.UpdateSellerInputDto;
import gabrielborel.com.br.deliveryapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public CustomerOutputDto createCustomer(CreateCustomerInputDto inputDto) throws JsonProcessingException {
        var address = addressService.createAddressFromCep(inputDto.getCep());
        var customer = Customer.fromInputDto(inputDto, address);
        customer.setAddress(address);
        this.customerRepository.save(customer);
        return CustomerOutputDto.fromModel(customer);
    }

    public List<CustomerOutputDto> getCustomers() {
        return CustomerOutputDto.fromModelList(customerRepository.findAll());
    }

    public CustomerOutputDto getCustomerById(int id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("customer not found"));
        return CustomerOutputDto.fromModel(customer);
    }

    public void deleteCustomerById(int id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("customer not found"));
        customerRepository.delete(customer);
    }

    public CustomerOutputDto updateCustomer(int id, UpdateCustomerInputDto updateCustomerInput) throws JsonProcessingException {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("customer not found"));

        updateIfNotNull(updateCustomerInput.getName(), customer::setName);
        updateIfNotNull(updateCustomerInput.getEmail(), customer::setEmail);
        updateIfNotNull(updateCustomerInput.getPhoneNumber(), customer::setPhoneNumber);

        if (updateCustomerInput.getCep() != null) {
            var address = addressService.createAddressFromCep(updateCustomerInput.getCep());
            customer.setAddress(address);
        }

        customerRepository.save(customer);
        return CustomerOutputDto.fromModel(customer);
    }

    public List<DeliveryOrderOutputDto> getCustomerDeliveryOrders(int id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("customer not found"));
        return DeliveryOrderOutputDto.fromModelList(customer.getDeliveryOrders());
    }

    private void updateIfNotNull(String newValue, Consumer<String> updater) {
        if (newValue != null) {
            updater.accept(newValue);
        }
    }
}
