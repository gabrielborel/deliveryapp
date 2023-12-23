package gabrielborel.com.br.deliveryapp.models.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerInputDto {
    private String name;
    private String email;
    private String cep;
    private String phoneNumber;

    public static CreateCustomerInputDto fromRequestBody(CreateCustomerHttpRequestBodyDto dto) {
        return new CreateCustomerInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getCep(),
                dto.getPhoneNumber()
        );
    }
}
