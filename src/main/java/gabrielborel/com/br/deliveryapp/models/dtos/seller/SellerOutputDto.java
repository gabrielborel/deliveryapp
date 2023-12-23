package gabrielborel.com.br.deliveryapp.models.dtos.seller;

import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.Seller;
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
public class SellerOutputDto {
    private int id;
    private String name;
    private String email;
    private String identification;
    private String phoneNumber;
    private Address address;

    public static SellerOutputDto fromModel(Seller seller) {
        return new SellerOutputDto(
                seller.getId(),
                seller.getName(),
                seller.getEmail(),
                seller.getIdentification(),
                seller.getPhoneNumber(),
                seller.getStoreAddress()
        );
    }

    public static List<SellerOutputDto> fromModelList(Iterable<Seller> sellers) {
        List<SellerOutputDto> sellersDto = new ArrayList<>();
        sellers.forEach(seller -> sellersDto.add(fromModel(seller)));
        return sellersDto;
    }
}
