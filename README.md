# Gerenciamento de Pessoas

API para gerenciamento de pessoas em uma universidade, desenvolvida com Spring Boot, MySQL, JPA, Hibernate e Lombok.

## Endpoints

- *POST* /api/pessoas: Cria uma nova pessoa.
- *GET* /api/pessoas/{id}: Busca uma pessoa por ID.
- *PUT* /api/pessoas/{id}: Atualiza uma pessoa existente.
- *DELETE* /api/pessoas/{id}: Deleta uma pessoa por ID.
- *GET* /api/pessoas/buscar?nome=Rafael&idade=18: Busca pessoas cujo nome começa com o valor informado e idade maior que o valor especificado.

## Configuração

1. Configure o MySQL com o banco universidade.
2. Atualize o application.properties com as credenciais do MySQL.
3. Execute o projeto com mvn spring-boot:run.

## Integrantes

- Giulia Karolina
- Luis Felipe
