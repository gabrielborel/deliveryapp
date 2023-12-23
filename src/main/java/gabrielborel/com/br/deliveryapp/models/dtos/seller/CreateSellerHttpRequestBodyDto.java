package gabrielborel.com.br.deliveryapp.models.dtos.seller;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSellerHttpRequestBodyDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "identification (cpf/cnpj) is required")
    private String identification;
    @NotBlank(message = "cep is required")
    private String cep;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
}
