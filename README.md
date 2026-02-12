# 🏥 SUS SmartFlow

Sistema backend para triagem inteligente e prontuário unificado no Sistema Único de Saúde (SUS).

Projeto desenvolvido para o Hackathon – Inovação para otimização de atendimento no SUS.

---

## 🎯 Objetivo

O SUS SmartFlow tem como objetivo otimizar o atendimento nas unidades de saúde por meio de:
- Classificação automática de risco (triagem)
- Centralização do histórico de atendimentos
- Apoio à tomada de decisão dos profissionais de saúde

---

## 🧠 Funcionalidades

- Cadastro de pacientes
- Registro de triagens
- Classificação automática (VERDE, AMARELO, VERMELHO)
- Consulta de histórico por paciente
- API documentada com Swagger (OpenAPI)

---

## 🏗️ Arquitetura

A aplicação segue o padrão em camadas:

Cliente (Swagger/Postman)  
→ API REST (Resources)  
→ Service (Regra de negócio)  
→ Banco de Dados (H2)

---

## ⚙️ Tecnologias

- Java 17
- Quarkus
- Hibernate Panache
- H2 Database
- OpenAPI / Swagger UI
- Maven

---

## ▶️ Como rodar o projeto

### Pré-requisitos:
- Java 21+
- Maven
- Git

### Passos:

Clone o repositório:

```bash
git clone https://github.com/oliveiragabrielc/sus-smartflow#
cd sus-smartflow
