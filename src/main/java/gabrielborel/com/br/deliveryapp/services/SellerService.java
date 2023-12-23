package gabrielborel.com.br.deliveryapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.Seller;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.CreateSellerInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.SellerOutputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.seller.UpdateSellerInputDto;
import gabrielborel.com.br.deliveryapp.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final AddressService addressService;

    @Autowired
    public SellerService(SellerRepository sellerRepository, AddressService addressService) {
        this.sellerRepository = sellerRepository;
        this.addressService = addressService;
    }

    public SellerOutputDto createSeller(CreateSellerInputDto createSellerDto) throws JsonProcessingException {
        var address = addressService.createAddressFromCep(createSellerDto.getCep());
        var seller = Seller.fromInputDto(createSellerDto, address);
        seller.setStoreAddress(address);
        sellerRepository.save(seller);
        return SellerOutputDto.fromModel(seller);
    }

    public List<SellerOutputDto> getSellers() {
        return SellerOutputDto.fromModelList(sellerRepository.findAll());
    }

    public SellerOutputDto getSellerById(int id) {
        var seller = sellerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("seller not found"));
        return SellerOutputDto.fromModel(seller);
    }

    public void deleteSellerById(int id) {
        var seller = sellerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("seller not found"));
        sellerRepository.delete(seller);
    }

    public SellerOutputDto updateSeller(int id, UpdateSellerInputDto updateSellerDto) throws JsonProcessingException {
        var seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("seller not found"));

        updateIfNotNull(updateSellerDto.getName(), seller::setName);
        updateIfNotNull(updateSellerDto.getEmail(), seller::setEmail);
        updateIfNotNull(updateSellerDto.getIdentification(), seller::setIdentification);
        updateIfNotNull(updateSellerDto.getPhoneNumber(), seller::setPhoneNumber);

        if (updateSellerDto.getCep() != null) {
            var address = addressService.createAddressFromCep(updateSellerDto.getCep());
            seller.setStoreAddress(address);
        }

        sellerRepository.save(seller);
        return SellerOutputDto.fromModel(seller);
    }

    private void updateIfNotNull(String newValue, Consumer<String> updater) {
        if (newValue != null) {
            updater.accept(newValue);
        }
    }
}
