package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Embedded
    private Address address;

    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Customer(String name, Address address, String email, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {}

    @Override
    public String toString() {
        return "Cliente {" +
                "nome='" + name + '\'' +
                ", endereço=" + address +
                ", email='" + email + '\'' +
                ", telefone='" + phoneNumber + '\'' +
                '}';
    }
}
