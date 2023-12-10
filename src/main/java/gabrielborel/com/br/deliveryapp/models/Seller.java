package gabrielborel.com.br.deliveryapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Seller {
    private String id;
    private String name;
    private String identification;
    private String email;
    private String phoneNumber;
    private Address storeAddress;
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Seller(String name, String identification, Address storeAddress, String email, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.identification = identification;
        this.storeAddress = storeAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", storeAddress=" + storeAddress +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
