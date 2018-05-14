CREATE TABLE hubs (
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(60) NOT NULL,
  latitude      FLOAT NOT NULL,
  longitude     FLOAT NOT NULL,
  country       VARCHAR(2)  NOT NULL,
  incoming_load INT,
  outgoing_load INT
);

CREATE TABLE legs (
  id             SERIAL PRIMARY KEY,
  from_hub       SERIAL REFERENCES hubs (id),
  to_hub         SERIAL REFERENCES hubs (id),
  distance       INT NOT NULL,
  avg_speed      FLOAT,
  avg_load       FLOAT,
  avg_efficiency FLOAT
);

CREATE TABLE subtrips (
  id         SERIAL PRIMARY KEY,
  trip_id    INT       NOT NULL,
  leg_id     SERIAL REFERENCES legs (id),
  start_date TIMESTAMP NOT NULL,
  end_date   TIMESTAMP NOT NULL,
  duration   INT       NOT NULL,
  load       INT       NOT NULL,
  load_time  INT
);

CREATE INDEX start_idx
  ON subtrips
  USING btree (start_date);

CREATE INDEX end_indx
  ON subtrips
  USING btree (end_date);
