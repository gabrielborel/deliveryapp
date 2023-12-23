package gabrielborel.com.br.deliveryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CreateCustomerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.customer.CustomerOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.MERGE, targetEntity = Address.class)
    private Address address;

    @JsonManagedReference
    @JsonIgnore
    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Customer(int id, String name, Address address, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Customer(String name, Address address, String email, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public static Customer fromInputDto(CreateCustomerInputDto dto, Address address) {
        return new Customer(
                dto.getName(),
                address,
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }

    public static Customer fromOutputDto(CustomerOutputDto dto) {
        return new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }
}
