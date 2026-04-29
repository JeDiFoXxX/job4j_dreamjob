CREATE TABLE postge (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    visible BOOLEAN DEFAULT TRUE,
    city_id INTEGER,
    file_id INTEGER
);