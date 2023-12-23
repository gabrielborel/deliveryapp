package gabrielborel.com.br.deliveryapp.models.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerHttpRequestBodyDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "cep is required")
    private String cep;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
}
