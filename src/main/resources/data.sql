
INSERT INTO cliente (id, nome, exclusive, saldo, numero_conta, data_nascimento)
VALUES (10000, 'Pedro Santos', false, 1040.40, '1234567890', '2000-01-01');

INSERT INTO cliente (id, nome, exclusive, saldo, numero_conta, data_nascimento)
VALUES (10001, 'Luis Fonseca', true, 2090.20, '1234567891', '2000-01-01');


-- Inserts para o cliente com ID 1
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100001, 10000, 'DEPOSITO', 500.00, '2023-05-11 09:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100002, 10000, 'DEPOSITO', 200.00, '2023-05-13 10:30:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100003, 10000, 'DEPOSITO', 100.00, '2023-05-16 11:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100004, 10000, 'DEPOSITO', 300.00, '2023-05-18 14:20:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100005, 10000, 'DEPOSITO', 150.00, '2023-05-18 15:50:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100006, 10000, 'SAQUE', 200.00, '2023-05-12 16:30:00', 0.004, 0.80);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100007, 10000, 'SAQUE', 400.00, '2023-05-25 10:15:00', 0.01, 4.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100008, 10000, 'DEPOSITO', 250.00, '2023-05-16 12:30:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (100009, 10000, 'DEPOSITO', 1000.00, '2023-05-19 14:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000010, 10000, 'SAQUE', 150.00, '2023-05-21 16:00:00', 0.004, 0.60);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000011, 10000, 'SAQUE', 500.00, '2023-05-22 10:30:00', 0.01, 5.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000012, 10000, 'DEPOSITO', 300.00, '2023-05-25 12:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000013, 10000, 'DEPOSITO', 800.00, '2023-05-27 15:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000014, 10000, 'SAQUE', 1000.00, '2023-05-29 16:15:00', 0.01, 10.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000015, 10000, 'SAQUE', 50.00, '2023-05-30 09:30:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000016, 10000, 'DEPOSITO', 400.00, '2023-05-21 11:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000017, 10000, 'DEPOSITO', 600.00, '2023-05-23 14:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000018, 10000, 'SAQUE', 200.00, '2023-05-27 15:15:00', 0.004, 0.80);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000019, 10000, 'SAQUE', 100.00, '2023-05-20 16:30:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000020, 10000, 'DEPOSITO', 150.00, '2023-05-22 09:45:00', 0.00, 0.00);

-- Inserts para o cliente com ID 2
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000021, 10001, 'DEPOSITO', 500.00, '2023-05-22 09:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000022, 10001, 'DEPOSITO', 200.00, '2023-05-25 10:30:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000023, 10001, 'DEPOSITO', 100.00, '2023-05-17 12:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000024, 10001, 'DEPOSITO', 300.00, '2023-05-19 14:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000025, 10001, 'DEPOSITO', 150.00, '2023-05-22 15:15:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000026, 10001, 'SAQUE', 200.00, '2023-05-24 09:30:00', 0.004, 0.80);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000027, 10001, 'SAQUE', 400.00, '2023-05-26 10:45:00', 0.01, 4.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000028, 10001, 'DEPOSITO', 250.00, '2023-05-28 12:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000029, 10001, 'DEPOSITO', 1000.00, '2023-05-20 14:15:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000030, 10001, 'SAQUE', 150.00, '2023-05-23 15:30:00', 0.004, 0.60);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000031, 10001, 'SAQUE', 500.00, '2023-05-26 09:45:00', 0.01, 5.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000032, 10001, 'DEPOSITO', 300.00, '2023-05-28 11:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000033, 10001, 'DEPOSITO', 800.00, '2023-05-30 13:15:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000034, 10001, 'SAQUE', 1000.00, '2023-05-21 14:30:00', 0.01, 10.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000035, 10001, 'SAQUE', 50.00, '2023-05-24 15:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000036, 10001, 'DEPOSITO', 400.00, '2023-05-26 09:00:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000037, 10001, 'DEPOSITO', 600.00, '2023-05-29 10:15:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000038, 10001, 'SAQUE', 200.00, '2023-05-21 11:30:00', 0.004, 0.80);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000039, 10001, 'SAQUE', 100.00, '2023-05-23 12:45:00', 0.00, 0.00);
INSERT INTO movimentacao_de_conta (id, cliente_id, tipo, valor, data, percentual_taxa, valor_taxa)
VALUES (1000040, 10001, 'DEPOSITO', 150.00, '2023-05-25 14:00:00', 0.00, 0.00);