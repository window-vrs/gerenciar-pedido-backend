/*==============================================================*/
/* Table: TB_ROLE                                               */
/*==============================================================*/
INSERT INTO TB_ROLE (
    STR_NOME,
    STR_DESCRICAO,
    B_STATUS
) VALUES
('ADMIN', 'Administrador', TRUE),
('USER', 'Usuario comum', TRUE),
('GESTOR', 'Gestor sistema', TRUE);

/*==============================================================*/
/* Table: TB_USER                                               */
/*==============================================================*/
--- senha padrão: 123456
INSERT INTO TB_USER (
    USERNAME,
    PASSWORD,
    STATUS
) VALUES
('joao.silva', '$2a$10$BTXO8bI04PneRqZgtJQ3feUKthMgyaTGCrqojbqo6iFk.y94.oP9C', TRUE),
('maria.souza', '$2a$10$BTXO8bI04PneRqZgtJQ3feUKthMgyaTGCrqojbqo6iFk.y94.oP9C', TRUE),
('carlos.lima', '$2a$10$BTXO8bI04PneRqZgtJQ3feUKthMgyaTGCrqojbqo6iFk.y94.oP9C', TRUE);


/*==============================================================*/
/* Table: RL_ROLE_USUARIO                                       */
/*==============================================================*/
-- João -> ADMIN
INSERT INTO RL_ROLE_USUARIO (
    ID_USUARIO,
    ID_ROLE
) VALUES (
    1, 1
);

/*==============================================================*/
/* Table: RL_ROLE_USUARIO                                       */
/*==============================================================*/
-- Maria -> USER
INSERT INTO RL_ROLE_USUARIO (
    ID_USUARIO,
    ID_ROLE
) VALUES (
    2, 2
);

/*==============================================================*/
/* Table: RL_ROLE_USUARIO                                       */
/*==============================================================*/
-- Carlos -> GESTOR
INSERT INTO RL_ROLE_USUARIO (
    ID_USUARIO,
    ID_ROLE
) VALUES (
    3, 3
);


/*==============================================================*/
/* Table: TB_CATEGORIA_PRODUTO                                  */
/*==============================================================*/
INSERT INTO TB_CATEGORIA_PRODUTO (STR_NOME, STR_DESCRICAO, B_STATUS)
VALUES
    ('Eletrônicos', 'Produtos eletrônicos em geral', 1),
    ('Informática', 'Computadores e acessórios', 1),
    ('Celulares', 'Smartphones e acessórios', 1);

    
/*==============================================================*/
/* Table: TB_PRODUTO                                            */
/*==============================================================*/
INSERT INTO TB_PRODUTO (
    ID_CATEGORIA,
    STR_nome,
    STR_descricao,
    D_preco,
    IT_estoque,
    STR_codigo_barras,
    B_STATUS,
    DT_CADASTRO,
    DT_ATUALIZADO
)
VALUES
(1, 'Smart TV 43"', 'Smart TV LED 43 polegadas Full HD', 1799.90, 12, '789000000002', TRUE, NOW(), NOW()),
(1, 'Smart TV 50"', 'Smart TV 4K 50 polegadas', 2499.90, 10, '789000000003', TRUE, NOW(), NOW()),
(1, 'Smart TV 55"', 'Smart TV 4K 55 polegadas', 3299.90, 8, '789000000004', TRUE, NOW(), NOW()),
(1, 'Smart TV 65"', 'Smart TV 4K 65 polegadas', 4599.90, 5, '789000000005', TRUE, NOW(), NOW()),
(1, 'Soundbar 2.1', 'Soundbar com subwoofer sem fio', 899.90, 20, '789000000006', TRUE, NOW(), NOW()),
(1, 'Caixa de Som Bluetooth', 'Caixa de som portátil Bluetooth', 249.90, 35, '789000000007', TRUE, NOW(), NOW()),
(1, 'Home Theater 5.1', 'Sistema de áudio Home Theater 5.1 canais', 1899.90, 7, '789000000008', TRUE, NOW(), NOW()),
(1, 'Projetor Full HD', 'Projetor com resolução Full HD', 2299.90, 6, '789000000009', TRUE, NOW(), NOW()),
(1, 'Projetor 4K', 'Projetor com suporte a resolução 4K', 4299.90, 4, '789000000010', TRUE, NOW(), NOW()),
(1, 'Fone Bluetooth', 'Fone de ouvido Bluetooth sem fio', 199.90, 50, '789000000011', TRUE, NOW(), NOW()),
(1, 'Fone Bluetooth Pro', 'Fone Bluetooth com cancelamento de ruído', 599.90, 25, '789000000012', TRUE, NOW(), NOW()),
(1, 'Soundbar Premium', 'Soundbar premium com Dolby Atmos', 2499.90, 9, '789000000013', TRUE, NOW(), NOW()),
(1, 'Câmera Digital', 'Câmera digital compacta', 1499.90, 11, '789000000014', TRUE, NOW(), NOW()),
(1, 'Câmera Mirrorless', 'Câmera mirrorless profissional', 5499.90, 4, '789000000015', TRUE, NOW(), NOW()),
(1, 'Câmera de Segurança', 'Câmera de segurança Wi-Fi Full HD', 299.90, 30, '789000000016', TRUE, NOW(), NOW()),
(1, 'Console Game', 'Console de videogame geração atual', 3999.90, 6, '789000000017', TRUE, NOW(), NOW()),
(1, 'Controle Wireless', 'Controle sem fio para videogame', 399.90, 22, '789000000018', TRUE, NOW(), NOW()),
(1, 'Drone Compacto', 'Drone compacto com câmera HD', 1599.90, 8, '789000000019', TRUE, NOW(), NOW()),
(1, 'Drone Profissional', 'Drone profissional com câmera 4K', 6999.90, 3, '789000000020', TRUE, NOW(), NOW()),

(2, 'Teclado Mecânico', 'Teclado mecânico RGB ABNT2', 299.90, 25, '789000000021', TRUE, NOW(), NOW()),
(2, 'Teclado Wireless', 'Teclado sem fio compacto', 149.90, 40, '789000000022', TRUE, NOW(), NOW()),
(2, 'Mouse Gamer', 'Mouse gamer RGB 7200 DPI', 199.90, 35, '789000000023', TRUE, NOW(), NOW()),
(2, 'Mouse Wireless', 'Mouse sem fio ergonômico', 129.90, 45, '789000000024', TRUE, NOW(), NOW()),
(2, 'Mousepad Gamer', 'Mousepad gamer tamanho grande', 89.90, 60, '789000000025', TRUE, NOW(), NOW()),
(2, 'Monitor 21.5"', 'Monitor LED Full HD 21.5 polegadas', 699.90, 18, '789000000026', TRUE, NOW(), NOW()),
(2, 'Monitor 24"', 'Monitor Full HD 24 polegadas', 899.90, 20, '789000000027', TRUE, NOW(), NOW()),
(2, 'Monitor 27"', 'Monitor IPS 27 polegadas', 1399.90, 14, '789000000028', TRUE, NOW(), NOW()),
(2, 'Monitor Gamer 27"', 'Monitor gamer 27 polegadas 144Hz', 1899.90, 10, '789000000029', TRUE, NOW(), NOW()),
(2, 'Monitor Ultrawide', 'Monitor ultrawide 34 polegadas', 2999.90, 7, '789000000030', TRUE, NOW(), NOW()),
(2, 'Webcam Full HD', 'Webcam Full HD para videoconferências', 249.90, 30, '789000000031', TRUE, NOW(), NOW()),
(2, 'Webcam 4K', 'Webcam profissional com resolução 4K', 699.90, 12, '789000000032', TRUE, NOW(), NOW()),
(2, 'Headset Gamer', 'Headset gamer com microfone', 349.90, 25, '789000000033', TRUE, NOW(), NOW()),
(2, 'Headset Wireless', 'Headset sem fio com microfone', 499.90, 18, '789000000034', TRUE, NOW(), NOW()),
(2, 'SSD 240GB', 'SSD SATA 240GB', 169.90, 40, '789000000035', TRUE, NOW(), NOW()),
(2, 'SSD 480GB', 'SSD SATA 480GB', 299.90, 35, '789000000036', TRUE, NOW(), NOW()),
(2, 'SSD 1TB', 'SSD NVMe 1TB', 599.90, 25, '789000000037', TRUE, NOW(), NOW()),
(2, 'HD Externo 1TB', 'HD externo USB 3.0 1TB', 329.90, 20, '789000000038', TRUE, NOW(), NOW()),
(2, 'HD Externo 2TB', 'HD externo USB 3.0 2TB', 499.90, 16, '789000000039', TRUE, NOW(), NOW()),
(2, 'Memória RAM 8GB', 'Memória RAM DDR4 8GB', 159.90, 50, '789000000040', TRUE, NOW(), NOW()),
(2, 'Memória RAM 16GB', 'Memória RAM DDR4 16GB', 289.90, 35, '789000000041', TRUE, NOW(), NOW()),
(2, 'Memória RAM 32GB', 'Kit memória RAM DDR4 32GB', 549.90, 20, '789000000042', TRUE, NOW(), NOW()),
(2, 'Placa de Vídeo 8GB', 'Placa de vídeo dedicada 8GB', 2299.90, 8, '789000000043', TRUE, NOW(), NOW()),
(2, 'Placa de Vídeo 12GB', 'Placa de vídeo dedicada 12GB', 3499.90, 6, '789000000044', TRUE, NOW(), NOW()),
(2, 'Fonte 500W', 'Fonte de alimentação 500W', 299.90, 20, '789000000045', TRUE, NOW(), NOW()),
(2, 'Fonte 650W', 'Fonte de alimentação 650W', 399.90, 18, '789000000046', TRUE, NOW(), NOW()),
(2, 'Fonte 750W', 'Fonte de alimentação 750W', 549.90, 12, '789000000047', TRUE, NOW(), NOW()),
(2, 'Gabinete Gamer', 'Gabinete gamer com lateral em vidro', 499.90, 15, '789000000048', TRUE, NOW(), NOW()),
(2, 'Cooler para CPU', 'Cooler para processador', 199.90, 25, '789000000049', TRUE, NOW(), NOW()),
(2, 'Hub USB', 'Hub USB com 4 portas', 99.90, 50, '789000000050', TRUE, NOW(), NOW()),

(3, 'Smartphone 64GB', 'Smartphone com 64GB de armazenamento', 999.90, 20, '789000000051', TRUE, NOW(), NOW()),
(3, 'Smartphone 128GB', 'Smartphone com 128GB de armazenamento', 1399.90, 25, '789000000052', TRUE, NOW(), NOW()),
(3, 'Smartphone 256GB', 'Smartphone com 256GB de armazenamento', 1999.90, 18, '789000000053', TRUE, NOW(), NOW()),
(3, 'Smartphone Pro 128GB', 'Smartphone premium com 128GB', 2499.90, 15, '789000000054', TRUE, NOW(), NOW()),
(3, 'Smartphone Pro 256GB', 'Smartphone premium com 256GB', 2999.90, 12, '789000000055', TRUE, NOW(), NOW()),
(3, 'Smartphone Pro Max', 'Smartphone premium com câmera avançada', 4299.90, 8, '789000000056', TRUE, NOW(), NOW()),
(3, 'Celular Básico', 'Celular básico com teclado físico', 199.90, 30, '789000000057', TRUE, NOW(), NOW()),
(3, 'Tablet 8"', 'Tablet com tela de 8 polegadas', 699.90, 20, '789000000058', TRUE, NOW(), NOW()),
(3, 'Tablet 10"', 'Tablet com tela de 10 polegadas', 999.90, 18, '789000000059', TRUE, NOW(), NOW()),
(3, 'Tablet 11"', 'Tablet premium com tela de 11 polegadas', 1999.90, 10, '789000000060', TRUE, NOW(), NOW()),
(3, 'Smartwatch', 'Relógio inteligente com monitoramento esportivo', 499.90, 25, '789000000061', TRUE, NOW(), NOW()),
(3, 'Smartwatch Pro', 'Smartwatch premium com GPS', 999.90, 15, '789000000062', TRUE, NOW(), NOW()),
(3, 'Pulseira Fitness', 'Pulseira inteligente para atividades físicas', 249.90, 30, '789000000063', TRUE, NOW(), NOW()),
(3, 'Carregador USB-C', 'Carregador rápido USB-C', 99.90, 60, '789000000064', TRUE, NOW(), NOW()),
(3, 'Carregador Turbo', 'Carregador rápido de alta potência', 149.90, 45, '789000000065', TRUE, NOW(), NOW()),
(3, 'Carregador Wireless', 'Carregador sem fio para smartphones', 129.90, 40, '789000000066', TRUE, NOW(), NOW()),
(3, 'Cabo USB-C 1m', 'Cabo USB-C de 1 metro', 39.90, 100, '789000000067', TRUE, NOW(), NOW()),
(3, 'Cabo USB-C 2m', 'Cabo USB-C de 2 metros', 59.90, 80, '789000000068', TRUE, NOW(), NOW()),
(3, 'Cabo Lightning', 'Cabo Lightning para dispositivos compatíveis', 69.90, 70, '789000000069', TRUE, NOW(), NOW()),
(3, 'Película de Vidro', 'Película protetora de vidro temperado', 29.90, 100, '789000000070', TRUE, NOW(), NOW()),
(3, 'Capa Silicone', 'Capa de silicone para smartphone', 49.90, 80, '789000000071', TRUE, NOW(), NOW()),
(3, 'Capa Premium', 'Capa protetora premium para smartphone', 99.90, 60, '789000000072', TRUE, NOW(), NOW()),
(3, 'Suporte Veicular', 'Suporte veicular para smartphone', 79.90, 45, '789000000073', TRUE, NOW(), NOW()),
(3, 'Power Bank 10000mAh', 'Bateria externa de 10000mAh', 149.90, 35, '789000000074', TRUE, NOW(), NOW()),
(3, 'Power Bank 20000mAh', 'Bateria externa de 20000mAh', 249.90, 25, '789000000075', TRUE, NOW(), NOW()),
(3, 'Fone TWS', 'Fone de ouvido TWS totalmente sem fio', 199.90, 40, '789000000076', TRUE, NOW(), NOW()),
(3, 'Fone TWS Pro', 'Fone TWS com cancelamento de ruído', 499.90, 20, '789000000077', TRUE, NOW(), NOW()),
(3, 'Fone Esportivo', 'Fone Bluetooth para atividades esportivas', 179.90, 30, '789000000078', TRUE, NOW(), NOW()),
(3, 'Adaptador USB-C', 'Adaptador USB-C multifuncional', 89.90, 50, '789000000079', TRUE, NOW(), NOW()),
(3, 'Adaptador HDMI', 'Adaptador USB-C para HDMI', 119.90, 35, '789000000080', TRUE, NOW(), NOW()),
(3, 'Suporte para Tablet', 'Suporte ajustável para tablets', 99.90, 30, '789000000081', TRUE, NOW(), NOW()),
(3, 'Teclado para Tablet', 'Teclado Bluetooth para tablet', 199.90, 25, '789000000082', TRUE, NOW(), NOW()),
(3, 'Caneta para Tablet', 'Caneta digital para tablet', 299.90, 20, '789000000083', TRUE, NOW(), NOW()),
(3, 'Smartwatch Infantil', 'Smartwatch infantil com GPS', 399.90, 15, '789000000084', TRUE, NOW(), NOW()),
(3, 'Rastreador Bluetooth', 'Rastreador Bluetooth para objetos', 129.90, 40, '789000000085', TRUE, NOW(), NOW()),
(3, 'Ring Light', 'Ring light LED para celular', 159.90, 30, '789000000086', TRUE, NOW(), NOW()),
(3, 'Tripé para Celular', 'Tripé ajustável para smartphone', 119.90, 35, '789000000087', TRUE, NOW(), NOW()),
(3, 'Microfone USB', 'Microfone USB para gravações', 299.90, 20, '789000000088', TRUE, NOW(), NOW()),
(3, 'Microfone Lapela', 'Microfone de lapela para smartphones', 99.90, 40, '789000000089', TRUE, NOW(), NOW()),
(3, 'Lente para Celular', 'Kit de lentes para câmera do smartphone', 89.90, 25, '789000000090', TRUE, NOW(), NOW()),
(3, 'Mini Projetor', 'Mini projetor portátil', 799.90, 12, '789000000091', TRUE, NOW(), NOW()),
(3, 'Controle Bluetooth', 'Controle Bluetooth para smartphones', 149.90, 30, '789000000092', TRUE, NOW(), NOW()),
(3, 'Gamepad Mobile', 'Controle gamer para celular', 249.90, 20, '789000000093', TRUE, NOW(), NOW()),
(3, 'Dock USB-C', 'Dock station USB-C para notebook', 399.90, 15, '789000000094', TRUE, NOW(), NOW()),
(3, 'Leitor de Cartão', 'Leitor de cartões USB-C', 79.90, 40, '789000000095', TRUE, NOW(), NOW()),
(3, 'Cartão MicroSD 64GB', 'Cartão de memória MicroSD 64GB', 59.90, 60, '789000000096', TRUE, NOW(), NOW()),
(3, 'Cartão MicroSD 128GB', 'Cartão de memória MicroSD 128GB', 89.90, 50, '789000000097', TRUE, NOW(), NOW()),
(3, 'Cartão MicroSD 256GB', 'Cartão de memória MicroSD 256GB', 159.90, 35, '789000000098', TRUE, NOW(), NOW()),
(3, 'Cartão MicroSD 512GB', 'Cartão de memória MicroSD 512GB', 299.90, 20, '789000000099', TRUE, NOW(), NOW()),
(3, 'Kit Acessórios Celular', 'Kit com diversos acessórios para celular', 199.90, 25, '789000000100', TRUE, NOW(), NOW());

    