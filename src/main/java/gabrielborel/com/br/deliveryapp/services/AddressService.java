package gabrielborel.com.br.deliveryapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gabrielborel.com.br.deliveryapp.models.Address;
import gabrielborel.com.br.deliveryapp.models.dtos.address.ViaCepAddressResponseDto;
import gabrielborel.com.br.deliveryapp.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AddressService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address createAddressFromCep(String cep) throws JsonProcessingException {
        var CEP_API_URL = "https://viacep.com.br/ws/" + cep + "/json/";

        WebClient webClient = WebClient.builder()
                .baseUrl(CEP_API_URL)
                .build();

        String jsonResponse = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        var viaCepAddress = this.objectMapper.readValue(jsonResponse, ViaCepAddressResponseDto.class);

        var address = new Address(
                viaCepAddress.getLogradouro(),
                viaCepAddress.getBairro(),
                viaCepAddress.getLocalidade(),
                viaCepAddress.getUf(),
                viaCepAddress.getCep()
        );

        this.addressRepository.save(address);

        return address;
    }
}
