CREATE TABLE produto(
	id int AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100) NOT NULL,
	descricao varchar(255),
	valor decimal(15,2) NOT NULL,
	data_cadastro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	empresa_id INT NOT NULL,
	FOREIGN KEY(empresa_id) REFERENCES empresa(id) ON DELETE CASCADE
);