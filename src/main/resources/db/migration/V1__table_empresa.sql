CREATE TABLE empresa(
	id int AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100) NOT NULL,
	cnpj varchar(255),
	data_cadastro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP()
);