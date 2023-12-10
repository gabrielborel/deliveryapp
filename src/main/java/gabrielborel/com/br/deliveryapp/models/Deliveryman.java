package gabrielborel.com.br.deliveryapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Deliveryman {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String identification;
    private String vehicleLicensePlate;
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Deliveryman(String name, String email, String phoneNumber, String identification, String vehicleLicensePlate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.identification = identification;
        this.vehicleLicensePlate = vehicleLicensePlate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Deliveryman{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", vehicleLicensePlate='" + vehicleLicensePlate + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
