package gabrielborel.com.br.deliveryapp.models.dtos.customer;

import lombok.Getter;

@Getter
public class UpdateCustomerHttpRequestBodyDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String cep;
}
