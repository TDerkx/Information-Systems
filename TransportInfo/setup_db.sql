CREATE TABLE hubs (
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(60) NOT NULL,
  latitude      VARCHAR(20) NOT NULL,
  longitude     VARCHAR(20) NOT NULL,
  country       VARCHAR(5)  NOT NULL,
  incoming_load INT,
  outgoing_load INT
);

CREATE TABLE legs (
  from_hub       SERIAL PRIMARY KEY REFERENCES hubs (id),
  to_hub         SERIAL PRIMARY KEY REFERENCES hubs (id),
  distance       INT NOT NULL,
  avg_speed      FLOAT,
  avg_load       FLOAT,
  avg_efficiency FLOAT
);

CREATE TABLE subtrips (
  id         INT PRIMARY KEY,
  from_hub   SERIAL PRIMARY KEY REFERENCES legs (from_hub),
  to_hub     SERIAL PRIMARY KEY REFERENCES legs (to_hub),
  start_date TIMESTAMP NOT NULL,
  end_date   TIMESTAMP NOT NULL,
  duration   INT,
  load       INT       NOT NULL,
  load_time  INT
);

CREATE INDEX start_idx
  ON subtrips
  USING btree (start_date);

CREATE INDEX end_indx
  ON subtrips
  USING btree (end_date);
