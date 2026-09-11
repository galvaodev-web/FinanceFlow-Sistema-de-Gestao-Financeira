# FinanceFlow

Aplicação full stack de gestão financeira pessoal desenvolvida como projeto de portfólio, com foco em arquitetura backend, organização de domínio, API REST, testes automatizados e experiência de uso em dashboard.

## Destaques

- Backend em Java 21 e Spring Boot
- API REST com arquitetura em camadas
- Persistência com PostgreSQL e Spring Data JPA
- DTOs, validações e tratamento padronizado de erros
- Dashboard responsivo em React
- Testes automatizados com JUnit e Mockito
- Cobertura de testes com JaCoCo
- Documentação de API com Swagger/OpenAPI
- CI com GitHub Actions para `mvn verify` e build do frontend
- Docker e Docker Compose para subir backend + PostgreSQL
- Dependabot para Maven, npm e GitHub Actions
- Documentação arquitetural em `docs/ARCHITECTURE.md`

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
- JaCoCo

### Frontend
- React
- Vite
- JavaScript
- CSS

### DevOps e qualidade
- Docker
- Docker Compose
- GitHub Actions
- Dependabot
- Maven
- npm

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

A documentação arquitetural detalhada está em [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md).

## Estrutura do projeto

```text
FinanceFlow-Sistema-de-Gestao-Financeira/
├── backend/
│   └── Dockerfile
├── frontend/
├── docs/
│   └── ARCHITECTURE.md
├── .github/
│   ├── dependabot.yml
│   └── workflows/
│       ├── ci.yml
│       └── deploy-pages.yml
├── docker-compose.yml
├── .env.example
└── README.md
```

## Como executar

### Opção 1: Docker Compose

Com Docker instalado, suba o backend e o PostgreSQL com:

```bash
docker compose up --build
```

A API ficará disponível em:

```text
http://localhost:8080/api
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

### Opção 2: execução local

#### Pré-requisitos

- Java 21
- Maven 3.9+
- Node.js 20+
- PostgreSQL

#### Banco de dados

Crie um banco PostgreSQL chamado `financeflow`.

Copie `.env.example` para `.env` ou exporte as variáveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/financeflow
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

#### Backend

```bash
cd backend
mvn spring-boot:run
```

#### Frontend

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

## Testes e CI

### Backend

```bash
cd backend
mvn verify
```

O comando executa os testes e gera o relatório JaCoCo em:

```text
target/site/jacoco/index.html
```

### Frontend

```bash
cd frontend
npm run build
```

O workflow `.github/workflows/ci.yml` executa automaticamente a verificação do backend e o build do frontend em pushes e pull requests para `main`. O relatório JaCoCo também é salvo como artefato da execução.

## Decisões de engenharia

Este projeto foi estruturado para demonstrar mais do que apenas CRUD. A intenção é evidenciar separação de responsabilidades, organização de domínio, persistência relacional, validação, tratamento de erros, documentação, testes automatizados, cobertura e integração contínua.

Autenticação não será tratada como uma tela isolada: a evolução planejada inclui Spring Security, JWT e escopo dos dados por usuário para que transações, categorias e orçamentos pertençam de fato ao usuário autenticado.

## Roadmap técnico

Próximas evoluções planejadas:

- Spring Security e autenticação JWT
- Suporte a múltiplos usuários e autorização por proprietário do recurso
- Testes de integração com Testcontainers
- Flyway para versionamento de schema
- Exportação CSV e relatórios financeiros
- Deploy completo de frontend, backend e banco em cloud

## Status

Projeto funcional e em evolução contínua como parte do meu portfólio de desenvolvimento full stack com Java e React.

---

Desenvolvido por [Guilherme Galvão](https://github.com/galvaodev-web).