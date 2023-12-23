package gabrielborel.com.br.deliveryapp.models.dtos.deliveryman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeliverymanInputDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String identification;
    private String vehicleLicensePlate;

    public static UpdateDeliverymanInputDto fromRequestBody(UpdateDeliverymanHttpRequestBodyDto dto) {
        return new UpdateDeliverymanInputDto(
                dto.getName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getIdentification(),
                dto.getVehicleLicensePlate()
        );
    }
}
