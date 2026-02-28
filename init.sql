-- 创建数据库
CREATE DATABASE IF NOT EXISTS kp3 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE kp3;

-- 系统用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    status INT DEFAULT 1,
    org_id BIGINT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    role_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    menu_name VARCHAR(50) NOT NULL,
    menu_code VARCHAR(50) NOT NULL UNIQUE,
    url VARCHAR(200),
    icon VARCHAR(50),
    parent_id BIGINT DEFAULT 0,
    sort INT DEFAULT 0,
    type INT DEFAULT 1,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES sys_role(id),
    FOREIGN KEY (menu_id) REFERENCES sys_menu(id)
);

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES sys_user(id),
    FOREIGN KEY (role_id) REFERENCES sys_role(id)
);

-- 运营机构表
CREATE TABLE IF NOT EXISTS org_organization (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    contact_name VARCHAR(50),
    contact_phone VARCHAR(20),
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 运营机构用户表
CREATE TABLE IF NOT EXISTS org_org_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    org_id BIGINT NOT NULL,
    phone VARCHAR(20),
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (org_id) REFERENCES org_organization(id)
);

-- 预约用户表
CREATE TABLE IF NOT EXISTS org_booking_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    id_card VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 预约团队表
CREATE TABLE IF NOT EXISTS org_booking_team (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(100) NOT NULL,
    team_type VARCHAR(50),
    contact_name VARCHAR(50) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 场馆表
CREATE TABLE IF NOT EXISTS venue_venue (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    venue_name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    description TEXT,
    image_url VARCHAR(200),
    three_d_url VARCHAR(200),
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 活动分类表
CREATE TABLE IF NOT EXISTS venue_activity_category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    category_code VARCHAR(50) NOT NULL UNIQUE,
    sort INT DEFAULT 0,
    status INT DEFAULT 1,
    show_in_home INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 活动表
CREATE TABLE IF NOT EXISTS venue_activity (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    activity_name VARCHAR(100) NOT NULL,
    category_id BIGINT NOT NULL,
    category_name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    time_type VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    description TEXT,
    notice TEXT,
    max_people INT DEFAULT 1,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES venue_activity_category(id)
);

-- 活动时间段表
CREATE TABLE IF NOT EXISTS venue_activity_time (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    activity_id BIGINT NOT NULL,
    time_slot VARCHAR(50) NOT NULL,
    capacity INT DEFAULT 0,
    remaining INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES venue_activity(id)
);

-- 个人预约表
CREATE TABLE IF NOT EXISTS booking_personal (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    activity_name VARCHAR(100) NOT NULL,
    booking_date DATE NOT NULL,
    booking_time VARCHAR(50) NOT NULL,
    number_of_people INT DEFAULT 1,
    name VARCHAR(50) NOT NULL,
    id_card VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    additional_info TEXT,
    status INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES venue_activity(id)
);

-- 团队预约表
CREATE TABLE IF NOT EXISTS booking_team (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    activity_name VARCHAR(100) NOT NULL,
    booking_date DATE NOT NULL,
    booking_time VARCHAR(50) NOT NULL,
    team_type VARCHAR(50) NOT NULL,
    team_name VARCHAR(100) NOT NULL,
    number_of_people INT DEFAULT 1,
    contact_name VARCHAR(50) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    remarks TEXT,
    attachment VARCHAR(200),
    status INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES venue_activity(id)
);

-- 核销记录表
CREATE TABLE IF NOT EXISTS booking_verification (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL,
    booking_type VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    verify_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    operator VARCHAR(50) NOT NULL
);

-- 到访登记表
CREATE TABLE IF NOT EXISTS booking_visit (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    id_card VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    visit_date DATE NOT NULL,
    visit_time VARCHAR(50) NOT NULL,
    operator VARCHAR(50) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 资讯分类表
CREATE TABLE IF NOT EXISTS info_category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    category_code VARCHAR(50) NOT NULL UNIQUE,
    sort INT DEFAULT 0,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 资讯表
CREATE TABLE IF NOT EXISTS info_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    category_id BIGINT NOT NULL,
    category_name VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    cover_image VARCHAR(200),
    video_url VARCHAR(200),
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES info_category(id)
);

-- 浏览记录表
CREATE TABLE IF NOT EXISTS info_view_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    info_id BIGINT NOT NULL,
    user_id BIGINT,
    view_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (info_id) REFERENCES info_info(id)
);

-- 点赞记录表
CREATE TABLE IF NOT EXISTS info_like_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    info_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    like_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (info_id) REFERENCES info_info(id)
);

-- 插入默认数据
-- 系统用户
INSERT INTO sys_user (username, password, name, phone, email, status) VALUES
('admin', '$2a$10$E6Yh6880t2fXjNvQ0vDp9u3i2e8w2y7f9g8h7j6k5l4m3n2b1a', '管理员', '13800138000', 'admin@example.com', 1);

-- 角色
INSERT INTO sys_role (role_name, role_code, description, status) VALUES
('管理员', 'admin', '系统管理员', 1),
('运营人员', 'operator', '运营机构人员', 1);

-- 菜单
INSERT INTO sys_menu (menu_name, menu_code, url, icon, parent_id, sort, type, status) VALUES
('系统管理', 'system', '/system', 'setting', 0, 1, 0, 1),
('用户管理', 'user', '/system/user', 'user', 1, 1, 1, 1),
('角色管理', 'role', '/system/role', 'avatar', 1, 2, 1, 1),
('菜单管理', 'menu', '/system/menu', 'menu', 1, 3, 1, 1),
('运营机构管理', 'organization', '/organization', 'office-building', 0, 2, 0, 1),
('场馆和活动管理', 'venue', '/venue', 'location', 0, 3, 0, 1),
('预约管理', 'booking', '/booking', 'ticket', 0, 4, 0, 1),
('科普资讯管理', 'info', '/info', 'document', 0, 5, 0, 1),
('数据分析', 'analysis', '/analysis', 'data-analysis', 0, 6, 0, 1);

-- 活动分类
INSERT INTO venue_activity_category (category_name, category_code, sort, status, show_in_home) VALUES
('参观预约', 'visit', 1, 1, 1),
('科普活动', 'science', 2, 1, 1),
('科普研学', 'research', 3, 1, 1);

-- 资讯分类
INSERT INTO info_category (category_name, category_code, sort, status) VALUES
('科技新闻', 'tech', 1, 1),
('科普知识', 'science', 2, 1);

-- 场馆
INSERT INTO venue_venue (venue_name, address, description, image_url, three_d_url, status) VALUES
('科普科技馆', '北京市海淀区中关村南大街5号', '一座现代化的科普科技馆，展示各种科学知识和科技成果', 'https://example.com/venue1.jpg', 'https://example.com/3d/venue1', 1);

-- 活动
INSERT INTO venue_activity (activity_name, category_id, category_name, type, time_type, start_date, end_date, description, notice, max_people, status) VALUES
('参观科技馆', 1, '参观预约', '参观预约', '选日期', '2026-03-01', '2026-03-31', '参观科技馆的常设展览，了解科学知识', '请携带身份证，按时到达', 5, 1),
('科普实验', 2, '科普活动', '科普活动', '按排期', '2026-03-01', '2026-03-31', '参与有趣的科普实验，动手实践科学原理', '请穿着舒适的衣服，服从工作人员安排', 10, 1);

-- 活动时间段
INSERT INTO venue_activity_time (activity_id, time_slot, capacity, remaining) VALUES
(1, '09:00-11:00', 50, 50),
(1, '14:00-16:00', 50, 50),
(2, '10:00-11:30', 20, 20),
(2, '14:00-15:30', 20, 20);

-- 资讯
INSERT INTO info_info (title, category_id, category_name, content, cover_image, video_url, view_count, like_count, status) VALUES
('人工智能的发展历程', 1, '科技新闻', '人工智能（Artificial Intelligence，简称AI）是研究、开发用于模拟、延伸和扩展人的智能的理论、方法、技术及应用系统的一门新的技术科学。', 'https://example.com/info1.jpg', 'https://example.com/video1.mp4', 1000, 100, 1),
('黑洞的奥秘', 2, '科普知识', '黑洞是宇宙中最神秘的天体之一，本文将为您揭开其神秘面纱。', 'https://example.com/info2.jpg', 'https://example.com/video2.mp4', 800, 80, 1);
