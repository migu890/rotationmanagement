INSERT INTO category (code, description)
VALUES
    ('A', 'Erfassen, Interpretieren und Darstellen von Anforderungen für Applikationen'),
    ('B', 'Entwickeln von Applikationen unter Berücksichtigung von Qualitätsmerkmalen'),
    ('C', 'Aufbauen und Pflegen von Daten sowie von deren Strukturen'),
    ('D', 'Inbetriebnahme von ICT-Geräten'),
    ('E', 'Arbeiten in Projekten');

INSERT INTO competency (category, code, description)
SELECT id, 'A1', 'Anforderungen und Bedürfnisse analysieren und strukturieren und dokumentieren'
FROM category
WHERE code = 'A';

INSERT INTO competency (category, code, description)
SELECT id, 'A2', 'Verschiedene Lösungsvorschläge mit den notwendigen Benutzerschnittstellen erarbeiten'
FROM category
WHERE code = 'A';

INSERT INTO competency (category, code, description)
SELECT id, 'A3', 'Anforderungen und Bedürfnisse in den gewählten Lösungsvorschlägen auf Vollständigkeit überprüfen'
FROM category
WHERE code = 'A';

INSERT INTO competency (category, code, description)
SELECT id, 'B1', 'Testkonzept erstellen, unterschiedliche Testvorgehen einsetzen und Applikationen systematisch testen'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'B2', 'Architekturvorgaben in einem konkreten Entwurf umsetzen'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'B3', 'Applikationen unter Anwendung geeigneter Vorgehensmodelle benutzergerecht entwickeln und dokumentieren'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'B4', 'Anwendung und Benutzerschnittstellen für Applikationen gemäss den Kundenbedürfnissen und dem Entwurf implementieren'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'B5', 'Qualität der Applikationen sicherstellen'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'B6', 'Einführung der Applikationen vorbereiten und umsetzen'
FROM category
WHERE code = 'B';

INSERT INTO competency (category, code, description)
SELECT id, 'C1', 'Daten identifizieren und analysieren und mit geeigneten Datenmodellen entwickeln'
FROM category
WHERE code = 'C';

INSERT INTO competency (category, code, description)
SELECT id, 'C2', 'Datenmodell in einer Datenbank umsetzen'
FROM category
WHERE code = 'C';

INSERT INTO competency (category, code, description)
SELECT id, 'C3', 'Aus Applikationen auf Daten mit geeigneten Sprachmitteln zugreifen'
FROM category
WHERE code = 'C';

INSERT INTO competency (category, code, description)
SELECT id, 'D1', 'Arbeitsplatz und Serverdienste für den lokalen Netzbetrieb nach Vorgaben installieren und konfigurieren'
FROM category
WHERE code = 'D';

INSERT INTO competency (category, code, description)
SELECT id, 'E1', 'Arbeiten und Aufträge systematisch und effizient vorbereiten, strukturieren, durchführen und dokumentieren'
FROM category
WHERE code = 'E';

INSERT INTO competency (category, code, description)
SELECT id, 'E2', 'In Projekten mitarbeiten und nach Projektmethoden arbeiten'
FROM category
WHERE code = 'E';

INSERT INTO competency (category, code, description)
SELECT id, 'E3', 'In Projekten zielgerichtet und den jeweiligen Personen angepasst kommunizieren'
FROM category
WHERE code = 'E';

INSERT INTO apprentice (
    first_name,
    last_name,
    year,
    looking_for_rotation
)
VALUES
    ('Luca', 'Meier', 2, TRUE),
    ('Sven', 'Keller', 3, TRUE),
    ('Nina', 'Fischer', 1, FALSE);

INSERT INTO rotation (
    title,
    description,
    department,
    technologies,
    status,
    date_of_availability
)
VALUES
    (
        'Backend Development Rotation',
        'Entwicklung von REST Schnittstellen mit Spring Boot',
        'Digital Solutions',
        'Java, Spring Boot, MariaDB',
        'available',
        '2026-07-01'
    ),
    (
        'QA Automation Rotation',
        'Automatisierte Tests und Qualitätssicherung',
        'Quality Engineering',
        'JUnit, Mockito, Postman',
        'available',
        '2026-08-01'
    ),
    (
        'Database Engineering Rotation',
        'Arbeiten mit relationalen Datenbanken und SQL',
        'Data Engineering',
        'MariaDB, SQL, DBeaver',
        'occupied',
        '2026-09-01'
    );

INSERT INTO apprentice_competency (
    apprentice_id,
    competency_id,
    competency_state
)
VALUES
    (1, 1, 'Done'),
    (1, 2, 'In Progress'),
    (1, 10, 'Open'),

    (2, 4, 'Done'),
    (2, 5, 'Done'),
    (2, 11, 'In Progress'),

    (3, 2, 'Open'),
    (3, 3, 'In Progress'),
    (3, 15, 'Done');

INSERT INTO rotation_competency (
    rotation_id,
    competency_id,
    weight
)
VALUES
    (1, 4, 10),
    (1, 5, 8),
    (1, 11, 9),

    (2, 4, 10),
    (2, 9, 8),
    (2, 15, 5),

    (3, 10, 10),
    (3, 11, 10),
    (3, 12, 8);

INSERT INTO assignment (
    rotation_id,
    apprentice_id,
    start_date,
    end_date
)
VALUES
    (
        1,
        2,
        '2026-01-01',
        '2026-06-30'
    );