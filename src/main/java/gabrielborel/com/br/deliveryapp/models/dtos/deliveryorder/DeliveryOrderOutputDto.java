package gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder;

import gabrielborel.com.br.deliveryapp.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryOrderOutputDto {
    private int id;
    private Seller seller;
    private Customer customer;
    private Deliveryman deliveryman;
    private DeliveryOrderStatus status;
    private String createdAt;
    private String canceledAt;
    private String deliveredAt;
    private Boolean isFinished;

    public static DeliveryOrderOutputDto fromModel(DeliveryOrder deliveryOrder) {
        return new DeliveryOrderOutputDto(
                deliveryOrder.getId(),
                deliveryOrder.getSeller(),
                deliveryOrder.getCustomer(),
                deliveryOrder.getDeliveryman(),
                deliveryOrder.getStatus(),
                deliveryOrder.getCreatedAt().toString(),
                deliveryOrder.getCanceledAt() != null ? deliveryOrder.getCanceledAt().toString() : null,
                deliveryOrder.getDeliveredAt() != null ? deliveryOrder.getDeliveredAt().toString() : null,
                deliveryOrder.getIsFinished()
        );
    }

    public static List<DeliveryOrderOutputDto> fromModelList(Iterable<DeliveryOrder> deliveryOrders) {
        List<DeliveryOrderOutputDto> deliveryOrdersDto = new ArrayList<>();
        deliveryOrders.forEach(deliveryOrder -> deliveryOrdersDto.add(fromModel(deliveryOrder)));
        return deliveryOrdersDto;
    }


    @Override
    public String toString() {
        return "Pedido de Entrega {" +
                "id=" + id +
                "vendedor=" + seller +
                ", cliente=" + customer +
                ", entregador=" + deliveryman +
                ", status=" + status +
                ", criada em=" + createdAt +
                ", entregue em=" + deliveredAt +
                ", cancelada em=" + canceledAt +
                ", está finalizada=" + isFinished +
                '}';
    }
}
