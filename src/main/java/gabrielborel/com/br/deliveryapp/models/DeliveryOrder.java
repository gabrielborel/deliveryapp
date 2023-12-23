package gabrielborel.com.br.deliveryapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.CreateDeliveryOrderInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_order_sequence")
    @SequenceGenerator(name = "delivery_order_sequence", sequenceName = "delivery_order_sequence", allocationSize = 1)
    private int id;

    @JsonBackReference
    @ManyToOne
    private Seller seller;

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    @JsonBackReference
    @ManyToOne
    private Deliveryman deliveryman;

    @Column
    private DeliveryOrderStatus status;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate canceledAt;

    @Column
    private LocalDate deliveredAt;

    @Column
    private Boolean isFinished;

    public DeliveryOrder(Seller seller, Customer customer, Deliveryman deliveryman) {
        this.seller = seller;
        this.customer = customer;
        this.deliveryman = deliveryman;
        this.status = DeliveryOrderStatus.PENDING;
        this.createdAt = LocalDate.now();
        this.isFinished = false;
    }

    public void startDelivery() {
        if (this.isFinished) {
            throw new IllegalStateException("delivery order is already finished");
        }
        if (this.status != DeliveryOrderStatus.PENDING) {
            throw new IllegalStateException("delivery order is not pending");
        }

        this.status = DeliveryOrderStatus.IN_PROGRESS;
    }

    public void cancelDelivery() {
        if (this.isFinished) {
            throw new IllegalStateException("delivery order is already finished");
        }
        if (this.status == DeliveryOrderStatus.DELIVERED) {
            throw new IllegalStateException("delivery order is already delivered");
        }
        if (this.status == DeliveryOrderStatus.CANCELED) {
            throw new IllegalStateException("delivery order is already canceled");
        }

        this.status = DeliveryOrderStatus.CANCELED;
        this.canceledAt = LocalDate.now();
        this.isFinished = true;
    }

    public void completeDelivery() {
        if (this.isFinished) {
            throw new IllegalStateException("delivery order is already finished");
        }
        if (this.status != DeliveryOrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("delivery order is not in progress");
        }

        this.status = DeliveryOrderStatus.DELIVERED;
        this.deliveredAt = LocalDate.now();
        this.isFinished = true;
    }
}
