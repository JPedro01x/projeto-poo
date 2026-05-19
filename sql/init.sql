CREATE DATABASE IF NOT EXISTS `crud_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `crud_db`;

-- Table: clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `cep` VARCHAR(20),
  `endereco` VARCHAR(512),
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_clientes_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(500) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `cliente_id` BIGINT,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_pedidos_cliente` (`cliente_id`),
  CONSTRAINT `fk_pedidos_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Example seed data (optional)
INSERT INTO `clientes` (`nome`, `email`, `cep`, `endereco`) VALUES
('João Silva', 'joao@example.com', '01001000', 'Praça da Sé, Sé - São Paulo/SP'),
('Maria Souza', 'maria@example.com', '20040030', 'Rua do Ouvidor, Centro - Rio de Janeiro/RJ');

INSERT INTO `pedidos` (`descricao`, `valor`, `cliente_id`) VALUES
('Entrega de livros', 120.50, 1),
('Serviço de consultoria', 500.00, 2);
crud_db
