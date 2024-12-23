SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists thesis;

use thesis;

-- 用户表
create table if not exists user
(
    user_id int auto_increment primary key, -- 主键，用户编号(教职工号/学号)
    username varchar(255) not null, -- 姓名
    password varchar(255) not null default '111111', -- 密码(用于登录系统)
    email varchar(255) default null, -- 邮箱(非必需)
    phone varchar(255) default null, -- 电话号码(非必需)
    role enum('学生', '教职工') not null, -- 角色(学生、教职工)
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp
);

-- 学生表
create table if not exists student
(
    student_id int primary key, -- 主键，学号(关联user.user_id)
    grade varchar(255) not null, -- 年级
    class varchar(255) not null, -- 班级
    advisor_id int, -- 指导老师编号(关联staff.staff_id, 角色为指导老师)
    foreign key (advisor_id) references staff(staff_id)
);

-- 教职工表
create table if not exists staff
(
    staff_id int primary key, -- 主键，教职工号(关联user.user_id)
    position enum('秘书', '院领导', '专业负责人', '答辩小组组长', '答辩小组成员', '评阅教师', '指导老师') not null, -- 职位
    foreign key (staff_id) references user(user_id)
);

-- 答辩小组表
create table if not exists defense_group
(
    group_id int auto_increment primary key, -- 主键，答辩小组编号
    leader_id int not null, -- 组长编号(关联staff.staff_id, 职位为答辩小组组长)
    foreign key (leader_id) references staff(staff_id)
);

-- 答辩小组成员表
create table if not exists defense_group_member
(
    group_id int not null, -- 答辩小组编号(关联defense_group.group_id)
    member_id int not null, -- 成员编号(关联staff.staff_id, 职位为答辩小组成员)
    foreign key (group_id) references defense_group(group_id),
    foreign key (member_id) references staff(staff_id),
    primary key (group_id, member_id)
);

-- 文件表
create table if not exists file
(
    file_id int auto_increment primary key, -- 主键，文件编号
    owner_id int not null, -- 拥有者编号(关联user.user_id)
    file_name varchar(255) not null, -- 文件名
    file_path varchar(255) not null, -- 文件路径
    file_type enum('论文撰写规范','选题申报表', '开题报告', '中期报告', '论文', '课题任务书', '指导教师审阅表', '评阅教师审阅表', '答辩评审表') not null, -- 文件类型
    version int not null default 1, -- 版本号
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp,
    foreign key (owner_id) references user(user_id)
);

-- 论文撰写规范表
create table if not exists thesis_writing_standard
(
    standard_id int auto_increment primary key, -- 主键，论文撰写规范编号
    standard_name varchar(255) not null, -- 论文撰写规范名称
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为论文撰写规范)
    foreign key (file_id) references file(file_id)
);

-- 选题申报表
create table if not exists topic_submission
(
    topic_id int auto_increment primary key, -- 主键，选题编号
    title varchar(255) not null, -- 选题题目
    description text, -- 描述
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为选题申报表)
    review_opinion text, -- 专业负责人审查意见
    review_status enum('0', '1') default '0' not null, -- 专业负责人审查状态(0: 未审查, 1: 已审查)
    leader_opinion text, -- 院领导审定意见 text,
    leader_status enum('0', '1') default '0' not null, -- 院领导审定状态(0: 未审定, 1: 已审定)
    foreign key (file_id) references file(file_id)
);

-- 开题报告表
create table if not exists opening_report
(
    report_id int auto_increment primary key, -- 主键，开题报告编号
    opening_name varchar(255) not null, -- 开题报告名称
    description text, -- 描述
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为开题报告)
    review_opinion text, -- 专业负责人审查意见
    review_status enum('0', '1') default '0' not null, -- 专业负责人审查状态(0: 未审查, 1: 已审查)
    foreign key (file_id) references file(file_id)
);

-- 中期报告表
create table if not exists middle_report
(
    report_id int auto_increment primary key, -- 主键，中期报告编号
    middle_name varchar(255) not null, -- 中期报告名称
    description text, -- 描述
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为中期报告)
    foreign key (file_id) references file(file_id)
);

-- 论文表
create table if not exists thesis
(
    thesis_id int auto_increment primary key, -- 主键，论文编号
    thesis_name varchar(255) not null, -- 论文名称
    description text, -- 描述
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为论文)
    foreign key (file_id) references file(file_id)
);

-- 课题任务书表
create table if not exists task_book
(
    task_id int auto_increment primary key, -- 主键，课题任务书编号
    student_id int not null, -- 学生编号(关联student.student_id)
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为课题任务书)
    task_name varchar(255) not null, -- 课题名称
    description text, -- 描述
    review_opinion text, -- 专业负责人审查意见
    review_status enum('0', '1') default '0' not null, -- 专业负责人审查状态(0: 未审查, 1: 已审查)
    leader_opinion text, -- 院领导审定意见 text,
    leader_status enum('0', '1') default '0' not null, -- 院领导审定状态(0: 未审定, 1: 已审定)
    foreign key (student_id) references student(student_id),
    foreign key (file_id) references file(file_id)
);

-- 指导教师审阅表
create table if not exists advisor_review
(
    review_id int auto_increment primary key, -- 主键，指导教师审阅表编号
    review_name varchar(255) not null, -- 审阅名称
    description text, -- 描述
    student_id int not null, -- 学生编号(关联student.student_id)
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为指导教师审阅表)
    score int not null, -- 成绩
    is_pass enum('0', '1') not null, -- 是否通过答辩(0: 未通过, 1: 通过)
    foreign key (student_id) references student(student_id),
    foreign key (file_id) references file(file_id)
);

-- 评阅教师审阅表
create table if not exists reviewer_review
(
    review_id int auto_increment primary key, -- 主键，评阅教师审阅表编号
    review_name varchar(255) not null, -- 审阅名称
    description text, -- 描述
    student_id int not null, -- 学生编号(关联student.student_id)
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为评阅教师审阅表)
	score int not null, -- 成绩
    is_pass enum('0', '1') not null, -- 是否通过答辩(0: 未通过, 1: 通过)
    foreign key (student_id) references student(student_id),
    foreign key (file_id) references file(file_id)
);

-- 答辩评审表
create table if not exists defense_review
(
    review_id int auto_increment primary key, -- 主键，答辩评审表编号
    review_name varchar(255) not null, -- 审阅名称
    description text, -- 描述
    student_id int not null, -- 学生编号(关联student.student_id)
    file_id int not null, -- 文件编号(关联file.file_id, 文件类型为答辩评审表)
    score enum('优秀', '良好', '中等', '及格', '不及格') not null, -- 答辩最终成绩
    foreign key (student_id) references student(student_id),
    foreign key (file_id) references file(file_id)
);