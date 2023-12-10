package gabrielborel.com.br.deliveryapp;

import gabrielborel.com.br.deliveryapp.loaders.CustomerLoader;
import gabrielborel.com.br.deliveryapp.loaders.DeliveryOrderLoader;
import gabrielborel.com.br.deliveryapp.loaders.DeliverymanLoader;
import gabrielborel.com.br.deliveryapp.loaders.SellerLoader;
import gabrielborel.com.br.deliveryapp.services.CustomerService;
import gabrielborel.com.br.deliveryapp.services.DeliveryOrderService;
import gabrielborel.com.br.deliveryapp.services.DeliverymanService;
import gabrielborel.com.br.deliveryapp.services.SellerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryappApplication {
	public static void main(String[] args) {
		var ctx = SpringApplication.run(DeliveryappApplication.class, args);

		CustomerLoader.load();
		DeliverymanLoader.load();
		SellerLoader.load();
		DeliveryOrderLoader.load();

		var customerService = ctx.getBean(CustomerService.class);
		var deliverymanService = ctx.getBean(DeliverymanService.class);
		var sellerService = ctx.getBean(SellerService.class);
		var deliveryOrderService = ctx.getBean(DeliveryOrderService.class);

		System.out.println("\nCustomers:");
		customerService.getCustomers().forEach(System.out::println);

		System.out.println("\nDeliverymen:");
		deliverymanService.getDeliverymen().forEach(System.out::println);

		System.out.println("\nSellers:");
		sellerService.getSellers().forEach(System.out::println);

		System.out.println("\nDelivery Orders:");
		deliveryOrderService.getDeliveryOrders().forEach(System.out::println);
	}
}
