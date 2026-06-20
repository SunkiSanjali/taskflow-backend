INSERT INTO users (name, email) VALUES
                                    ('Alice', 'alice@mail.com'),
                                    ('Bob', 'bob@mail.com');

INSERT INTO projects (name) VALUES
                                ('TaskFlow App'),
                                ('Intern Project');

INSERT INTO tasks
(title, description, status, priority, due_date, assignee_id, project_id)
VALUES
    ('Setup backend', 'Initial Spring Boot setup', 'OPEN', 'HIGH', '2026-06-25', 1, 1),
    ('Design DB', 'Create schema design', 'IN_PROGRESS', 'MEDIUM', '2026-06-28', 2, 1);