package gabrielborel.com.br.deliveryapp.models.dtos.seller;

import lombok.Getter;

@Getter
public class UpdateSellerHttpRequestBodyDto {
    private String name;
    private String email;
    private String identification;
    private String phoneNumber;
    private String cep;
}
