# Desafio Itaú - API de Transações Financeiras 🚀

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red?style=for-the-badge&logo=apachemaven)

API REST desenvolvida em Java 17 com Spring Boot como parte do Desafio de Backend do Itaú. A API é responsável por receber transações financeiras, armazená-las em memória e calcular estatísticas em tempo real sobre elas.

O projeto foi focado em performance e boas práticas, garantindo que as estatísticas sejam calculadas de forma instantânea, sem a necessidade de um banco de dados.

## ✨ Features

* **API REST** para registrar e limpar transações.
* **Cálculo de Estatísticas** (`soma`, `média`, `min`, `max`, `contagem`) em tempo real.
* **Armazenamento 100% em Memória** (utilizando `ArrayList`) para máxima performance.
* **Validação de Regras de Negócio**, como não aceitar transações com datas futuras ou valores negativos.
* **Documentação da API** completa com SpringDoc (OpenAPI 3) e Swagger UI.
* **Testes Unitários e de Integração** (JUnit 5, Mockito e MockMvc) para garantir a qualidade e o comportamento esperado.
* **Monitoramento de Saúde (Health Check)** via Spring Boot Actuator.
* **Tratamento de Exceções Centralizado** (`@ControllerAdvice`) para respostas de erro consistentes.
* **Intervalo de Estatísticas Configurável** via parâmetro de busca no endpoint `GET /estatistica`.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Web** (para a API REST)
* **Spring Boot Actuator** (para Health Check)
* **SpringDoc (OpenAPI 3)** (para documentação Swagger)
* **Lombok** (para reduzir boilerplate)
* **JUnit 5 & Mockito** (para testes)
* **Maven** (como gerenciador de dependências)

## 🚀 Como Executar

### Pré-requisitos

* Java 17 (ou superior)
* Maven

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Romulo567/desafio-itau.git](https://github.com/Romulo567/desafio-itau.git)
    cd api-desafio-itau
    ```

2.  **Compile e empacote o projeto:**
    (Utilizando o Maven Wrapper incluído no projeto)

    *No Linux/Mac:*
    ```bash
    ./mvnw clean install
    ```
    *No Windows:*
    ```bash
    mvnw.cmd clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    java -jar target/api-desafio-itau-0.0.1-SNAPSHOT.jar
    ```

A aplicação estará disponível em `http://localhost:8080`.

## 📖 Endpoints da API

Após iniciar a aplicação, você pode acessar a documentação completa e interativa do Swagger UI em:

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

### Receber Transações

`POST /transacao`

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `valor` | Double | **Obrigatório.** O valor da transação |
| `dataHora` | OffsetDateTime | **Obrigatório.** O horário que a transação ocorreu |

---

### Limpar Transações

`DELETE /transacao`

---

### Calcular Estatísticas

`GET /estatistica`

| Parâmetro | Tipo | Descrição |
| :--- | :--- | :--- |
| `intervaloBusca` | Integer | **Não Obrigatório.** O padrão default é 60s |
