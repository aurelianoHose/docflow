-- Table: flow

-- DROP TABLE flow;

CREATE TABLE flow
(
  id bigserial NOT NULL,
  flow character varying,
  link character varying,
  codec_id bigint NOT NULL,
  bitrade numeric,
  icon character varying(256),
  user_id bigserial,
  added timestamp NULL,
  modified timestamp NULL,
  del boolean DEFAULT false,
  actual boolean DEFAULT true,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_flow_codecs FOREIGN KEY (codec_id)
      REFERENCES codecs (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_id_creator FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE flow
  OWNER TO netradio;

INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио Tele-Sat', 'http://stream4.radiostyle.ru:8004/tele-sat', 1, 128, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио «В ритме 90-х»', 'http://webcast.apexradio.ru:8000/90?tyurtyurtuy', 2, 256, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('42fm', 'http://listen.42fm.ru:8000/stealkill', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('РадиоТочка-Монреаль', 'http://174.142.248.152:8001/rtochka192.mp3', 1, 192, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('SunRadio – Black', 'http://radio.sunradio.ru:80/rnb128', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('SunRadio – Lounge', 'http://radio.sunradio.ru/lounge128', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио "Город зажигает свои звёзды"', 'http://webcast.apexradio.ru:8000/gzsz', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио ICE', 'http://live26.kiwi.kz:8000/ice?36&79', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Biker-FM', 'http://stream1.radiostyle.ru:8001/biker-fm', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Другое Радио - Астрахань', 'http://drugoeradio.net:8000/listen128', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио Центр - Ульяновск', 'http://radio.mv.ru:8080/Radio_centr', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио Дизель - Пенза', 'http://listen.rockfunk.ru:8007/listen', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Saint-P - Санкт-Петербург', 'http://67.159.60.45:8052/;?icy=http', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио «Club Touch» - Новокузнецк', 'http://webcast.apexradio.ru:8000/clubtouch', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Артём FM - Артём', 'http://listen2.myradio24.com:9000/8118', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Общага FM', 'http://s02.radio-tochka.com:8790/radio', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Русский шансон - Ростов-на-Дону', 'http://listen.myradio24.com:9000/8144', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('161FM -  Ростов-на-Дону', 'http://stream.161.fm:8000/128', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио «ElectroN»', 'http://radio-electron.ru:8000/128', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Казантип FM', 'http://radio.kazantip-fm.ru:8000/mp3', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Ночное радио', 'http://nightradio.ru:8000/live', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Radio Zabawa', 'http://listen.myradio24.com:9000/8020', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио «Боб-Джаз»', 'http://stream01.radiocon.ru:8000/bobjazz', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио «RollStation»', 'http://46.32.69.108:8000/live96', 3, 320, 0, now());
INSERT INTO flow (flow, link, codec_id, bitrade, user_id, added) VALUES ('Радио РОЛ', 'http://radio.andynet.org:8000/ROL', 3, 320, 0, now());
















