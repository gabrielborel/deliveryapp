package gabrielborel.com.br.deliveryapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Customer customer;

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

    public DeliveryOrder() {}

    @Override
    public String toString() {
        return "Pedido de Entrega {" +
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
