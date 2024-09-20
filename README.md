# CrediFac API

Este projeto é uma aplicação web desenvolvida em **Spring Boot** com **Thymeleaf** que permite gerenciar empréstimos de clientes, incluindo a geração automática de parcelas e situações de pagamento como Paga, Inadimplente, Em Aberto e Pago Parcialmente.

## Funcionalidades

- Cadastro de clientes e empréstimos
- Cálculo automático de parcelas mensais
- Exibição da situação das parcelas (Paga, Inadimplente, etc.)
- Integração com frontend via Thymeleaf
- Uso de Docker para gerenciamento do banco de dados

## Tecnologias Utilizadas

- **Java 22**
- **Spring Boot** (versão compatível com Java 22)
- **Thymeleaf** para o frontend
- **Hibernate** para ORM
- **Postgres** (ou outro banco de dados, configurável via `application.properties`)
- **Docker** e **Docker Compose** para containerização do banco de dados
- **JUnit** e **Mockito** para testes unitários
- **DBeaver** para gerenciar conexões com bancos de dados

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Java 22](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [DBeaver](https://dbeaver.io/) (opcional, para gerenciar o banco de dados)

## Instalação e Configuração

1. **Clonar o repositório**

   ```bash
   git clone https://github.com/seu-usuario/credifac-api.git
   cd credifac-api
   
2. **Configurar o banco de dados**
  - cd database-docker
  - docker-compose up -d

3. **executar a aplicação**
 - docker-compose up --build