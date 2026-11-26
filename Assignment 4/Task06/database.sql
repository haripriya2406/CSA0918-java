CREATE DATABASE IF NOT EXISTS campus_events;
USE campus_events;

CREATE TABLE IF NOT EXISTS students (
    register_no VARCHAR(30) PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS registrations (
    registration_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    register_no VARCHAR(30) NOT NULL,
    event_id INT NOT NULL,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_registration_student
        FOREIGN KEY (register_no) REFERENCES students(register_no),
    CONSTRAINT fk_registration_event
        FOREIGN KEY (event_id) REFERENCES events(event_id),
    CONSTRAINT uq_student_event UNIQUE (register_no, event_id)
);

INSERT IGNORE INTO events(event_id, event_name) VALUES
(1, 'Coding Contest'),
(2, 'Cultural Fest'),
(3, 'Technical Symposium');

-- Example paginated query:
-- SELECT r.registration_id, r.register_no, s.student_name, s.email,
--        e.event_name, r.registered_at
-- FROM registrations r
-- JOIN students s ON s.register_no = r.register_no
-- JOIN events e ON e.event_id = r.event_id
-- WHERE r.event_id = ?
-- ORDER BY r.registration_id
-- LIMIT ? OFFSET ?;
