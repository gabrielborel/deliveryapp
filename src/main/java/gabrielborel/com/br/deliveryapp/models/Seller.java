package gabrielborel.com.br.deliveryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.CreateSellerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.SellerOutputDto;
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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_sequence")
    @SequenceGenerator(name = "seller_sequence", sequenceName = "seller_sequence", allocationSize = 1)
    private int id;

    @Column
    private String name;

    @Column
    private String identification;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.MERGE, targetEntity = Address.class)
    private Address storeAddress;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Seller(int id, String name, String identification, Address storeAddress, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.storeAddress = storeAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Seller(String name, String identification, Address storeAddress, String email, String phoneNumber) {
        this.name = name;
        this.identification = identification;
        this.storeAddress = storeAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrders.add(deliveryOrder);
    }

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

    public static Seller fromInputDto(CreateSellerInputDto dto, Address address) {
        return new Seller(
                dto.getName(),
                dto.getIdentification(),
                address,
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }

    public static Seller fromOutputDto(SellerOutputDto dto) {
        return new Seller(
                dto.getId(),
                dto.getName(),
                dto.getIdentification(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }
}
