# CRUD Two Entities (Cliente ↔ Pedido)

Projeto Java Maven com CRUD completo para duas entidades relacionadas (`Cliente` e `Pedido`), integração com MySQL via JPA/Hibernate, menu CLI interativo e consumo da API externa ViaCEP para enriquecer endereço por CEP.

Requisitos atendidos:
- CRUD completo para `Cliente` e `Pedido` (Create/Read/Update/Delete)
- Relação `Cliente` 1:N `Pedido`
- Conexão com MySQL via JPA/Hibernate
- Menu interativo via `Scanner` para inserir/atualizar/deletar/listar
- Consumo da API ViaCEP para preencher `endereco` a partir do `cep`
- Arquivo `.env` para configurações sensíveis (`.env.example` incluído)
- `README.md` com instruções

Como rodar

1. Instale o MySQL e crie um banco de dados (por exemplo `crud_db`).

2. Copie `.env.example` para `.env` e ajuste os valores:

```
JDBC_URL=jdbc:mysql://localhost:3306/crud_db?useSSL=false&serverTimezone=UTC
JDBC_USER=root
JDBC_PASS=senha
```

3. Build com Maven:

```bash
mvn clean package
```

4. Rode o jar gerado (exemplo):

```bash
java -jar target/crud-two-entities-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Observações
- O projeto usa `hibernate.hbm2ddl.auto=update` por padrão para criar/atualizar as tabelas. Para produção, ajuste conforme necessário.
- A API ViaCEP não requer chave, mas depende de conexão de internet.
