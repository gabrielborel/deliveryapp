package gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDeliveryOrderInputDto {
    private int sellerId;
    private int customerId;
    private int deliverymanId;

    public static CreateDeliveryOrderInputDto fromHttpRequestBodyDto(CreateDeliveryOrderHttpRequestBodyDto dto) {
        return new CreateDeliveryOrderInputDto(dto.getSellerId(), dto.getCustomerId(), dto.getDeliverymanId());
    }
}
