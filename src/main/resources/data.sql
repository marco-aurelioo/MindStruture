insert into role(role) values('USER');
insert into role(role) values('COORD');
insert into role(role) values('ADMIN');
insert into role(role) values('ANONIMO');

INSERT INTO "PUBLIC"."USERCONNECTION" VALUES
('6565231086850331', 'facebook', '6565231086850331', 1, 'Marco Aurelio Marcolino', NULL, 'https://graph.facebook.com/v2.5/6565231086850331/picture', 'EAAFDEzANAwYBABGhzNM6o08gdOuJurDdQfa0NDr4Hl2tDOLyACpmyibwRZAVpmRyZB62tpgbInaYFKwz5zoBAZC1uuHXxwFVySNr9uyY5UJPPYDLugE2QH5zZAyZB5uWh9T5ZCreYf5Vj7AoIW2qbEmsjhj1mkyH2OJCZAft4n6szeRl8LhFphQLIKKsTgzxuuZBlas375nIx4FDtTQOZAR8NZC6dhXsLGkztb3TVxZBfd8xAZDZD', NULL, NULL, 1636205917041);

INSERT INTO "PUBLIC"."USER" VALUES
(X'fe08f7bb3e864d8193b007610cccf175', 'https://graph.facebook.com/v2.5/6565231086850331/picture', 'marco_aurelioo@yahoo.com.br', 'Marco Aurelio Marcolino', '$2a$10$bVvrIRQ7DWJ6A5ueiHGGW.VGESorg4lUkH8WB4CaaC2S4gut4FpPe'),
(X'eefa134d94d84940a6f3c72715e1ea4d', NULL, 'marco@mailinator.com', 'marco', '$2a$10$lZ1VbgnWZyl6hakD9KwRfuj2T2TRf.Pcp4Ihe96eeJWl89/0mCP9a');

INSERT INTO "PUBLIC"."MESSAGE" VALUES
(X'9662d80e6d224a06a28b3941480ffafb', NULL, X'eefa134d94d84940a6f3c72715e1ea4d', 'marco_aurelioo@yahoo.com.br', STRINGDECODE('solicita\u00e7\u00e3o de amizade de marco acesso o link http://localhost:8080/amigos/confirmacao?from=eefa134d-94d8-4940-a6f3-c72715e1ea4d&msgId=9662d80e-6d22-4a06-a28b-3941480ffafb'), FALSE);

INSERT INTO "PUBLIC"."USER_ROLES" VALUES
(X'fe08f7bb3e864d8193b007610cccf175', 1),
(X'eefa134d94d84940a6f3c72715e1ea4d', 1);

INSERT INTO "PUBLIC"."FRIEND_LIST" VALUES
(X'6c7ade2dc762456d94f7ee57fb27072f', X'fe08f7bb3e864d8193b007610cccf175'),
(X'fbfaa708d28942f8a40f03273d6c67ac', X'eefa134d94d84940a6f3c72715e1ea4d');

INSERT INTO FRIEND_LIST_FRIENDS(FRIEND_LIST_ENTITY_ID, FRIENDS_ID ) VALUES (X'6c7ade2dc762456d94f7ee57fb27072f',X'eefa134d94d84940a6f3c72715e1ea4d')
