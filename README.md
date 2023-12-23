Optei por deixar um arquivo **docker-compose.yml** para facilitar a execução do projeto, por não precisar ter o ambiente configurado, basta ter o **docker** e **docker-compose** instalados na máquina.

Para subir o banco de dados, basta executar o comando abaixo na raiz do projeto:

```bash
docker-compose up -d
```

E assim iniciar a aplicação Java que irá consumir o banco de dados.


# Documentação das Rotas
## Rotas de Customers

1. **POST** `/customer`
    - Parâmetros: Objeto `CreateCustomerHttpRequestBodyDto` no corpo da requisição.
    - Exemplo de corpo da requisição:
    ```json
    {
         "name": "Gabriel Borel",
         "email": "biel_borel@hotmail.com",
         "phoneNumber": "24999899256",
         "identification": "19252283714",
         "cep": "27259170"
    }
    ```
    - Resposta: Objeto `CustomerOutputDto` com status HTTP 201 (Criado) ou mensagem de erro com status HTTP 400 (Requisição Inválida).
    - Exemplo de corpo da resposta:
    ```json
    {
        "id": 1,
        "name": "Gabriel Borel",
        "email": "biel_borel@hotmail.com",
        "phoneNumber": "24999899256",
        "identification": "19252283714",
        "address": {
            "id": 1,
            "cep": "27259170",
            "street": "Rua Quatorze",
            "district": "Jardim Vila Rica - Tiradentes",
            "city": "Volta Redonda",
            "state": "RJ"
        }
    }
    ```

2. **GET** `/customer`
    - Parâmetros: Nenhum.
    - Resposta: Lista de objetos `CustomerOutputDto` com status HTTP 200 (OK).

3. **GET** `/customer/{id}`
    - Parâmetros: `id` (inteiro) no caminho.
    - Resposta: Objeto `CustomerOutputDto` com status HTTP 200 (OK) ou mensagem de erro com status HTTP 400 (Requisição Inválida).

4. **DELETE** `/customer/{id}`
    - Parâmetros: `id` (inteiro) no caminho.
    - Resposta: Mensagem "customer deleted" com status HTTP 200 (OK) ou mensagem de erro com status HTTP 400 (Requisição Inválida).

5. **PUT** `/customer/{id}`
    - Parâmetros: `id` (inteiro) no caminho, objeto `UpdateCustomerHttpRequestBodyDto` no corpo da requisição.
    - Resposta: Objeto `CustomerOutputDto` com status HTTP 200 (OK) ou mensagem de erro com status HTTP 400 (Requisição Inválida).

6. **GET** `/customer/{id}/delivery-orders`
    - Parâmetros: `id` (inteiro) no caminho.
    - Resposta: Lista de objetos `DeliveryOrderOutputDto` com status HTTP 200 (OK) ou mensagem de erro com status HTTP 400 (Requisição Inválida).
