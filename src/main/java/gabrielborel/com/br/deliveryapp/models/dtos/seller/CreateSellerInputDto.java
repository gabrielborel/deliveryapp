package gabrielborel.com.br.deliveryapp.models.dtos.seller;

import gabrielborel.com.br.deliveryapp.models.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSellerInputDto {
    private String name;
    private String email;
    private String identification;
    private String phoneNumber;
    private String cep;

    public static CreateSellerInputDto fromRequestBody(CreateSellerHttpRequestBodyDto dto) {
        return new CreateSellerInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getIdentification(),
                dto.getPhoneNumber(),
                dto.getCep()
        );
    }
}
