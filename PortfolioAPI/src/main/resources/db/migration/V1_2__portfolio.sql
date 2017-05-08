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

-- ALBUM --
INSERT INTO portfolio.album (id, name, description, path, type, read_only) VALUES (1, 'Covers', 'Covers', 'c4ca4238a0b923820dcc509a6f75849b', '1', 1);
INSERT INTO portfolio.album (id, name, description, path, type) VALUES (2, 'album1', 'description1', 'c4ca4238a0b923820dcc509a6f75849c', '0');