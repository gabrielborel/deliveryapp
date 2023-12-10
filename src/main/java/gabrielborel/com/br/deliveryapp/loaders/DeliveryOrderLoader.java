package gabrielborel.com.br.deliveryapp.loaders;

import gabrielborel.com.br.deliveryapp.models.Customer;
import gabrielborel.com.br.deliveryapp.models.DeliveryOrder;
import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.models.Seller;
import gabrielborel.com.br.deliveryapp.services.CustomerService;
import gabrielborel.com.br.deliveryapp.services.DeliveryOrderService;
import gabrielborel.com.br.deliveryapp.services.DeliverymanService;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderLoader {
    private static DeliveryOrderService deliveryOrderService;
    private static CustomerService customerService;
    private static DeliverymanService deliverymanService;
    private static SellerService sellerService;

    @Autowired
    public DeliveryOrderLoader(DeliveryOrderService deliveryOrderService, CustomerService customerService, DeliverymanService deliverymanService, SellerService sellerService) {
        DeliveryOrderLoader.deliveryOrderService = deliveryOrderService;
        DeliveryOrderLoader.customerService = customerService;
        DeliveryOrderLoader.deliverymanService = deliverymanService;
        DeliveryOrderLoader.sellerService = sellerService;
    }

    public static void load() {
        var customers = customerService.getCustomers().toArray();
        var deliverymen = deliverymanService.getDeliverymen().toArray();
        var sellers = sellerService.getSellers().toArray();

        for (var i = 0; i < 3; i++) {
            var customer = (Customer) customers[i];
            var deliveryman = (Deliveryman) deliverymen[i];
            var seller = (Seller) sellers[i];
            var deliveryOrder = new DeliveryOrder(seller, customer, deliveryman);
            deliveryOrderService.createDeliveryOrder(deliveryOrder);
        }
    }


}
