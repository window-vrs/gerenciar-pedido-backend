# GERENCIA PEDIDO

Projeto para estudos das principais tecnologias do ecossistema Java e Spring, focado em exemplos práticos e didáticos para gerenciar pedidos.

## Visão geral

Este projeto é uma aplicação backend desenvolvida com Java 21 e Spring Boot. Ele reúne módulos e conceitos essenciais para aprendizado: Spring Data (JPA), Spring Security, validação, mapeamento de DTOs e boas práticas de projeto.

Tecnologias principais:

- Java 21
- Maven (wrapper incluído)
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- ModelMapper
- SpringDoc (OpenAPI/Swagger)

## Requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 21 (JDK)
- MySQL (ou outro banco compatível) para desenvolvimento local
- Git (opcional)
- Conexão de internet para baixar dependências Maven

Observação: o projeto já inclui o Maven Wrapper (`mvnw` / `mvnw.cmd`), então você não precisa ter Maven globalmente instalado.

## Estrutura do projeto

- `src/main/java` - código fonte Java
- `src/main/resources` - recursos (application.properties, scripts SQL)
- `src/main/resources/scripts` - scripts SQL para criar/ popular esquema inicial
- `pom.xml` - configuração do Maven e dependências

## Configuração do banco de dados

No arquivo `src/main/resources/application.properties` (ou `application.yml`) configure as propriedades de conexão com o banco. Exemplo mínimo para MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gerencia_pedido?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Se preferir, execute os scripts SQL localizados em `src/main/resources/scripts` para criar o esquema e dados iniciais.

## Como executar

A seguir há comandos para executar e empacotar a aplicação. Use o Maven Wrapper fornecido no projeto.

No Windows (cmd):

```cmd
mvnw.cmd clean package -DskipTests
mvnw.cmd spring-boot:run
```

No Unix / macOS:

```bash
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```

Após o package você também pode executar o JAR gerado:

```cmd
java -jar target\gerenciar-pedido-0.0.1-SNAPSHOT.jar
```

Observação: ajuste o nome do JAR caso a versão seja diferente.

## Endpoints e documentação da API

O projeto inclui integração com SpringDoc OpenAPI. Após subir a aplicação, acesse a interface Swagger em:

- http://localhost:8080/swagger-ui.html

ou

- http://localhost:8080/swagger-ui/index.html

(Se a URL padrão estiver diferente, verifique as configurações do SpringDoc ou `application.properties`.)

## Exemplos de endpoints

A seguir há exemplos de endpoints e exemplos de requisições baseados nos controllers do projeto.

Base URL: http://localhost:8080

1) Autenticação

- POST /autenticacao

Request JSON:

```json
{
  "userName": "usuario",
  "password": "senha123"
}
```

cURL:

```bash
curl -X POST "http://localhost:8080/autenticacao" -H "Content-Type: application/json" -d "{\"userName\": \"usuario\", \"password\": \"senha123\"}"
```

Resposta (exemplo): 200 OK

```json
{
  "id": 1,
  "userName": "usuario",
  "status": true,
  "roles": [
    { "id": 2, "nome": "USER", "descricao": "Usuário padrão" }
  ]
}
```

2) Usuários

- GET /user
  - Lista todos os usuários

cURL:

```bash
curl -X GET "http://localhost:8080/user" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- GET /user/recuperar/{id}
  - Recupera um usuário por ID

cURL:

```bash
curl -X GET "http://localhost:8080/user/recuperar/1" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- GET /user/pesquisar?userName=joao&status=true
  - Pesquisa por filtros (userName, status)

cURL:

```bash
curl -X GET "http://localhost:8080/user/pesquisar?userName=joao&status=true" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- POST /user
  - Cria um novo usuário

Request JSON:

```json
{
  "userName": "novoUsuario",
  "password": "senhaSegura",
  "observacao": "Usuário criado para testes",
  "status": true,
  "roles": [ { "id": 2 } ]
}
```

cURL:

```bash
curl -X POST "http://localhost:8080/user" -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d "{\"userName\":\"novoUsuario\",\"password\":\"senhaSegura\",\"observacao\":\"Usuário criado para testes\",\"status\":true,\"roles\":[{\"id\":2}]}"
```

- PATCH /user
  - Atualiza um usuário (envie `id` no payload)

Request JSON:

```json
{
  "id": 1,
  "userName": "usuarioAtualizado",
  "password": "novaSenha123",
  "observacao": "Atualizado",
  "status": true,
  "roles": [ { "id": 2 } ]
}
```

cURL:

```bash
curl -X PATCH "http://localhost:8080/user" -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d "{\"id\":1,\"userName\":\"usuarioAtualizado\",\"password\":\"novaSenha123\",\"observacao\":\"Atualizado\",\"status\":true,\"roles\":[{\"id\":2}]}"
```

- DELETE /user/{id}
  - Remove um usuário

cURL:

```bash
curl -X DELETE "http://localhost:8080/user/1" -H "Authorization: Bearer <TOKEN>"
```

3) Produtos

- GET /produto
  - Lista todos os produtos

cURL:

```bash
curl -X GET "http://localhost:8080/produto" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- GET /produto/recuperar/{id}
  - Recupera um produto por ID

cURL:

```bash
curl -X GET "http://localhost:8080/produto/recuperar/1" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- GET /produto/pesquisar?nomeProduto=Caneta&status=true&descricao=azul
  - Pesquisa por filtros

cURL:

```bash
curl -X GET "http://localhost:8080/produto/pesquisar?nomeProduto=Caneta&status=true&descricao=azul" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- GET /produto/pesquisa-paginada?nomeProduto=Caneta&pagina=0&tamanhoPagina=10
  - Pesquisa paginada

cURL:

```bash
curl -X GET "http://localhost:8080/produto/pesquisa-paginada?nomeProduto=Caneta&pagina=0&tamanhoPagina=10" -H "Accept: application/json" -H "Authorization: Bearer <TOKEN>"
```

- POST /produto
  - Cria um novo produto

Request JSON:

```json
{
  "nome": "Caneta Azul",
  "descricao": "Caneta esferográfica azul",
  "preco": 2.5,
  "estoque": 150,
  "codigoBarras": "7891234567890",
  "status": true,
  "categoria": { "id": 1 }
}
```

cURL:

```bash
curl -X POST "http://localhost:8080/produto" -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d "{\"nome\":\"Caneta Azul\",\"descricao\":\"Caneta esferográfica azul\",\"preco\":2.5,\"estoque\":150,\"codigoBarras\":\"7891234567890\",\"status\":true,\"categoria\":{\"id\":1}}"
```

- PATCH /produto
  - Atualiza um produto (envie `id` no payload)

Request JSON:

```json
{
  "id": 1,
  "nome": "Caneta Azul Premium",
  "descricao": "Caneta esferográfica azul - versão premium",
  "preco": 3.75,
  "estoque": 120,
  "codigoBarras": "7891234567890",
  "status": true,
  "categoria": { "id": 1 }
}
```

cURL:

```bash
curl -X PATCH "http://localhost:8080/produto" -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d "{\"id\":1,\"nome\":\"Caneta Azul Premium\",\"descricao\":\"Caneta esferográfica azul - versão premium\",\"preco\":3.75,\"estoque\":120,\"codigoBarras\":\"7891234567890\",\"status\":true,\"categoria\":{\"id\":1}}"
```

- DELETE /produto/{id}
  - Remove um produto

cURL:

```bash
curl -X DELETE "http://localhost:8080/produto/1" -H "Authorization: Bearer <TOKEN>"
```

## Testes

Para executar os testes unitários e de integração:

```cmd
mvnw.cmd test
```

## Observações sobre desenvolvimento

- Lombok: o projeto usa Lombok para reduzir boilerplate. Instale o plugin Lombok na sua IDE e habilite o suporte a anotações para evitar erros de compilação/IDE.
- Perfil de execução: para perfis específicos (dev, prod), use `-Dspring.profiles.active=dev` ou configure variáveis de ambiente conforme necessário.

## Boas práticas sugeridas

- Use o Maven Wrapper para garantir que todos usem a mesma versão do Maven.
- Nunca comite credenciais reais no repositório; prefira variáveis de ambiente ou arquivos de configuração ignorados.
- Mantenha os scripts SQL em `src/main/resources/scripts` sob versionamento para reprodução do ambiente.

## Como contribuir

1. Fork do repositório.
2. Crie uma branch com a sua feature: `git checkout -b feature/nome-da-feature`.
3. Faça commits pequenos e com mensagens descritivas.
4. Abra um Pull Request descrevendo a mudança.

## Contato

Para dúvidas ou sugestões, abra uma issue no repositório ou envie um e-mail ao mantenedor do projeto.

## Licença

Este projeto pode ser usado para estudos e demonstrações. Adicione uma licença formal ao repositório conforme sua necessidade (por exemplo, MIT, Apache-2.0, etc.).

