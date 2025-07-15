-- TIPO_EQUIPAMENTO
INSERT INTO tipo_equipamento (nome_tipo) VALUES
('Notebook'),
('Monitor'),
('Mouse'),
('Teclado'),
('Headset'),
('Dock Station'),
('Webcam'),
('HD Externo'),
('Suporte Ergonômico'),
('Tablet');

-- EQUIPAMENTO
INSERT INTO equipamento (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Intel i7, 16GB RAM, SSD 512GB', 'SN123456A', 'Dell', 'XPS 13', '2023-03-15', 16, 1),
('UltraWide 34", HDMI/DP', 'SN234567B', 'LG', '34WL500', '2022-12-10', 19, 2),
('Sem fio, recarregável', 'SN345678C', 'Logitech', 'MX Master 3', '2023-06-01', 13, 3),
('Mecânico, RGB', 'SN456789D', 'Razer', 'BlackWidow', '2023-05-20', 14, 4),
('Com cancelamento de ruído', 'SN567890E', 'HyperX', 'Cloud II', '2023-01-18', 18, 5),
('USB-C, 2xHDMI, RJ45', 'SN678901F', 'Dell', 'WD19', '2022-09-10', 22, 6),
('Full HD 1080p', 'SN789012G', 'Logitech', 'C920', '2023-08-12', 11, 7),
('2TB, USB 3.0', 'SN890123H', 'Seagate', 'Expansion', '2023-02-01', 17, 8),
('Suporte ajustável de alumínio', 'SN901234I', 'ErgoTech', 'EZStand', '2022-11-01', 21, 9),
('64GB, Wi-Fi', 'SN012345J', 'Apple', 'iPad Air', '2023-07-05', 12, 10);

-- ESTOQUE
-- A PK (id_estoque) será igual ao id_equipamento, pois é @MapsId
INSERT INTO estoque (id_estoque, id_equipamento, quantidade_disponivel, quantidade_em_uso, quantidade_defeituosa) VALUES
(1, 1, 5, 3, 2),
(2, 2, 6, 2, 2),
(3, 3, 15, 5, 0),
(4, 4, 10, 8, 2),
(5, 5, 7, 3, 0),
(6, 6, 3, 5, 2),
(7, 7, 6, 2, 2),
(8, 8, 4, 4, 2),
(9, 9, 8, 1, 1),
(10, 10, 2, 6, 2);

-- PARAMETROS_SISTEMA
INSERT INTO parametros_sistema (
    tempo_medio_reposicao,
    tempo_medio_consumo_estoque,
    tempo_medio_envio,
    taxa_media_equipamentos_defeituosos,
    estoque_minimo_seguranca
) VALUES
(10, 30, 5, 0.15, 3),
(12, 28, 6, 0.12, 4),
(15, 35, 7, 0.18, 5),
(9, 25, 4, 0.10, 2),
(11, 29, 5, 0.14, 3),
(10, 31, 6, 0.13, 4),
(13, 27, 5, 0.11, 3),
(14, 33, 7, 0.16, 6),
(8, 26, 4, 0.09, 2),
(10, 30, 5, 0.12, 3);
