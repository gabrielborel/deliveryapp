Optei por deixar um arquivo **docker-compose.yml** para facilitar a execução do projeto, por não precisar ter o ambiente configurado, basta ter o **docker** e **docker-compose** instalados na máquina.

Para subir o banco de dados, basta executar o comando abaixo na raiz do projeto:

```bash
docker-compose up -d
```

E assim iniciar a aplicação Java que irá consumir o banco de dados.
