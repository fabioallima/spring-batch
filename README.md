# Projeto Spring Batch

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green.svg)](https://spring.io/projects/spring-boot)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()

## Visão Geral do Projeto

Spring Batch é um framework poderoso para a execução de tarefas em lote em Java. Este projeto é uma demonstração abrangente de como utilizar o Spring Batch para gerenciar e executar tarefas em lote de forma eficiente.

## Principais Características e Tecnologias

1. **Spring Boot 3.4.1**: Framework base para o desenvolvimento rápido de aplicações Java.
2. **Persistência de Dados**:
   - JPA/Hibernate para ORM
   - Banco de dados H2
3. **Desenvolvimento e Produtividade**:
   - Lombok para redução de boilerplate
   - MapStruct para mapeamento objeto-objeto
4. Spring Batch:
   - `JobLauncher` para iniciar o processo de lote. É possível definir parâmetros e propriedades executar o job
   - `Job` é o principal componente do Spring Batch, é uma aplicação que processa uma quantidade finita de dados sem interação ou interrupção
   - `Step` representa uma etapa na qual a lógica é executada. Normalmente os steps são encadeados com o intuito de fornecer um resultado ao final do processamento
   - `Tasklet` para executar tarefas simples
   - `Chunks` para executar tarefas mais complexas, são quebrados em três etapas: **leitura** (ItemReader), **processamento** (ItemProcessor) e **escrita** (ItemWriter)
   - `ItemReader` e `ItemWriter` para ler e escrever dados
   - `ItemProcessor` para processar itens antes de serem escritos
   - `JobRepository` para persistir o estado do processo em lote. Responsável por armazenar informações sobre os jobs, passos, execuções de passos e outras entidades relacionadas ao processo em lote
   - `JobLauncher` para iniciar o processo de lote. É possível definir parâmetros e propriedades executar o job
   

## Componentes Spring Batch:
![Diagram Batch](https://raw.githubusercontent.com/fabioallima/spring-batch/refs/heads/main/src/main/resources/assets/diagram-batch.svg)


## Requisitos de Sistema

- Java Development Kit (JDK) 21
- Maven 3.6+

## Docker Compose para MySQL:

Script: resources/docker/docker-compose.yml

Subir a estrutura:
``` 
docker-compose up -d
```

Parar a estrutura:
```
docker-compose down
```
#### Dados de conexão para o phpMyAdmin
- Server: mysql-docker
- Usuário: root / user
- Senha: 1234567

#### Dados de conexão para o MySQL Workbench
- Host: 127.0.0.1
- Porta: 3307
- Usuário: root / user

## Como Executar o Projeto

```bash
# Clonar o repositório
git clone https://github.com/fabioallima/spring-batch

# Entrar no diretório do projeto
cd dslist

# Construir o projeto
mvn clean install

# Executar os testes
mvn test

# Iniciar a aplicação
mvn spring-boot:run
```

## Autor

Fabio Almeida Lima

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Fabio%20Almeida%20Lima-blue.svg)](https://www.linkedin.com/in/fabio-alima/)

