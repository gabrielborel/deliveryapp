package gabrielborel.com.br.deliveryapp.models.dtos.customer;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutputDto {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    public static CustomerOutputDto fromModel(Customer customer) {
        return new CustomerOutputDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );
    }

    public static List<CustomerOutputDto> fromModelList(Iterable<Customer> customers) {
        List<CustomerOutputDto> customersDto = new ArrayList<>();
        customers.forEach(customer -> customersDto.add(fromModel(customer)));
        return customersDto;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id=" + id +
                "nome='" + name + '\'' +
                ", endereço=" + address +
                ", email='" + email + '\'' +
                ", telefone='" + phoneNumber + '\'' +
                '}';
    }

}
