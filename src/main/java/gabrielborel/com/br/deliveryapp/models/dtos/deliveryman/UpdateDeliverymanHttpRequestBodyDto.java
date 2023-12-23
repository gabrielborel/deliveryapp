package gabrielborel.com.br.deliveryapp.models.dtos.deliveryman;

import lombok.Getter;

@Getter
public class UpdateDeliverymanHttpRequestBodyDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String identification;
    private String vehicleLicensePlate;
}
