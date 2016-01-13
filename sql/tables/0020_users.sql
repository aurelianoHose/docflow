-- Table: users

--DROP TABLE users;

CREATE TABLE users
(
  id bigserial NOT NULL,
  name character varying unique,
  passwd character(32),
  user_id bigint,
  added timestamp NULL,
  modified timestamp NULL,
  del boolean default false,
  CONSTRAINT users_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO netradio;

  INSERT INTO users (id, name, passwd, user_id, added) VALUES (0, 'system', md5('secret'), 0, now());
  ALTER TABLE users ADD CONSTRAINT fk_id_creator FOREIGN KEY (user_id) REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
  INSERT INTO users (name, passwd, user_id, added) VALUES ('admin', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user1', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user2', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user3', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user4', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user5', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user6', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user7', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user8', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user9', md5('secret'), 0, now());
  INSERT INTO users (name, passwd, user_id, added) VALUES ('user10', md5('secret'), 0, now());
