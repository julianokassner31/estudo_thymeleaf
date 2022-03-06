ALTER TABLE produto 
	ADD COLUMN usuario_id INT NOT NULL;
ALTER TABLE produto
	ADD FOREIGN KEY (usuario_id) REFERENCES usuario (id);