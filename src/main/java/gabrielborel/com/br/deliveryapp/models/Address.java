package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public Address(String street, String number, String neighborhood, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address() {}

    @Override
    public String toString() {
        return "Endereço {" +
                "rua='" + street + '\'' +
                ", número='" + number + '\'' +
                ", bairro='" + neighborhood + '\'' +
                ", cidade='" + city + '\'' +
                ", UF='" + state + '\'' +
                ", CEP='" + zipCode + '\'' +
                '}';
    }
}
