-- Table: Codecs

-- DROP TABLE codecs;

CREATE TABLE codecs
(
  id bigserial NOT NULL,
  name character varying,
  CONSTRAINT pk_codecs PRIMARY KEY (id),
  CONSTRAINT uk_codecs UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE codecs
  OWNER TO netradio;

INSERT INTO codecs (name) VALUES ('mp3');
INSERT INTO codecs (name) VALUES ('ogg');
INSERT INTO codecs (name) VALUES ('opus');