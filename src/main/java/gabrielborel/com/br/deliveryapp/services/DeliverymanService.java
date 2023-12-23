package gabrielborel.com.br.deliveryapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import gabrielborel.com.br.deliveryapp.models.Deliveryman;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.CreateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.DeliverymanOutputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryman.UpdateDeliverymanInputDto;
import gabrielborel.com.br.deliveryapp.models.dtos.deliveryorder.DeliveryOrderOutputDto;
import gabrielborel.com.br.deliveryapp.repositories.DeliverymanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class DeliverymanService {
    private final DeliverymanRepository deliverymanRepository;

    @Autowired
    public DeliverymanService(DeliverymanRepository deliverymanRepository) {
        this.deliverymanRepository = deliverymanRepository;
    }

    public DeliverymanOutputDto createDeliveryman(CreateDeliverymanInputDto inputDto) throws JsonProcessingException {
        var deliveryman = Deliveryman.fromInputDto(inputDto);
        this.deliverymanRepository.save(deliveryman);
        return DeliverymanOutputDto.fromModel(deliveryman);
    }

    public List<DeliverymanOutputDto> getDeliverymen() {
        return DeliverymanOutputDto.fromModelList(deliverymanRepository.findAll());
    }

    public DeliverymanOutputDto getDeliverymanById(int id) {
        var deliveryman = deliverymanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("deliveryman not found"));
        return DeliverymanOutputDto.fromModel(deliveryman);
    }

    public void deleteDeliverymanById(int id) {
        var deliveryman = deliverymanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("deliveryman not found"));
        deliverymanRepository.delete(deliveryman);
    }

    public DeliverymanOutputDto updateDeliveryman(int id, UpdateDeliverymanInputDto updateDeliverymanInput) throws JsonProcessingException {
        var deliveryman = deliverymanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("deliveryman not found"));

        updateIfNotNull(updateDeliverymanInput.getName(), deliveryman::setName);
        updateIfNotNull(updateDeliverymanInput.getEmail(), deliveryman::setEmail);
        updateIfNotNull(updateDeliverymanInput.getPhoneNumber(), deliveryman::setPhoneNumber);
        updateIfNotNull(updateDeliverymanInput.getIdentification(), deliveryman::setIdentification);
        updateIfNotNull(updateDeliverymanInput.getVehicleLicensePlate(), deliveryman::setVehicleLicensePlate);

        deliverymanRepository.save(deliveryman);
        return DeliverymanOutputDto.fromModel(deliveryman);
    }

    public List<DeliveryOrderOutputDto> getDeliverymanDeliveryOrders(int id) {
        var deliveryman = deliverymanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("deliveryman not found"));
        return DeliveryOrderOutputDto.fromModelList(deliveryman.getDeliveryOrders());
    }

    private void updateIfNotNull(String newValue, Consumer<String> updater) {
        if (newValue != null) {
            updater.accept(newValue);
        }
    }
}
