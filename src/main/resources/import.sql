insert into role (nom) values ('admin');
insert into role (nom) values ('prof');

insert into utilisateur (role_id, email, mdp) VALUES (1,'admin@email.com','$2a$10$uSwACi0Xkw9pOVSdFGhzPebFLBqAHDO5hVHr27qVd71OtVDJnuDJS');
insert into utilisateur (role_id, email, mdp) values (2,'prof@email.com','$2a$10$GM8MfrVFZ3BnjuXJbGYcSu.FsRUnyuHJyVB7es1qU.5mlk1EHp29m');

-- mdp admin = admin@email.com1
-- mdp prof = prof@email.com1
