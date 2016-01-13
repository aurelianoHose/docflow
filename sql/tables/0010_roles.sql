-- Table: roles

-- DROP TABLE roles;

CREATE TABLE roles
(
  id bigserial NOT NULL,
  name character varying,
  CONSTRAINT role_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE roles
  OWNER TO netradio;

  INSERT INTO roles (name) VALUES ('admin');
  INSERT INTO roles (name) VALUES ('user');