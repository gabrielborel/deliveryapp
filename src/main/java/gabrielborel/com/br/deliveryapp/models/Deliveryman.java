package gabrielborel.com.br.deliveryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.CreateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.DeliverymanOutputDto;
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
public class Deliveryman {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deliveryman_sequence")
    @SequenceGenerator(name = "deliveryman_sequence", sequenceName = "deliveryman_sequence", allocationSize = 1)
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

    @JsonManagedReference
    @JsonIgnore
    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "deliveryman")
    private List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public Deliveryman(int id, String name, String email, String phoneNumber, String identification, String vehicleLicensePlate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.identification = identification;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public Deliveryman(String name, String email, String phoneNumber, String identification, String vehicleLicensePlate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.identification = identification;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public void addDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrders.add(deliveryOrder);
    }

    @Override
    public String toString() {
        return "Entregador {" +
                "id=" + id +
                "nome='" + name + '\'' +
                ", CPF='" + identification + '\'' +
                ", Placa do veículo='" + vehicleLicensePlate + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + phoneNumber + '\'' +
                '}';
    }

    public static Deliveryman fromInputDto(CreateDeliverymanInputDto dto) {
        return new Deliveryman(
                dto.getName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getIdentification(),
                dto.getVehicleLicensePlate()
        );
    }

    public static Deliveryman fromOutputDto(DeliverymanOutputDto dto) {
        return new Deliveryman(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getIdentification(),
                dto.getVehicleLicensePlate()
        );
    }
}
