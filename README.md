# 📦 Projeto Final - API de Gestão de Equipamentos Raros

Esta API REST foi desenvolvida como parte do projeto final da disciplina, com o objetivo de gerenciar equipamentos, seus estoques e o empréstimo desses itens para colaboradores.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5
- Spring Security + JWT
- PostgreSQL
- Hibernate (JPA)
- Lombok
- Swagger/OpenAPI
- Jakarta Validation

---

## 📌 Funcionalidades da API

### 🔐 Autenticação e Autorizações

- Login com JWT e geração de token
- Confirmação de e-mail
- Recuperação e redefinição de senha
- Refresh de token

#### Endpoints

| Método | Rota                      | Descrição                                   |
|--------|---------------------------|---------------------------------------------|
| POST   | /auth/login               | Login e geração de token                    |
| POST   | /auth/refresh-token       | Renovar access token com refresh token      |
| POST   | /auth/registrar           | Registro de novo usuário                    |
| POST   | /auth/confirmar-email     | Confirmação de e-mail                       |
| POST   | /auth/esqueci-senha       | Solicita redefinição de senha               |
| POST   | /auth/resetar-senha       | Redefine senha com token enviado por e-mail |
| POST   | /auth/alterar-senha       | Cadastra nova senha                         |

---

### 🛠️ Equipamentos

CRUD completo com controle de disponibilidade.

| Método | Rota                 | Descrição                   |
|--------|----------------------|-----------------------------|
| GET    | /equipamentos        | Listar todos os equipamentos|
| GET    | /equipamentos/{id}   | Buscar por ID               |
| POST   | /equipamentos        | Criar novo equipamento      |
| PUT    | /equipamentos/{id}   | Atualizar equipamento       |
| DELETE | /equipamentos/{id}   | Deletar equipamento         |

---

### 👤 Equipamento-Colaborador (Empréstimos)

| Método | Rota                                 | Descrição                           |
|--------|--------------------------------------|-------------------------------------|
| GET    | /api/emprestimos                     | Listar todos os envios              |
| GET    | /api/emprestimos/{id}                | Buscar envio por ID                 |
| POST   | /api/emprestimos                     | Cadastrar novo envio                |
| PUT    | /api/emprestimos/{id}                | Atualizar envio existente           |
| DELETE | /api/emprestimos/{id}                | Deletar envio por ID                |
| PUT    | /api/emprestimos/{id}/devolver       | Registrar devolução do equipamento  |

---

### 📦 Parâmetros do Sistema

Define as regras de estoque mínimo, reposição, consumo, etc.

| Método | Rota                                             | Descrição                                 |
|--------|--------------------------------------------------|-------------------------------------------|
| GET    | /parametros-sistema                              | Lista todos os parâmetros                 |
| GET    | /parametros-sistema/{id}                         | Buscar por ID                             |
| POST   | /parametros-sistema                              | Criar novo parâmetro                      |
| PUT    | /parametros-sistema/{id}                         | Atualizar parâmetro                       |
| DELETE | /parametros-sistema/{id}                         | Deletar parâmetro                         |
| GET    | /parametros-sistema/relatorio-previsao-falta     | Gera relatório de previsão de falta       |

---

### 🧾 Tipos de Equipamento

Categoria de equipamentos (ex: notebook, celular).

| Método | Rota                         | Descrição                             |
|--------|------------------------------|---------------------------------------|
| GET    | /tipos-equipamento           | Listar todos os tipos                 |
| GET    | /tipos-equipamento/{id}      | Buscar por ID                         |
| POST   | /tipos-equipamento           | Criar novo tipo                       |
| PUT    | /tipos-equipamento/{id}      | Atualizar tipo                        |
| DELETE | /tipos-equipamento/{id}      | Deletar tipo                          |

---

### 👥 Usuário

| Método | Rota            | Descrição                  |
|--------|-----------------|----------------------------|
| GET    | /usuarios       | Listar todos os usuários   |
| GET    | /usuarios/{id}  | Buscar usuário por ID      |
| POST   | /usuarios       | Criar novo usuário         |
| PUT    | /usuarios/{id}  | Atualizar usuário          |
| DELETE | /usuarios/{id}  | Deletar usuário            |

---


---

### 🧑‍🔧 Colaborador

| Método | Rota                                 | Descrição                     |
|--------|--------------------------------------|-------------------------------|
| POST   | /colaborador/cadastrar-colaborador   | Cadastrar novo colaborador    |
| PATCH  | /colaborador/editar-colaborador/{id} | Editar colaborador existente  |
| GET    | /colaborador/imprimir-colaboradores  | Imprimir lista de colaboradores |
| DELETE | /colaborador/deleta-colaborador/{id} | Deletar colaborador           |

---

### 🚨 Alertas de Estoque

| Método | Rota                          | Descrição                        |
|--------|-------------------------------|----------------------------------|
| POST   | /api/estoque-alerta/verificar | Verifica alerta de estoque baixo|

---

## 🧪 Como Rodar o Projeto

### 1. Pré-requisitos

- Java 21
- PostgreSQL
- Maven

### 2. Configuração do Banco

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar a aplicação

```bash
./mvnw spring-boot:run
```

Acesse em:

```
http://localhost:8080
```

---

## 📘 Documentação Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Autenticação JWT

- Login via `POST /auth/login`
- Copie o token JWT e clique em **Authorize** no Swagger
- Formato:  
```
Bearer seu_token_jwt_aqui
```

---

## 👨‍💻 Desenvolvedores

**JRLL TECH** – Grupo de Desenvolvimento

- João Paulo Fernandes Conceição  
- Rodrigo Mayer Alves  
- Luis Felipe Assunção Pereira  
- Lara Freire de Oliveira  

---

## 📜 Licença

Este projeto é de uso educacional.