USE rotationmanagement_db;

CREATE TABLE apprentice (
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            first_name VARCHAR(50) NOT NULL,
                            last_name VARCHAR(50) NOT NULL,
                            year INTEGER NOT NULL,
                            looking_for_rotation TINYINT(1) NOT NULL
);

CREATE TABLE category (
                          id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          code VARCHAR(1) NOT NULL UNIQUE,
                          description TEXT NOT NULL
);

CREATE TABLE competency (
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            category INTEGER NOT NULL,
                            code VARCHAR(2) NOT NULL UNIQUE,
                            description TEXT NOT NULL,
                            CONSTRAINT fk_competency_category FOREIGN KEY (category) REFERENCES category(id)
);

CREATE TABLE rotation (
                          id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          title VARCHAR(60) NOT NULL,
                          description TEXT NOT NULL,
                          department VARCHAR(40) NOT NULL,
                          technologies TEXT,
                          status ENUM('available', 'occupied', 'on_hold') NOT NULL,
                          date_of_availability DATE NOT NULL
);

CREATE TABLE apprentice_competency (
                                       apprentice_id INTEGER NOT NULL,
                                       competency_id INTEGER NOT NULL,
                                       competency_state ENUM('Done', 'In Progress', 'Open') NOT NULL,
                                       PRIMARY KEY (apprentice_id, competency_id),
                                       CONSTRAINT fk_apprentice_competency_apprentice
                                           FOREIGN KEY (apprentice_id)
                                               REFERENCES apprentice(id),
                                       CONSTRAINT fk_apprentice_competency_competency
                                           FOREIGN KEY (competency_id)
                                               REFERENCES competency(id)
);

CREATE TABLE rotation_competency (
                                     rotation_id INTEGER NOT NULL,
                                     competency_id INTEGER NOT NULL,
                                     weight INTEGER NOT NULL,
                                     PRIMARY KEY (rotation_id, competency_id),
                                     CONSTRAINT fk_rotation_competency_rotation
                                         FOREIGN KEY (rotation_id)
                                             REFERENCES rotation(id),
                                     CONSTRAINT fk_rotation_competency_competency
                                         FOREIGN KEY (competency_id)
                                             REFERENCES competency(id)
);

CREATE TABLE assignment (
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            rotation_id INTEGER NOT NULL,
                            apprentice_id INTEGER NOT NULL,
                            start_date DATE NOT NULL,
                            end_date DATE,
                            CONSTRAINT fk_assignment_rotation
                                FOREIGN KEY (rotation_id)
                                    REFERENCES rotation(id),
                            CONSTRAINT fk_assignment_apprentice
                                FOREIGN KEY (apprentice_id)
                                    REFERENCES apprentice(id)
);