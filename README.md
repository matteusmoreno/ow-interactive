# OW Interactive

OW Interactive é uma aplicação de backend desenvolvida para gerenciar transações financeiras entre usuários. A aplicação permite criar usuários, realizar transações de crédito e débito, e processar reversões de transações.

Esta aplicação é referente ao desafio: [OW Interactive](https://github.com/owInteractive/desafio-backend).

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.3.2-blue)](https://spring.io/projects/spring-data-jpa)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)](https://www.mysql.com/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.24-yellowgreen)](https://projectlombok.org/)
[![Flyway](https://img.shields.io/badge/Flyway-9.0.1-purple)](https://flywaydb.org/)
[![Swagger](https://img.shields.io/badge/Swagger-3.0.0-brightgreen)](https://swagger.io/)

## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Endpoints da API](#endpoints-da-api)
- [Execução](#execução)
- [Licença](#licença)

## Descrição

Esta aplicação fornece uma API RESTful para gerenciar transações financeiras. As principais funcionalidades incluem:

- **Criar Usuários**: Adiciona um novo usuário ao sistema.
- **Transações de Crédito e Débito**: Permite adicionar e retirar fundos da conta de um usuário.
- **Reversão de Transações**: Permite reverter transações previamente realizadas.
- **Consulta de Transações por Usuário**: Permite consultar todas as transações associadas a um usuário específico.

## Funcionalidades

- **Criação de Usuário**
    - `POST /users/create`
- **Criação de Transações de Crédito**
    - `POST /transactions/credit`
- **Criação de Transações de Débito**
    - `POST /transactions/debit`
- **Criação de Transações de Reversão**
    - `POST /transactions/reversal`
- **Consulta de Transações por Usuário**
    - `GET /transactions/find-all-by-user/{id}`

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Flyway para migrações de banco de dados**

## Configuração do Ambiente

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/ow-interactive.git
    ```

2. **Configure o banco de dados MySQL:**

   Certifique-se de que o MySQL esteja instalado e em execução. Crie um banco de dados chamado `ow_interactive`.

3. **Configure as variáveis de ambiente:**

   Defina as seguintes variáveis de ambiente com as credenciais do banco de dados MySQL:

    ```bash
    export MYSQL_USERNAME=seu_usuario
    export MYSQL_PASSWORD=sua_senha
    ```

4. **Compile e execute a aplicação:**

    ```bash
    cd ow-interactive
    ./mvnw spring-boot:run
    ```

## Endpoints da API

### Usuários

- **Criar Usuário**
    - **Endpoint:** `POST /users/create`
    - **Request Body:**
      ```json
      {
        "name": "Nome do Usuário",
        "email": "email@exemplo.com",
        "birthday": "yyyy-MM-dd"
      }
      ```

- **Consultar Todos os Usuários**
    - **Endpoint:** `GET /users/find-all`
    - **Query Parameters:** `page`, `size`

- **Detalhes do Usuário**
    - **Endpoint:** `GET /users/details/{id}`

- **Excluir Usuário**
    - **Endpoint:** `DELETE /users/delete/{id}`

### Transações

- **Criação de Transação de Crédito**
    - **Endpoint:** `POST /transactions/credit`
    - **Request Body:**
      ```json
      {
        "value": 100.00,
        "userId": 1
      }
      ```

- **Criação de Transação de Débito**
    - **Endpoint:** `POST /transactions/debit`
    - **Request Body:**
      ```json
      {
        "value": 50.00,
        "userId": 1
      }
      ```

- **Criação de Reversão de Transação**
    - **Endpoint:** `POST /transactions/reversal`
    - **Request Body:**
      ```json
      {
        "transactionId": 1,
        "userId": 1
      }
      ```

- **Consultar Transações por Usuário**
    - **Endpoint:** `GET /transactions/find-all-by-user/{id}`
    - **Query Parameters:** `page`, `size`

## Execução

Para executar a aplicação localmente, siga as instruções de configuração e utilize o comando `./mvnw spring-boot:run`. A aplicação estará disponível em `http://localhost:8080`.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

---

Desenvolvido por [Matteus Moreno](https://github.com/matteusmoreno).
