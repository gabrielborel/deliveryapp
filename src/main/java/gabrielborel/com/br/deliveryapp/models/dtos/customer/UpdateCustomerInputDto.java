package gabrielborel.com.br.deliveryapp.models.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerInputDto {
    private String name;
    private String email;
    private String cep;
    private String phoneNumber;

    public static UpdateCustomerInputDto fromRequestBody(UpdateCustomerHttpRequestBodyDto dto) {
        return new UpdateCustomerInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getCep(),
                dto.getPhoneNumber()
        );
    }
}
