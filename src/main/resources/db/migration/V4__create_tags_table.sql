CREATE TABLE tags (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100)
);

CREATE TABLE task_tags (
                           task_id BIGINT,
                           tag_id BIGINT,
                           PRIMARY KEY (task_id, tag_id),
                           FOREIGN KEY (task_id) REFERENCES tasks(id),
                           FOREIGN KEY (tag_id) REFERENCES tags(id)
);