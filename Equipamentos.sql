-- Notebook (id_tipo_equipamento = 1)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Notebook com 16GB RAM, SSD 512GB', 'NB001', 'Dell', 'Inspiron', '2023-01-10', 12, 1),
('Notebook com 8GB RAM, HD 1TB', 'NB002', 'Lenovo', 'ThinkPad', '2023-02-15', 10, 1),
('Notebook com 16GB RAM, SSD 256GB', 'NB003', 'HP', 'Pavilion', '2023-03-20', 8, 1),
('Notebook com 32GB RAM, SSD 1TB', 'NB004', 'Apple', 'MacBook Pro', '2023-04-25', 6, 1),
('Notebook com 4GB RAM, HD 500GB', 'NB005', 'Acer', 'Aspire', '2023-05-30', 4, 1);

-- Carregador de notebook (id_tipo_equipamento = 3)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Carregador Dell 65W', 'CN001', 'Dell', '65W', '2023-01-12', 12, 3),
('Carregador Lenovo 90W', 'CN002', 'Lenovo', '90W', '2023-02-18', 11, 3),
('Carregador HP 45W', 'CN003', 'HP', '45W', '2023-03-22', 9, 3),
('Carregador Apple Magsafe', 'CN004', 'Apple', 'Magsafe', '2023-04-28', 7, 3),
('Carregador Acer 65W', 'CN005', 'Acer', '65W', '2023-06-01', 5, 3);

-- Computador (id_tipo_equipamento = 4)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Desktop i5, 8GB RAM', 'PC001', 'Dell', 'OptiPlex', '2023-01-15', 12, 4),
('Desktop Ryzen 5, 16GB RAM', 'PC002', 'HP', 'EliteDesk', '2023-02-20', 10, 4),
('Desktop i7, 32GB RAM', 'PC003', 'Lenovo', 'ThinkCentre', '2023-03-25', 8, 4),
('Desktop i3, 4GB RAM', 'PC004', 'Positivo', 'Master', '2023-04-30', 6, 4),
('Desktop Ryzen 3, 8GB RAM', 'PC005', 'Asus', 'VivoPC', '2023-06-05', 4, 4);

-- Monitor (id_tipo_equipamento = 5)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Monitor 21.5 Full HD', 'MON001', 'LG', 'Flatron', '2023-01-18', 12, 5),
('Monitor 24 LED', 'MON002', 'Samsung', 'Curvo', '2023-02-22', 11, 5),
('Monitor 27 IPS', 'MON003', 'Dell', 'Ultrasharp', '2023-03-28', 9, 5),
('Monitor 19 VGA', 'MON004', 'AOC', 'Classic', '2023-04-15', 7, 5),
('Monitor 22 Full HD', 'MON005', 'Philips', 'Brilliance', '2023-06-10', 5, 5);

-- Cabo HDMI (id_tipo_equipamento = 6)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Cabo HDMI 1.5m', 'HDMI001', 'Multilaser', 'Basic', '2023-01-20', 12, 6),
('Cabo HDMI 2m', 'HDMI002', 'Positivo', 'HD2', '2023-02-25', 10, 6),
('Cabo HDMI 3m', 'HDMI003', 'Dell', 'HDMI3', '2023-03-30', 8, 6),
('Cabo HDMI 1m', 'HDMI004', 'Philips', 'Gold', '2023-04-10', 6, 6),
('Cabo HDMI 5m', 'HDMI005', 'Knup', 'Premium', '2023-06-15', 4, 6);

-- Celular Android (id_tipo_equipamento = 7)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Android 12, 128GB', 'AND001', 'Samsung', 'A52', '2023-01-25', 12, 7),
('Android 11, 64GB', 'AND002', 'Motorola', 'Moto G', '2023-02-28', 11, 7),
('Android 13, 256GB', 'AND003', 'Xiaomi', 'Redmi Note', '2023-03-05', 10, 7),
('Android 10, 32GB', 'AND004', 'LG', 'K10', '2023-04-12', 8, 7),
('Android 14, 512GB', 'AND005', 'Asus', 'Zenfone', '2023-06-20', 6, 7);

-- Celular iOS (id_tipo_equipamento = 8)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('iOS 16, 128GB', 'IOS001', 'Apple', 'iPhone 13', '2023-01-30', 12, 8),
('iOS 15, 64GB', 'IOS002', 'Apple', 'iPhone 12', '2023-02-15', 11, 8),
('iOS 14, 256GB', 'IOS003', 'Apple', 'iPhone 11', '2023-03-18', 9, 8),
('iOS 13, 32GB', 'IOS004', 'Apple', 'iPhone SE', '2023-04-25', 7, 8),
('iOS 17, 512GB', 'IOS005', 'Apple', 'iPhone 14', '2023-06-25', 5, 8);

-- Carregador de celular (id_tipo_equipamento = 9)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Carregador USB-C 25W', 'CC001', 'Samsung', 'Fast', '2023-01-12', 12, 9),
('Carregador Turbo Power', 'CC002', 'Motorola', 'Turbo', '2023-02-14', 11, 9),
('Carregador Lightning 20W', 'CC003', 'Apple', 'Original', '2023-03-17', 9, 9),
('Carregador Universal 15W', 'CC004', 'Multilaser', 'Smart', '2023-04-23', 7, 9),
('Carregador Quick Charge', 'CC005', 'Xiaomi', 'QC3', '2023-06-27', 5, 9);

-- Fone de ouvido (id_tipo_equipamento = 10)
INSERT INTO EQUIPAMENTO (especificacoes, numero_serie, marca, modelo, data_aquisicao, tempo_uso, id_tipo_equipamento) VALUES
('Fone Bluetooth', 'FONE001', 'JBL', 'Tune 500BT', '2023-01-10', 12, 10),
('Fone com fio P2', 'FONE002', 'Samsung', 'Wired', '2023-02-11', 11, 10),
('Fone Lightning', 'FONE003', 'Apple', 'EarPods', '2023-03-12', 10, 10),
('Fone USB-C', 'FONE004', 'Motorola', 'Stereo', '2023-04-13', 9, 10),
('Fone TWS', 'FONE005', 'Xiaomi', 'Redmi Buds', '2023-06-30', 7, 10);