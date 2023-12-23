package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)

    private int id;

    @Column
    private String street;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zipCode;

    public Address(String street, String neighborhood, String city, String state, String zipCode) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Endereço {" +
                "rua='" + street + '\'' +
                ", bairro='" + neighborhood + '\'' +
                ", cidade='" + city + '\'' +
                ", UF='" + state + '\'' +
                ", CEP='" + zipCode + '\'' +
                '}';
    }
}
