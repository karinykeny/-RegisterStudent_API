CREATE TABLE estudante (
	matricula int AUTO_INCREMENT PRIMARY KEY,
	nome varchar(30) NOT NULL,
	sobrenome varchar(70) NOT NULL,
	email varchar(100) NULL,
	cpf varchar(11) UNIQUE NOT NULL,
	rua varchar(100) NULL,
	numero int NULL,
	bairro varchar(50) NULL,
	cidade varchar(50) NULL,
	uf char(2) NULL,
	cep varchar(8) NULL,
	complemento varchar(30) NULL
) AUTO_INCREMENT=100;

CREATE TABLE telefone (
	id int UNSIGNED NOT NULL AUTO_INCREMENT, 
	numero_telefone varchar(11) NULL,
	matricula int,
	PRIMARY KEY (id),
	KEY matricula (matricula),
	CONSTRAINT matricula FOREIGN KEY (matricula) REFERENCES estudante (matricula)
);