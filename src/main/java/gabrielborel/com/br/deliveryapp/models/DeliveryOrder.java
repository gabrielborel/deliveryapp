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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public DeliveryOrder(int id, Seller seller, Customer customer, Deliveryman deliveryman) {
        this.id = id;
        this.seller = seller;
        this.customer = customer;
        this.deliveryman = deliveryman;
        this.status = DeliveryOrderStatus.PENDING;
        this.createdAt = LocalDate.now();
        this.isFinished = false;
    }

    public DeliveryOrder(Seller seller, Customer customer, Deliveryman deliveryman) {
        this.seller = seller;
        this.customer = customer;
        this.deliveryman = deliveryman;
        this.status = DeliveryOrderStatus.PENDING;
        this.createdAt = LocalDate.now();
        this.isFinished = false;
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
