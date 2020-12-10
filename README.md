# Pessoas API

## Objetivo
Disponibilizar uma interface web que acessa algumas APIs REST para gerenciar operações de listagem, cadastro, edição e exclusão de pessoas.

## Componentes do projeto
- Maven (embarcado)
- Java 8
- Spring Boot (2.2.11-RELEASE)
- Thymeleaf
- Spring JDBC
- Banco de dados H2 (in memory)

## Build do projeto e teste unitário
A partir do diretório raiz do projeto, executar:
```
[Unix]   $ ./mvnw clean install
[Windows]> mvnw.bat clean install
```

## Execução do projeto
A partir do diretório raiz do projeto, executar:
```
[Unix]   $ ./mvnw spring-boot:run
[Windows]> mvnw.bat spring-boot:run
```
- **note**: inicia na porta 8080 com a tabela de pessoas vazia.

## Utilizando o sistema
Acesse a tela principal através do endereço http://localhost:8080/pessoas-api/, onde mostrará o menu com as opções: **Listar Pessoas** e **Cadastrar Pessoa**.

Cada tela tem o link para a Home.

## Autoria
GitHub: [willesreis](https://github.com/willesreis)