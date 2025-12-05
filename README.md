# 📚 Library API — Spring Boot 3 + JPA (Java 21)

API RESTful desenvolvida para gerenciamento de recursos de uma biblioteca, aplicando arquitetura limpa, validações, DTOs, serviços desacoplados, JPA/Hibernate e boas práticas de desenvolvimento backend profissional.

---

## 📌 Sobre o projeto

Este projeto foi desenvolvido acompanhando o curso **Profissionalize-se em Java com Spring Boot (Atualizado 2025)**, seguindo rigorosamente padrões modernos de API REST, estruturação de camadas e qualidade no código.

Até o momento, o projeto contempla:

- Estrutura inicial completa do Spring Boot 3
- Configuração de dependências essenciais (Web, JPA, Validation)
- Estruturação de pacotes seguindo boas práticas
- Criação da entidade **Autor**
- Implementação do **contrato de criação de Autor (POST /autores)**
- Uso de DTOs, validações e tratamento básico de erros
- Persistência com Spring Data JPA
- Serviços bem definidos com responsabilidade clara

Este repositório serve como um portfólio técnico, demonstrando domínio de Java moderno e boas práticas backend.

---

## 🧱 Tecnologias utilizadas

| Tecnologia | Uso |
|-----------|-----|
| **Java 21** | Linguagem principal |
| **Spring Boot 3** | Framework da aplicação |
| **Spring Web** | Criação de APIs REST |
| **Spring Data JPA** | Persistência com Hibernate |
| **Bean Validation** | Validação de dados de entrada |
| **Maven** | Gerenciamento de dependências |
| **H2 / MySQL** | Banco de dados |
| **Lombok** | Redução de boilerplate |

---

## 🗂 Arquitetura do projeto

```
src/main/java
└── com.example.libraryapi
    ├── domain        // Entidades da aplicação
    │   └── Autor.java
    ├── dto           // Objetos de transferência (requests/responses)
    ├── mapper        // Conversão entre entidades e DTOs
    ├── repository    // Interfaces Spring Data JPA
    ├── service       // Regras de negócio
    │   └── AutorService.java
    ├── controller    // Endpoints REST
    │   └── AutorController.java
    └── config        // Configurações da aplicação
```

Essa arquitetura reflete separação de responsabilidades, facilitando manutenção, testes e escalabilidade.

---

## ✨ Funcionalidades (até o momento)

### ✅ Autor
- Criar novo Autor → `POST /autores`
- Validações de campos obrigatórios
- Conversão automática entre DTO e entidade
- Persistência via JPA/Hibernate
- Retorno padronizado para a API

---

## 📥 Exemplo de requisição (POST /autores)

```json
{
  "nome": "Machado de Assis"
}
```

### ✔️ Resposta esperada

```json
{
  "id": 1,
  "nome": "Machado de Assis"
}
```

---

## 🚀 Como executar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/libraryapi.git
```

### 2. Entrar no projeto
```bash
cd libraryapi
```

### 3. Rodar via Maven
```bash
mvn spring-boot:run
```

### 4. Acessar a API
```
http://localhost:8080/autores
```

---

## 🧭 Próximos passos do projeto

- CRUD completo de Autor
- Entidade Livro
- CRUD de Livro
- Relação Autor ↔ Livro
- Tratamento de exceções global com @ControllerAdvice
- Swagger/OpenAPI
- Paginação e filtros
- Segurança com JWT

---

## 🧑‍💻 Sobre o desenvolvedor

Projeto criado por **Jardelson (Jota)** como parte de sua evolução profissional em backend Java e Spring Boot, com foco em qualidade, arquitetura limpa e boas práticas.

---

## ⭐ Contribua ou deixe uma estrela

Se este projeto te ajudou ou te inspirou, deixe uma ⭐ no GitHub.  
Isso ajuda o portfólio e motiva a continuidade do projeto.

