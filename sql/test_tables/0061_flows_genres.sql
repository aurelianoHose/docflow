-- Table: flows_genres

-- DROP TABLE flows_genres;

CREATE TABLE flows_genres
(
  genre_id bigint NOT NULL,
  flow_id  bigint NOT NULL,
  CONSTRAINT flows_genres_pk PRIMARY KEY (genre_id, flow_id),
  CONSTRAINT flows_genres_flow_fk FOREIGN KEY (flow_id)
      REFERENCES flow (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT flows_genres_genre_fk FOREIGN KEY (genre_id)
      REFERENCES genres (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users_roles
  OWNER TO netradio;
    INSERT INTO flows_genres (flow_id, genre_id) VALUES (1,1);
    INSERT INTO flows_genres (flow_id, genre_id) VALUES (2,2);
    INSERT INTO flows_genres (flow_id, genre_id) VALUES (3,3);
    INSERT INTO flows_genres (flow_id, genre_id) VALUES (4,4);