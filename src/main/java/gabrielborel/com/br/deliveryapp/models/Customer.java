package gabrielborel.com.br.deliveryapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Customer(String name, Address address, String email, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
