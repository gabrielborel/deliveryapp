package gabrielborel.com.br.deliveryapp.models;

import java.time.LocalDate;
import java.util.UUID;

public class DeliveryOrder {
    private String id;
    private Seller seller;
    private Customer customer;
    private Deliveryman deliveryman;
    private DeliveryOrderStatus status;
    private LocalDate createdAt;
    private LocalDate deliveredAt;
    private Boolean isFinished;

    public DeliveryOrder(Seller seller, Customer customer, Deliveryman deliveryman) {
        this.id = UUID.randomUUID().toString();
        this.seller = seller;
        this.customer = customer;
        this.deliveryman = deliveryman;
        this.status = DeliveryOrderStatus.PENDING;
        this.createdAt = LocalDate.now();
        this.isFinished = false;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "id='" + id + '\'' +
                ", seller=" + seller +
                ", customer=" + customer +
                ", deliveryman=" + deliveryman +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", deliveredAt=" + deliveredAt +
                ", isFinished=" + isFinished +
                '}';
    }
}
