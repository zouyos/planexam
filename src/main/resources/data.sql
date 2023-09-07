insert into role (nom) values ('admin');
insert into role (nom) values ('prof');

insert into utilisateur (role_id, email, mdp) VALUES (1,'hamza.benketaf@gmail.com','$2a$10$ACvV7pSw.9iPB9ljJFyRZ.Gznr5riJzYulBxKr0A3/ldBNwB4aRBe');

insert into specialite (id, libelle) VALUES (1,'SISR');
insert into specialite (id, libelle) VALUES (2,'SLAM');
insert into specialite (id, libelle) VALUES (3,'SI');
insert into specialite (id, libelle) VALUES (4,'Français');
insert into specialite (id, libelle) VALUES (5,'EDM');

insert into sessione5 (id, libelle, date_debut, date_fin) VALUES (1,'Épreuve E5', '2023-06-01', '2023-06-22');

insert into jour_passage (id, date_passage, session_e5_id) VALUES (1,'2023-06-01', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (2,'2023-06-02', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (3,'2023-06-03', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (4,'2023-06-04', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (5,'2023-06-05', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (6,'2023-06-06', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (7,'2023-06-07', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (8,'2023-06-08', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (9,'2023-06-09', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (10,'2023-06-10', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (11,'2023-06-11', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (12,'2023-06-12', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (13,'2023-06-13', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (14,'2023-06-14', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (15,'2023-06-15', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (16,'2023-06-16', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (17,'2023-06-17', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (18,'2023-06-18', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (19,'2023-06-19', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (20,'2023-06-20', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (21,'2023-06-21', 1);
insert into jour_passage (id, date_passage, session_e5_id) VALUES (22,'2023-06-22', 1);
