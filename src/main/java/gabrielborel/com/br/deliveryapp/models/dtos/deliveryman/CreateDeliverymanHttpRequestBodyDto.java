package gabrielborel.com.br.deliveryapp.models.dtos.deliveryman;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliverymanHttpRequestBodyDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
    @NotBlank(message = "identification is required")
    private String identification;
    @NotBlank(message = "vehicle license plate is required")
    private String vehicleLicensePlate;
}
