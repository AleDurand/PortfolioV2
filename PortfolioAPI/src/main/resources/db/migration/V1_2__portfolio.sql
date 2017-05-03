-- USER --
INSERT INTO portfolio.user (username, password, enabled) VALUES ('adurand', 'password', 1);
INSERT INTO portfolio.user (username, password, enabled) VALUES ('mrodecker', 'password', 1);
INSERT INTO portfolio.user (username, password, enabled) VALUES ('msanchez', 'password', 1);

-- AUTHORITY --
INSERT INTO portfolio.authority (id, name) VALUES (1, 'Administrator');
INSERT INTO portfolio.authority (id, name) VALUES (2, 'User');

-- USER HAS AUTHORITY --
INSERT INTO portfolio.user_has_authority (user_id, authority_id) VALUES ('adurand', 1);
INSERT INTO portfolio.user_has_authority (user_id, authority_id) VALUES ('mrodecker', 1);
INSERT INTO portfolio.user_has_authority (user_id, authority_id) VALUES ('msanchez', 1);