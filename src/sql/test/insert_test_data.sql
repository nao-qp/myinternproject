--users
INSERT INTO users (id, account, pass)
VALUE (1, '1', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu');

--profiles
INSERT INTO profiles (id, users_id, name, departments_id, roles_id, work_patterns_id)
VALUE (1, 1, '社員A', 1, 1, 1);

