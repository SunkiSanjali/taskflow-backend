CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50),
                       priority VARCHAR(50),
                       due_date DATE,

                       assignee_id BIGINT,
                       project_id BIGINT,

                       CONSTRAINT fk_task_user
                           FOREIGN KEY (assignee_id)
                               REFERENCES users(id),

                       CONSTRAINT fk_task_project
                           FOREIGN KEY (project_id)
                               REFERENCES projects(id)
);