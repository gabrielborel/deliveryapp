package gabrielborel.com.br.deliveryapp.models.dtos.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateSellerInputDto {
    private String name;
    private String email;
    private String identification;
    private String phoneNumber;
    private String cep;

    public static UpdateSellerInputDto fromRequestBody(UpdateSellerHttpRequestBodyDto dto) {
        return new UpdateSellerInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getIdentification(),
                dto.getPhoneNumber(),
                dto.getCep()
        );
    }
}
