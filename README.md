# FinanceFlow

Aplicação full stack de gestão financeira pessoal, criada como um projeto de portfólio com arquitetura em camadas, código legível e uma interface SaaS minimalista.

## Sobre

O FinanceFlow centraliza receitas, despesas, categorias e orçamentos mensais. O dashboard apresenta saldo, totais, evolução financeira, distribuição de gastos e movimentações recentes.

## Tecnologias

- Java 21, Spring Boot, Spring Web, Spring Data JPA e Bean Validation
- PostgreSQL, Lombok e Swagger/OpenAPI
- JUnit e Mockito
- React, Vite, JavaScript e CSS puro

## Funcionalidades

- CRUD de transações com validações e filtros combináveis
- Cadastro e exclusão de categorias por tipo
- Orçamento mensal por categoria, com gasto, percentual e saldo restante
- Resumo financeiro e agrupamentos por categoria
- Evolução mensal e últimas transações
- Estados de carregamento, erro e vazio
- Interface responsiva para desktop, tablet e celular
- Respostas de erro padronizadas e documentação OpenAPI

## Arquitetura

`React → API REST → Controller → Service → Repository → PostgreSQL`

O backend separa API, regras de negócio, persistência, entidades, DTOs, mapeamento, configuração e exceções. O frontend separa páginas, componentes reutilizáveis, hooks, serviços e utilitários.

## Como executar

### 1. Banco de dados

Crie um banco PostgreSQL chamado `financeflow`. Copie `.env.example` para `.env` ou exporte as variáveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/financeflow
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

### 2. Backend

É necessário Java 21 e Maven 3.9+.

```bash
cd backend
mvn spring-boot:run
```

A API estará em `http://localhost:8080/api`. O Swagger estará em `http://localhost:8080/swagger-ui.html`. As categorias iniciais são cadastradas automaticamente no primeiro uso.

### 3. Frontend

É necessário Node.js 20+.

```bash
cd frontend
npm install
npm run dev
```

Para outra URL da API, defina `VITE_API_URL`. O frontend abre em `http://localhost:5173`.

### 4. Testes e build

```bash
cd backend && mvn test
cd frontend && npm run build
```

## Screenshots

Adicione aqui capturas do dashboard, das transações e dos orçamentos após executar o projeto.

## Melhorias futuras

- Spring Security, JWT, login e múltiplos usuários
- Exportação CSV e relatórios
- Docker, CI/CD e deploy

## Sugestão de commits

`feat: create transaction domain` · `feat: add financial services` · `feat: expose REST API` · `test: add service tests` · `feat: create dashboard interface` · `feat: add budget tracking` · `docs: add project README`
