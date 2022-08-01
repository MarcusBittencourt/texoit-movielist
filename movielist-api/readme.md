# Piores do ano API

### Instalação
Projeto de desafio técnico Texo IT.

Consiste em uma API desenvolvida em Java 17 em conjunto com Spring Boot, que realiza o carregamento de um arquivo CSV sobre filmes para um banco de dados H2 embutido em memória. Por meio de chamadas REST é possível obter os premiados na categoria de pior filme, sendo estes os que ganharam no menor intervalo de tempo e também no maior intervalo de tempo. Opcionalmente você pode consultar os requisitos da API em `./docs/specification.pdf`

É necessário ter Java 17 previamente instalado para executar o projeto. Verifique a sua versão instalada executando:

```sh
java --version
```

Caso necessário execute: 

```sh
apt-get update
apt-get upgrade
apt install openjdk-17-jdk openjdk-17-jre
```

Primeiro instale as dependências do projeto a partir do diretório `./movie-list-api`

```sh
./mvnw install
```

### Execução

A partir do diretório `./movielist-api` execute a aplicação com o seguinte comando. Após isso os dados do arquivo CSV estarão carregados no banco de dados e a API estará respondendo chamadas a partir do endereço localhost:8080. 

```sh
./mvnw spring-boot:run
```

### Testes

Para verificar a qualidade do código uma cobertura mínima de testes de integração pode ser executada. Estes testes focaram apenas na camada HTTP, Também é necessário executar o seguinte comando a partir do diretório `./movielist-api`. Como resultado você verá o relatório de testes informando quais passaram ou não.

```sh
./mvnw test
``` 