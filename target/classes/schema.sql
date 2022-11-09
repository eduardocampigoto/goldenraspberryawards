CREATE TABLE IF NOT EXISTS studio
(
    id   INT PRIMARY KEY NOT NULL auto_increment,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS producer
(
    id   INTEGER PRIMARY KEY NOT NULL auto_increment,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS title
(
    id     INTEGER PRIMARY KEY NOT NULL auto_increment,
    name   VARCHAR(255),
    year   INT,
    winner CHAR(3)
);

CREATE TABLE IF NOT EXISTS producers_titles
(
    id          INTEGER PRIMARY KEY NOT NULL auto_increment,
    producer_id INTEGER             NOT NULL,
    title_id    INTEGER             NOT NULL,
    CONSTRAINT fk_producers_title_producer FOREIGN KEY (producer_id) REFERENCES producer (id),
    CONSTRAINT fk_producers_titles_title FOREIGN KEY (title_id) REFERENCES title (id)
);

CREATE TABLE IF NOT EXISTS studios_titles
(
    id        INTEGER PRIMARY KEY NOT NULL auto_increment,
    studio_id INTEGER             NOT NULL,
    title_id  INTEGER             NOT NULL,
    CONSTRAINT fk_studios_titles_studio FOREIGN KEY (studio_id) REFERENCES studio (id),
    CONSTRAINT fk_studios_titles_title FOREIGN KEY (title_id) REFERENCES title (id)
);