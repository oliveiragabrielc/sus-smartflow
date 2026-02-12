# 🏥 SUS SmartFlow

Sistema backend para triagem inteligente e prontuário unificado no Sistema Único de Saúde (SUS).

Projeto criado para demonstrar um fluxo simples de triagem com API REST, persistência com Hibernate Panache e cobertura de testes.

---

## 🎯 Visão geral

O sistema oferece endpoints para cadastrar pacientes, registrar triagens e consultar histórico de triagens por paciente. A classificação de risco é feita pelo `TriagemService` retornando: `VERDE`, `AMARELO` ou `VERMELHO`.

---

## 🧭 Endpoints principais

- `POST /pacientes` — cria um paciente. Corpo: `PacienteDTO` (nome, cpf, idade, doencasPreExistentes).
- `GET /pacientes` — lista todos os pacientes.
- `GET /pacientes/{id}` — obtém paciente por id (404 se não encontrado).
- `GET /pacientes/{id}/historico` — retorna lista de triagens do paciente.
- `POST /triagens` — cria uma triagem. Corpo: `TriagemDTO` (pacienteId, sintomas).
- `GET /triagens` — lista todas as triagens.

Exemplo rápido `PacienteDTO` / `TriagemDTO` campos disponíveis nas classes em `src/main/java/br/com/sus/dto`.

---

## 🧠 Regra de classificação (resumo)

Implementada em `TriagemService.classificar(String sintomas, int idade)`:
- Retorna `VERMELHO` se conter `dor no peito` ou `falta de ar`, `desmaio` ou `convulsão`.
- Retorna `AMARELO` se idade >= 60 e sintomas contiverem `febre`.
- Retorna `AMARELO` se sintomas contiverem `dor`.
- Caso contrário retorna `VERDE`.

---

## 🏗 Arquitetura e estrutura do código

- Resources: `src/main/java/br/com/sus/resource` — endpoints REST (`PacienteResource`, `TriagemResource`).
- Service: `src/main/java/br/com/sus/service/TriagemService.java` — regras de negócio.
- Model: `src/main/java/br/com/sus/model` — entidades JPA Panache (`Paciente`, `Triagem`).
- DTOs: `src/main/java/br/com/sus/dto` — objetos de transferência.
- Tests: `src/test/java/br/com/sus` — testes de integração/serviço adicionados.

---

## ⚙️ Tecnologias

- Java 21+
- Quarkus 3.x
- Hibernate ORM + Panache
- H2 
- Maven

---

## ▶️ Como executar (dev & testes)

### Pré-requisitos
- Java 21+
- Maven

### Rodar em modo desenvolvimento
```bash
./mvnw quarkus:dev
```
API será exposta em `http://localhost:8081` por padrão no perfil de testes/dev.

### Rodar testes
```bash
./mvnw test
```

### Gerar relatório de cobertura (JaCoCo)
```bash
./mvnw verify
```
Relatório HTML gerado em `target/jacoco-report/index.html` e o arquivo XML em `target/jacoco-report/jacoco.xml`.

---

## 💾 Banco de dados e dados de seed

- Configuração H2: `src/main/resources/application.properties` (usa `jdbc:h2:mem:susdb` em testes).
- Arquivo de seed: `src/main/resources/import.sql` — contém `INSERT` para `Paciente` e `Triagem` executados em ambiente dev/test.

Observação: as tabelas e sequences seguem a convenção do Hibernate (ex.: `Paciente_SEQ`, `Triagem_SEQ`). Se precisar redefinir, ajuste `import.sql`.

---

## 🔬 Testes adicionados

- `PacienteResourceTest` — cria, lista e busca paciente (checa 404).
- `TriagemResourceTest` — cria triagem e consulta histórico/listagem.
- `TriagemServiceTest` — testes unitários cobrindo regras de classificação.

Os testes são executados com Quarkus Test e usam H2 em memória.

---

## 📖 Documentação OpenAPI / Swagger

O projeto expõe a UI do Swagger (SmallRye OpenAPI). Após iniciar a aplicação em `quarkus:dev`, acesse `http://localhost:8081/` para ver a documentação.

---

## ✅ Recomendações e próximos passos

- Commitar as mudanças locais: `git add . && git commit -m "Add tests, JaCoCo and docs"`.
- Configurar CI para executar `mvn verify` e publicar `target/jacoco-report/jacoco.xml` para análise de cobertura.

---

Se quiser, eu posso: commitar as mudanças, adicionar instruções de run em Docker, ou configurar a geração do relatório JaCoCo em `target/site/jacoco`.
