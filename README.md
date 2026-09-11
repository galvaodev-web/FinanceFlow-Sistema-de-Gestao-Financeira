# FinanceFlow

Aplicação full stack de gestão financeira pessoal desenvolvida como projeto de portfólio, com foco em arquitetura backend, organização de domínio, API REST e experiência de uso em dashboard.

## Destaques

- Backend em Java 21 e Spring Boot
- API REST com arquitetura em camadas
- Persistência com PostgreSQL e Spring Data JPA
- DTOs, validações e tratamento padronizado de erros
- Dashboard responsivo em React
- Testes com JUnit e Mockito
- Documentação de API com Swagger/OpenAPI
- Estrutura separada entre frontend e backend

## Tecnologias

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Lombok
- Swagger / OpenAPI
- JUnit
- Mockito

### Frontend
- React
- Vite
- JavaScript
- CSS

## Funcionalidades

- CRUD de transações financeiras
- Cadastro e exclusão de categorias por tipo
- Filtros combináveis de transações
- Orçamento mensal por categoria
- Cálculo de gasto, percentual utilizado e saldo restante
- Resumo de receitas, despesas e saldo
- Agrupamento de gastos por categoria
- Evolução financeira mensal
- Listagem de movimentações recentes
- Estados de carregamento, erro e conteúdo vazio
- Interface responsiva para desktop, tablet e celular

## Arquitetura

```text
React
  ↓
API REST
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
PostgreSQL
```

O backend separa responsabilidades entre API, regras de negócio, persistência, entidades, DTOs, mapeamento, configuração e tratamento de exceções.

O frontend organiza páginas, componentes reutilizáveis, hooks, serviços e utilitários.

## Estrutura do projeto

```text
FinanceFlow-Sistema-de-Gestao-Financeira/
├── backend/
├── frontend/
├── .github/
├── .env.example
└── README.md
```

## Como executar

### Pré-requisitos

- Java 21
- Maven 3.9+
- Node.js 20+
- PostgreSQL

### 1. Banco de dados

Crie um banco PostgreSQL chamado `financeflow`.

Copie `.env.example` para `.env` ou exporte as variáveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/financeflow
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

### 2. Backend

```bash
cd backend
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080/api
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

O frontend abre em:

```text
http://localhost:5173
```

Para usar outra URL de API, configure `VITE_API_URL`.

## Testes e build

### Backend

```bash
cd backend
mvn test
```

### Frontend

```bash
cd frontend
npm run build
```

## Decisões de engenharia

Este projeto foi estruturado para demonstrar mais do que apenas CRUD. A intenção é evidenciar separação de responsabilidades, organização de domínio, persistência relacional, validação, tratamento de erros, documentação e testes automatizados.

## Roadmap

Próximas evoluções planejadas:

- Spring Security
- Autenticação JWT
- Suporte a múltiplos usuários
- Docker e Docker Compose
- Pipeline de CI/CD
- Exportação CSV
- Relatórios financeiros
- Deploy completo de frontend, backend e banco

## Status

Projeto funcional e em evolução contínua como parte do meu portfólio de desenvolvimento full stack com Java e React.

---

Desenvolvido por [Guilherme Galvão](https://github.com/galvaodev-web).