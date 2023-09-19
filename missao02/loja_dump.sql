CREATE SEQUENCE Seq_PessoaID
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE;

CREATE TABLE usuario (
    usuarioID INT PRIMARY KEY IDENTITY(1,1),
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

CREATE TABLE pessoa (
    pessoaID INT DEFAULT NEXT VALUE FOR Seq_PessoaID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    logradouro VARCHAR(200) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE pessoaFisica (
    pessoaFisicaID INT PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    FOREIGN KEY (pessoaFisicaID) REFERENCES pessoa(pessoaID)
);

CREATE TABLE pessoaJuridica (
    pessoaJuridicaID INT PRIMARY KEY,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    FOREIGN KEY (pessoaJuridicaID) REFERENCES pessoa(pessoaID)
);

CREATE TABLE produto (
    produtoID INT PRIMARY KEY IDENTITY(1,1),
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    precoVenda DECIMAL(10, 2) NOT NULL
);

CREATE TABLE movimento (
    movimentoID INT PRIMARY KEY IDENTITY(1,1),
    usuarioID INT NOT NULL,
    pessoaID INT NOT NULL,
    produtoID INT NOT NULL,
    quantidade INT NOT NULL,
    tipo CHAR(1) NOT NULL, -- 'E' para entrada (compra) ou 'S' para saída (venda)
    valorUnitario DECIMAL(10, 2) NOT NULL,
    dataMovimento DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (usuarioID) REFERENCES usuario(usuarioID),
    FOREIGN KEY (pessoaID) REFERENCES pessoa(pessoaID),
    FOREIGN KEY (produtoID) REFERENCES produto(produtoID)
);
