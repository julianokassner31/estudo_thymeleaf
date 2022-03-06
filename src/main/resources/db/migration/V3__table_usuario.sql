CREATE TABLE usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	login VARCHAR(20) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	empresa_id INT NOT NULL,
	data_cadastro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	CONSTRAINT uc_usuario_login_empresa UNIQUE (empresa_id, login),
	FOREIGN KEY(empresa_id) REFERENCES empresa(id) ON DELETE CASCADE
);