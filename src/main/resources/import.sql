-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Seed data for tests and dev environment
-- Pacientes
insert into Paciente (id, nome, cpf, idade, doencasPreExistentes) values (1, 'João Silva', '11122233344', 30, '');
insert into Paciente (id, nome, cpf, idade, doencasPreExistentes) values (2, 'Maria Oliveira', '22233344455', 65, 'hipertensão');
insert into Paciente (id, nome, cpf, idade, doencasPreExistentes) values (3, 'Carlos Pereira', '33344455566', 45, 'diabetes');

-- Ensure sequences continue after inserted ids (H2 sequence names from Hibernate defaults)
alter sequence if exists Paciente_SEQ restart with 100;

-- Triagens (historical records)
insert into Triagem (id, paciente_id, sintomas, classificacao, dataHora) values (1, 1, 'dor de cabeça', 'AMARELO', CURRENT_TIMESTAMP());
insert into Triagem (id, paciente_id, sintomas, classificacao, dataHora) values (2, 2, 'febre e falta de ar', 'VERMELHO', CURRENT_TIMESTAMP());
insert into Triagem (id, paciente_id, sintomas, classificacao, dataHora) values (3, 3, 'coceira', 'VERDE', CURRENT_TIMESTAMP());

alter sequence if exists Triagem_SEQ restart with 100;