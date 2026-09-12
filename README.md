# Gerenciar Pedido

README aplicação backend de exemplo construída com Java e Spring Boot para gerenciar usuários e produtos, com autenticação JWT, controle por roles e tratamento centralizado de erros.

## Visão geral

O objetivo deste projeto é demonstrar as principais práticas do ecossistema Java/Spring em uma aplicação backend simples e didática:

- Spring Boot (Java 21)
- Spring Data JPA para persistência
- Spring Security para autenticação e autorização (JWT)
- Validação de payloads e tratamento global de exceções
- DTOs para entrada/saída e mapeamento de entidades
- Paginação e filtros para endpoints de consulta
- Integração com SpringDoc/OpenAPI (Swagger)

## Destaques / Novas implementações

- Autenticação baseada em JWT (JSON Web Token)
  - `TokenService` gera e valida tokens assinados com HMAC. O segredo é configurável via propriedade `api.security.jwt.secret`.
  - Tokens possuem tempo de expiração (padrão: 15 minutos).
- Filtro de segurança (`SecurityFiler`)
  - Intercepta requisições, recupera token do header `Authorization: Bearer <TOKEN>`, valida e popula o `SecurityContext` com as authorities do usuário.
- Controle por roles/authorities
  - Endpoints protegidos com `@PreAuthorize` (ex.: `hasRole('GESTOR')`, `hasAuthority('ADMIN')`). Veja os controllers em `src/main/java/br/com/pedido/controller`.
- Tratamento global de exceções
  - `GlobalExceptionHandler` padroniza respostas de erro (validação, autenticação, autorização, token expirado, integridade de dados, registros não encontrados).
- Filtros e paginação
  - Endpoints de pesquisa permitem filtros opcionais e paginação (`Page<T>`).

## Requisitos

- Java 21 (JDK)
- MySQL (ou outro banco compatível) para desenvolvimento local
- Git (opcional)
- Conexão com a internet para baixar dependências Maven

Observação: o projeto inclui o Maven Wrapper (`mvnw` / `mvnw.cmd`).

## Configuração

Edite o arquivo `src/main/resources/application.properties` com suas configurações. Exemplo mínimo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gerencia_pedido?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Porta da aplicação
server.port=8080

# Segredo JWT (NUNCA COMITE SECRETS EM REPOSITÓRIO)
api.security.jwt.secret=troque-por-um-segredo-tenebroso
```

Dica: prefira usar variáveis de ambiente ou mecanismos de secret management para `api.security.jwt.secret` em ambientes reais.

## Como executar

No Windows (cmd):

```cmd
cd path\to\gerenciar-pedido
mvnw.cmd clean package -DskipTests
mvnw.cmd spring-boot:run
```

Ou execute o JAR gerado:

```cmd
java -jar target\gerenciar-pedido-0.0.1-SNAPSHOT.jar
```

No Unix / macOS:

```bash
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```

## Documentação da API (Swagger / OpenAPI)

Com a aplicação em execução, a interface Swagger estará disponível em:

- http://localhost:8080/gerenciar-pedido/api/v1/swagger-ui/index.html
- ou http://localhost:8080/gerenciar-pedido/api/v1/swagger-ui/index.html

## Autenticação (JWT)

Endpoint de autenticação:

- POST /autenticacao
  - Recebe credenciais (username/password) e retorna um token JWT no corpo da resposta.

Formato do request (exemplo):

```json
{
  "username": "usuario",
  "password": "senha123"
}
```

Resposta (exemplo):

```json
{
  "token": "eyJhbGciOiJI..."
}
```

Uso do token nos endpoints protegidos:

- Adicione o header HTTP `Authorization: Bearer <TOKEN>` nas requisições.

Observações:
- Tokens expiram (padrão 15 minutos). Se o token expirar, a API retorna HTTP 401 com mensagem apropriada.
- O filtro `SecurityFiler` valida e popula o contexto de segurança automaticamente.

## Endpoints principais (resumo)

Base URL: http://localhost:8080

Autenticação
- POST /autenticacao

Usuários (`UserController`)
- GET /user — lista todos (requer autenticação e permissões conforme `@PreAuthorize`)
- GET /user/recuperar/{id} — recupera por id
- GET /user/pesquisar?userName=...&status=... — pesquisa por filtros
- POST /user — cria usuário
- PATCH /user — atualiza usuário (envie `id` no payload)
- DELETE /user/{id} — remove usuário

Produtos (`ProdutoController`)
- GET /produto — lista todos
- GET /produto/recuperar/{id} — recupera por id
- GET /produto/pesquisar?nomeProduto=...&status=...&descricao=... — pesquisa por filtros
- GET /produto/pesquisa-paginada?nomeProduto=...&pagina=0&tamanhoPagina=10 — pesquisa paginada
- POST /produto — cria produto (requer role `GESTOR`)
- PATCH /produto — atualiza produto (requer role `GESTOR`)
- DELETE /produto/{id} — exclui produto (requer role `GESTOR`)

Consulte os controllers em `src/main/java/br/com/pedido/controller` para as regras de autorização aplicadas em cada rota.

## Formato de erros

Erros padronizados são retornados com o modelo `ErroResposta`:

```json
{
  "status": 422,
  "mensagem": "Erro de validação",
  "erros": [
    { "campo": "nome", "mensagem": "Nome é obrigatório" }
  ]
}
```

Códigos de status comuns retornados pelo `GlobalExceptionHandler`:
- 400 Bad Request — conflitos e erros genéricos
- 401 Unauthorized — login inválido, token inválido/expirado
- 403 Forbidden — acesso negado (sem permissão)
- 422 Unprocessable Content — erros de validação ou registro não encontrado
- 500 Internal Server Error — erros não tratados

## Exemplos de uso (cURL)

1) Autenticar e obter token

```bash
curl -X POST "http://localhost:8080/autenticacao" -H "Content-Type: application/json" -d '{"username":"admin","password":"admin123"}'
```

2) Consumir endpoint protegido

```bash
curl -X GET "http://localhost:8080/produto" -H "Accept: application/json" -H "Authorization: Bearer eyJhbGciOiJI..."
```

3) Criar produto (requer role `GESTOR`)

```bash
curl -X POST "http://localhost:8080/produto" -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d '{"nome":"Caneta Azul","descricao":"Caneta esferográfica azul","preco":2.5,"estoque":150,"codigoBarras":"7891234567890","status":true,"categoria":{"id":1}}'
```

## Testes

Execute a suíte de testes com Maven Wrapper:

```cmd
mvnw.cmd test
```

Os relatórios de testes são gerados em `target/surefire-reports`.

## Boas práticas

- Não versionar segredos (usar variáveis de ambiente ou secret manager).
- Habilite o plugin do Lombok na sua IDE.
- Use o Maven Wrapper para builds reprodutíveis.
- Mantenha scripts de criação/população do banco em `src/main/resources/scripts`.

## Contato e licença

Este projeto pode ser usado para estudos e demonstrações. Adicione uma licença formal ao repositório conforme sua necessidade (por exemplo, MIT, Apache-2.0, etc.).