package gabrielborel.com.br.deliveryapp.models.dtos.deliveryman;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliverymanOutputDto {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String identification;
    private String vehicleLicensePlate;

    public static DeliverymanOutputDto fromModel(Deliveryman deliveryman) {
        return new DeliverymanOutputDto(
                deliveryman.getId(),
                deliveryman.getName(),
                deliveryman.getEmail(),
                deliveryman.getPhoneNumber(),
                deliveryman.getIdentification(),
                deliveryman.getVehicleLicensePlate()
        );
    }

    public static List<DeliverymanOutputDto> fromModelList(Iterable<Deliveryman> deliverymen) {
        List<DeliverymanOutputDto> deliverymenDto = new ArrayList<>();
        deliverymen.forEach(deliveryman -> deliverymenDto.add(fromModel(deliveryman)));
        return deliverymenDto;
    }
}
