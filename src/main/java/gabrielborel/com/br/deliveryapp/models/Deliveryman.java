package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Deliveryman {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String identification;

    @Column
    private String vehicleLicensePlate;

    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "deliveryman")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Deliveryman(String name, String email, String phoneNumber, String identification, String vehicleLicensePlate) {
        this.name = name;
        this.identification = identification;
        this.vehicleLicensePlate = vehicleLicensePlate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Deliveryman() {}

    @Override
    public String toString() {
        return "Entregador {" +
                "nome='" + name + '\'' +
                ", CPF='" + identification + '\'' +
                ", Placa do veículo='" + vehicleLicensePlate + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + phoneNumber + '\'' +
                '}';
    }
}
