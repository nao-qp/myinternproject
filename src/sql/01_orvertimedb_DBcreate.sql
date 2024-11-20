--DB作成
create database overtimedb;
USE overtimedb;

--文字コード、文字照合順序を設定
ALTER DATABASE overtimedb
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


--テーブル作成
CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    account VARCHAR(20) NOT NULL COMMENT '社員番号',
    pass VARCHAR(60) NOT NULL COMMENT 'パスワード',
    is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '削除フラグ',
    create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY(id),
    UNIQUE(account),
    INDEX IX_users_account (account)
) COMMENT = 'ユーザー情報'
;

CREATE TABLE profiles (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    users_id INT NOT NULL COMMENT 'ユーザーID',
    name VARCHAR(20) NOT NULL COMMENT '社員名',
    departments_id INT NOT NULL COMMENT '部署',
    roles_id INT NOT NULL COMMENT '役職',
    work_patterns_id INT NOT NULL COMMENT '勤務パターン',
    create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY(id),
    INDEX IX_profiles_users_id (users_id)
) COMMENT = 'プロフィール情報'
;

