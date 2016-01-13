-- Table: types_configs

-- DROP TABLE types_configs;

CREATE TABLE types_configs
(
  id bigserial NOT NULL,
  name character varying,
  CONSTRAINT types_configs_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE types_configs
  OWNER TO netradio;

  INSERT INTO types_configs (name) VALUES ('crontab');
  INSERT INTO types_configs (name) VALUES ('regexp');
  INSERT INTO types_configs (name) VALUES ('count');