# 📁 Teste Técnico Java - Prover

Este projeto é uma API REST desenvolvida em Java 17 com Spring Boot para o cadastro de clientes, incluindo funcionalidades CRUD, importação via CSV e autenticação por token Bearer.

---

## ✅ Funcionalidades

- 🔍 Buscar todos os clientes
- 🔍 Buscar clientes por nome
- ➕ Cadastrar cliente
- ✏️ Atualizar cliente
- ❌ Deletar cliente
- 📤 Cadastro em massa via upload de arquivo `.csv`
- 🔐 Todas as rotas são protegidas por token `Bearer`

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- PostgreSQL (ou H2 para testes)
- Swagger (Springdoc)
- JUnit 5 / Mockito / Jacoco

---

## 🧪 Cobertura de testes

- Cobertura de código: **95%**
- Ferramenta: **JaCoCo**
- Para visualizar o relatório:

```bash
./mvnw clean test
open target/site/jacoco/index.html
```

---

## 🔑 Autenticação

Todas as requisições devem conter um header:

```
Authorization: Bearer <TOKEN_FIXO>
```

Para testes locais, o token fixo usado é:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q
```

---

## 📄 Documentação via Swagger

Acesse a documentação da API via:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🛠️ Como rodar localmente

### Requisitos

- Java 17
- PostgreSQL
- Maven

### Configuração

1. Clone o projeto
2. Configure o banco PostgreSQL (schema `test`)
3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

---

## 🗂️ Estrutura de pastas

```
src/
├── main/
│   ├── java/
│   │   └── com.prover.test/
│   │       ├── controller
│   │       ├── service
│   │       ├── model
│   │       ├── security
│   │       ├── config
│   │       └── repository
│   └── resources/
└── test/
    └── java/com.prover.test/
        └── ... (testes unitários organizados por pacote)
```

---

## 💾 Exemplo de CSV aceito

```csv
name,email,phone
João Silva,joao@email.com,123456789
Maria Souza,maria@email.com,987654321
```

---

## 👨‍💻 Autor

Desenvolvido por Diego Vargas.
