package gabrielborel.com.br.deliveryapp.models.dtos.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaCepAddressResponseDto {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;

    public ViaCepAddressResponseDto() {
    }

    public ViaCepAddressResponseDto(String logradouro, String bairro, String localidade, String uf, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.cep = cep;
    }
}
