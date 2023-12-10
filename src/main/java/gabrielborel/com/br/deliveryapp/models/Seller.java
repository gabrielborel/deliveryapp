package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Seller {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String identification;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Embedded
    private Address storeAddress;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Seller(String name, String identification, Address storeAddress, String email, String phoneNumber) {
        this.name = name;
        this.identification = identification;
        this.storeAddress = storeAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Seller() {}

    @Override
    public String toString() {
        return "Vendedor {" +
                "nome='" + name + '\'' +
                ", CPF/CNPJ='" + identification + '\'' +
                ", endereço da loja=" + storeAddress +
                ", email='" + email + '\'' +
                ", telefone='" + phoneNumber + '\'' +
                '}';
    }
}
