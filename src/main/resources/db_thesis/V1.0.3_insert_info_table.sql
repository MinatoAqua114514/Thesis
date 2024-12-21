-- 录入用户基本信息
insert into user (user_id, username, password, email, phone, role) values 
(1, '秘书A', 'password', 'staffA@example.com', '0987654321', '教职工'),
(2, '学生B', 'password', 'studentB@example.com', '1234567890', '学生'),
(3, '指导老师C', 'password', 'staffC@example.com', '0987654321', '教职工'),
(4, '专业负责人D', 'password', 'staffD@example.com', '0987654321', '教职工'),
(5, '院领导E', 'password', 'staffE@example.com', '0987654321', '教职工'),
(6, '评阅教师F', 'password', 'staffF@example.com', '0987654321', '教职工'),
(7, '答辩小组组长G', 'password', 'staffG@example.com', '0987654321', '教职工'),
(8, '答辩小组成员H', 'password', 'staffH@example.com', '0987654321', '教职工');

-- 录入学生信息
insert into student (student_id, grade, class, advisor_id) values 
(2, '2022', '软件工程1班', 3);

-- 录入教职工信息
insert into staff (staff_id, position) values 
(1, '秘书'),
(3, '指导老师'),
(4, '专业负责人'),
(5, '院领导'),
(6, '评阅教师'),
(7, '答辩小组组长'),
(8, '答辩小组成员');

-- 录入答辩小组信息
insert into defense_group (leader_id) values 
(7);

-- 录入答辩小组成员信息
insert into defense_group_member (group_id, member_id) values 
(1, 8);

-- 上传论文撰写规范文件
insert into file (owner_id, file_name, file_path, file_type) values 
(1, '论文撰写规范PDF', '/path/to/fileA', '论文撰写规范');

-- 提交论文撰写规范
insert into thesis_writing_standard (standard_name, file_id) values 
('2022届论文撰写规范', 1);




-- 学生上传了选题申报表、开题报告、中期报告和论文的文件
insert into file (owner_id, file_name, file_path, file_type) values 
(2, '选题申报表Word', '/path/to/fileB', '选题申报表'),
(2, '开题报告Word', '/path/to/fileC', '开题报告'),
(2, '中期报告Word', '/path/to/fileD', '中期报告'),
(2, '论文Word', '/path/to/fileE', '论文');

-- 学生提交了选题申报表
insert into topic_submission (title, description, file_id) values 
('我的选题申报表A', '选题申报表的描述', 2);

-- 学生提交了开题报告
insert into opening_report (opening_name, description, file_id) values 
('我的开题报告A', '开题报告的描述', 3);

-- 学生提交了中期报告
insert into middle_report (middle_name, description, file_id) values 
('我的中期报告A', '中期报告的描述', 4);

-- 学生提交了论文
insert into thesis (thesis_name, description, file_id) values 
('我的论文A', '论文的描述', 5);




-- 指导老师上传了课题任务书的文件
insert into file (owner_id, file_name, file_path, file_type) values 
(3, '课题任务书Word', '/path/to/fileF', '课题任务书');

-- 指导老师提交了课题任务书
insert into task_book (task_name, description, student_id, file_id) values 
('我的课题任务书A', '课题任务书的描述', 2, 6);

-- 指导老师上传了指导老师审阅表的文件
insert into file (owner_id, file_name, file_path, file_type) values 
(3, '指导老师审阅表Word', '/path/to/fileG', '指导教师审阅表');

-- 指导老师提交了指导老师审阅表
insert into advisor_review (review_name, description, student_id, file_id, score, is_pass) values 
('我的指导老师审阅表A', '指导老师审阅表的描述', 2, 7, 85, '1');




-- 专业负责人审批了选题申报表
update topic_submission 
set review_opinion = '专业负责人对选题申报表A的审批意见', review_status = '1' 
where file_id = 2;

-- 专业负责人审批了开题报告
update opening_report 
set review_opinion = '专业负责人对开题报告A的审批意见', review_status = '1' 
where file_id = 3;

-- 专业负责人审批了课题任务书
update task_book
set review_opinion = '专业负责人对课题任务书A的审批意见', review_status = '1' 
where file_id = 6;




-- 院领导审批了选题申报表
update topic_submission 
set leader_opinion = '选题申报表A的审批意见', leader_status = '1' 
where file_id = 2;

-- 假设院领导审批了课题任务书
update task_book 
set leader_opinion = '课题任务书A的审批意见', leader_status = '1' 
where file_id = 3;




-- 假设评阅教师E上传了评阅教师审阅表A的文件
insert into file (owner_id, file_name, file_path, file_type) values 
(5, '评阅教师审阅表A', '/path/to/fileH', '评阅教师审阅表');

-- 假设评阅教师E提交了评阅教师审阅表A
insert into reviewer_review (review_name, description, student_id, file_id, score, is_pass) values 
('我的评阅教师审阅表A', '评阅教师审阅表的描述', 2, 8, 85, '1');




-- 假设答辩小组组长上传了答辩小组审阅表A的文件
insert into file (owner_id, file_name, file_path, file_type) values 
(6, '答辩小组审阅表A', '/path/to/fileI', '答辩评审表');

-- 假设答辩小组组长提交了答辩小组审阅表A
insert into defense_review (review_name, description, student_id, file_id, score) values 
('我的答辩小组审阅表A', '答辩小组审阅表的描述', 2, 9, '良好');