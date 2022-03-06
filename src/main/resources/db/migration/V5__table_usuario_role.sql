CREATE TABLE usuario_role(
	id int AUTO_INCREMENT PRIMARY KEY,
	id_usuario INT NOT NULL,
	id_role INT NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_role) REFERENCES role(id)
);