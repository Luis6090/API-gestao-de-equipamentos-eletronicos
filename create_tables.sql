-- Comandos SQL CREATE TABLE para PostgreSQL

-- Tabela USUARIO
CREATE TABLE USUARIO (
    email VARCHAR(255) PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    status_confirmacao_email BOOLEAN DEFAULT FALSE
);

-- Tabela TIPO_EQUIPAMENTO
CREATE TABLE TIPO_EQUIPAMENTO (
    id_tipo_equipamento SERIAL PRIMARY KEY,
    nome_tipo VARCHAR(100) UNIQUE NOT NULL
);

-- Tabela PARAMETROS_SISTEMA
CREATE TABLE PARAMETROS_SISTEMA (
    id_parametro SERIAL PRIMARY KEY,
    tempo_medio_reposicao INT NOT NULL,
    tempo_medio_consumo_estoque INT NOT NULL,
    tempo_medio_envio INT NOT NULL,
    taxa_media_equipamentos_defeituosos DECIMAL(5,2) NOT NULL,
    estoque_minimo_seguranca INT NOT NULL
);

-- Tabela ENDEREÇO
CREATE TABLE ENDERECO(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cep CHAR(8) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    complemento TEXT NOT NULL,
    unidade VARCHAR(255) NOT NULL,
    bairro VARCHAR(30) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf CHAR(2) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    regiao VARCHAR(20) NOT NULL,
    ibge CHAR(7) NOT NULL,
    gia CHAR(4) NOT NULL,
    ddd CHAR(2) NOT NULL,
    siafi CHAR(7) NOT NULL
);

-- Tabela COLABORADOR
CREATE TABLE COLABORADOR (
    id_colaborador SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(13) NOT NULL,
    cep CHAR(8) NOT NULL,
    data_aniversario DATE NOT NULL,
    data_inicio_contrato DATE NOT NULL,
    data_rescisao_contrato DATE NOT NULL,
    especificacoes_equipamentos_necessarios TEXT,
    email_usuario VARCHAR(255) UNIQUE,
    FOREIGN KEY (email_usuario) REFERENCES USUARIO(email)
);

-- Tabela EQUIPAMENTO
CREATE TABLE EQUIPAMENTO (
    id_equipamento SERIAL PRIMARY KEY,
    especificacoes TEXT,
    numero_serie VARCHAR(255) UNIQUE NOT NULL,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    data_aquisicao DATE,
    tempo_uso INT,
    id_tipo_equipamento INT NOT NULL,
    FOREIGN KEY (id_tipo_equipamento) REFERENCES TIPO_EQUIPAMENTO(id_tipo_equipamento)
);

-- Tabela EMPRESTIMO_DEVOLUCAO
CREATE TABLE EMPRESTIMO_DEVOLUCAO (
    id_emprestimo_devolucao SERIAL PRIMARY KEY,
    id_equipamento INT NOT NULL,
    id_colaborador INT NOT NULL,
    data_entrega DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (id_equipamento) REFERENCES EQUIPAMENTO(id_equipamento),
    FOREIGN KEY (id_colaborador) REFERENCES COLABORADOR(id_colaborador)
);

-- Tabela ALERTA
CREATE TABLE ALERTA (
    id_alerta SERIAL PRIMARY KEY,
    tipo_alerta VARCHAR(100) NOT NULL,
    data_hora_geracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    descricao TEXT,
    id_equipamento INT,
    id_parametro INT,
    FOREIGN KEY (id_equipamento) REFERENCES EQUIPAMENTO(id_equipamento),
    FOREIGN KEY (id_parametro) REFERENCES PARAMETROS_SISTEMA(id_parametro)
);

CREATE TABLE ESTOQUE (
    id_estoque INT PRIMARY KEY,
    id_equipamento INT NOT NULL,
    quantidade_disponivel INT NOT NULL,
    quantidade_em_uso INT NOT NULL,
    quantidade_defeituosa INT NOT NULL,
    FOREIGN KEY (id_equipamento) REFERENCES EQUIPAMENTO(id_equipamento)
);

