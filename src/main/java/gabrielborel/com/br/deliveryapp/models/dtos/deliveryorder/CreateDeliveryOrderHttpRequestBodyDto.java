package gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDeliveryOrderHttpRequestBodyDto {
    @NotNull(message = "sellerId is required")
    @Min(value = 1, message = "sellerId must be greater than 0")
    private int sellerId;
    @NotNull(message = "customerId is required")
    @Min(value = 1, message = "customerId must be greater than 0")
    private int customerId;
    @NotNull(message = "deliverymanId is required")
    @Min(value = 1, message = "deliverymanId must be greater than 0")
    private int deliverymanId;
}