CREATE TABLE estoque(
	id INT AUTO_INCREMENT PRIMARY KEY,
	produto_id INT NOT NULL,
	usuario_id INT NOT NULL,
	qtd decimal(15,2) NOT NULL,
	dt_lcto TIMESTAMP NOT NULL,
	tp_lcto varchar(10) NOT NULL,
	FOREIGN KEY(produto_id) REFERENCES produto(id),
	FOREIGN KEY(usuario_id) REFERENCES usuario(id),
	CONSTRAINT tp_lcto_const CHECK ((tp_lcto = 'ENTRADA' or tp_lcto = 'SAIDA'))
);