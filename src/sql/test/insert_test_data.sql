--テストデータ


INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (1, '1', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '社員A', 1, 1, 1);
INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (2, '2', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '社員B', 1, 1, 1);
INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (3, '3', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '社員C', 1, 1, 1);
INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (4, '4', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '次長', 1, 2, 1);
INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (5, '5', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '課長', 1, 3, 1);
INSERT INTO users (id, account, pass, name, departments_id, roles_id, work_patterns_id)
VALUE (6, '6', '$2a$10$ma3yWLUZlHCD8gVP1fgB..eW1REVBmYh1SRdbGQzgIyct22uWTeGu', '人事部', 1, 4, 1);


INSERT INTO requests (id, users_id, departments_id, work_patterns_id, request_date, 
    start_time, end_time, rest_period, reason, approval_date, approval_users_id, is_checked, approval_status)
VALUE (1, 1, 1, 1, '2024-11-22',
    '2024-11-22 17:15:00', '2024-11-22 18:15:00', '00:00:00', '顧客対応のため', NULL, NULL, 0, NULL);
INSERT INTO requests (id, users_id, departments_id, work_patterns_id, request_date, 
    start_time, end_time, rest_period, reason, approval_date, approval_users_id, is_checked, approval_status)
VALUE (2, 1, 1, 1, '2024-11-23',
    '2024-11-23 17:15:00', '2024-11-23 18:15:00', '00:00:00', 'XX会議資料作成のため', NULL, NULL, 1, NULL);



INSERT INTO roles (id, name, role)
VALUE (1, '社員', '0');
INSERT INTO roles (id, name, role)
VALUE (2, '次長', '1');
INSERT INTO roles (id, name, role)
VALUE (3, '課長', '2');
INSERT INTO roles (id, name, role)
VALUE (4, '人事部', '3');


INSERT INTO departments (id, name)
VALUE (1, '営業部');