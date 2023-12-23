package gabrielborel.com.br.deliveryapp.models.dtos.deliveryman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliverymanInputDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String identification;
    private String vehicleLicensePlate;

    public static CreateDeliverymanInputDto fromRequestBody(CreateDeliverymanHttpRequestBodyDto dto) {
        return new CreateDeliverymanInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getIdentification(),
                dto.getVehicleLicensePlate()
        );
    }
}
