-- Table: flows_genres

-- DROP TABLE flows_genres;

CREATE TABLE flows_users
(
  user_id bigint NOT NULL,
  flow_id  bigint NOT NULL,
  CONSTRAINT flows_users_pk PRIMARY KEY (user_id, flow_id),
  CONSTRAINT flows_users_flow_fk FOREIGN KEY (flow_id)
      REFERENCES flow (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT flows_users_user_fk FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

ALTER TABLE flows_users
  OWNER TO netradio;

    INSERT INTO flows_users (flow_id, user_id) VALUES (1,1);
    INSERT INTO flows_users (flow_id, user_id) VALUES (13,1);