# CRUD Two Entities (Cliente ↔ Pedido)

Este projeto é uma aplicação Java Maven com CRUD completo para duas entidades relacionadas (`Cliente` e `Pedido`), integração com banco de dados MySQL usando JPA/Hibernate, menu de terminal para entrada de dados e consumo da API pública ViaCEP para enriquecer o endereço do cliente por CEP.

## Requisitos atendidos

- CRUD completo para `Cliente` e `Pedido` (Create/Read/Update/Delete)
- Relação `Cliente` 1:N `Pedido`
- Conexão com MySQL usando ORM JPA/Hibernate
- Menu interativo via `Scanner` para inserir, listar, atualizar e deletar
- Validação básica e tratamento de erros de entrada
- Consumo de API externa ViaCEP para buscar endereço por CEP
- Arquivo `.env` para configurações sensíveis
- `README.md` explicativo

## Estrutura do projeto

- `src/main/java/com/example/app` - classe principal e utilitários
- `src/main/java/com/example/model` - entidades JPA `Cliente` e `Pedido`
- `src/main/java/com/example/dao` - DAOs para persistência
- `src/main/java/com/example/service` - integração ViaCEP
- `src/main/resources/META-INF/persistence.xml` - configuração JPA
- `sql/init.sql` - script para criar banco e tabelas
- `.env.example` - exemplo de arquivo de ambiente

## Configuração

1. Crie o banco de dados MySQL, por exemplo `crud_db`.
2. Copie `.env.example` para `.env` na raiz do projeto:

```bash
copy .env.example .env
```

3. Abra `.env` e confirme os valores:

```text
JDBC_URL=jdbc:mysql://localhost:3306/crud_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
JDBC_USER=root
JDBC_PASS=YOUR_DB_PASSWORD_HERE
JDBC_DRIVER=com.mysql.cj.jdbc.Driver

HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect
HIBERNATE_HBM2DDL=update
HIBERNATE_SHOW_SQL=false
```

> Se o MySQL estiver em outro host ou porta, ajuste `JDBC_URL`.

4. Se preferir, use o script SQL para criar o banco e as tabelas:

```bash
mysql -u root -p < sql/init.sql
```

## Como rodar

1. Build do projeto:

```bash
mvn clean package
```

2. Execute o jar gerado:

```bash
java -jar target/crud-two-entities-1.0-SNAPSHOT-jar-with-dependencies.jar
```

3. Use o menu para cadastrar, listar, atualizar e deletar clientes e pedidos.

## Observações

- O JDBC URL já inclui `allowPublicKeyRetrieval=true` para compatibilidade com autenticação MySQL moderna.
- O `hibernate.hbm2ddl.auto=update` cria/atualiza tabelas automaticamente durante a execução.
- A API ViaCEP não exige chave e é usada para preencher o endereço do cliente com base no CEP informado.

## Commit e entrega

- Projeto Maven para `projeto-poo` com todos os requisitos do desafio.
- Garanta que o commit esteja feito antes de `22/05/2026 23:59`.
- Não inclua `.env` em commits públicos se houver credenciais reais.
