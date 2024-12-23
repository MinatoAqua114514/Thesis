-- 学生详细信息视图
create view student_details as
select
    u.user_id,
    u.username,
    u.email,
    u.phone,
    s.grade,
    s.class,
    s.advisor_id,
    coalesce(st.username, '未分配指导老师') as advisor_name
from
    user u
        left join
    student s on u.user_id = s.student_id
        left join
    user st on s.advisor_id = st.user_id
where
    u.role = '学生';

-- 教职工详细信息视图
CREATE VIEW staff_details AS
SELECT
    u.user_id,
    u.username,
    u.email,
    u.phone,
    COALESCE(s.position, '未分配职位') AS position
FROM
    user u
LEFT JOIN
    staff s ON u.user_id = s.staff_id
WHERE
    u.role = '教职工';

-- 学生文件信息视图
create view student_files as
select 
    s.student_id,
    u.username as student_name,
    f.file_id,
    f.file_name,
    f.file_type,
    f.version,
    f.create_time,
    f.update_time
from 
    student s
join 
    user u on s.student_id = u.user_id
join 
    file f on u.user_id = f.owner_id;


-- 指导老师的学生视图
create view advisor_students as
select 
    s.staff_id,
    u.username as advisor_name,
    st.student_id,
    su.username as student_name,
    st.grade,
    st.class
from 
    staff s
join 
    user u on s.staff_id = u.user_id
join 
    student st on s.staff_id = st.advisor_id
join 
    user su on st.student_id = su.user_id;

-- 答辩小组成员视图
create view defense_group_members as
select 
    dg.group_id,
    u.username as leader_name,
    dgm.member_id,
    mu.username as member_name
from 
    defense_group dg
join 
    user u on dg.leader_id = u.user_id
join 
    defense_group_member dgm on dg.group_id = dgm.group_id
join 
    user mu on dgm.member_id = mu.user_id;

-- 指导教师审阅详细信息视图
create view advisor_review_details as
select 
    ar.review_id,
    ar.student_id,
    su.username as student_name,
    f.owner_id as advisor_id,
    au.username as advisor_name,
    ar.score,
    ar.is_pass,
    ar.file_id,
    f.file_name,
    f.file_path,
    f.version,
    f.create_time,
    f.update_time
from 
    advisor_review ar
join 
    student s on ar.student_id = s.student_id
join 
    user su on su.user_id = ar.student_id
join 
    file f on ar.file_id = f.file_id
join 
    user au on f.owner_id = au.user_id;

-- 评阅教师审阅详细信息视图
create view reviewer_review_details as
select 
    rr.review_id,
    rr.student_id,
    su.username as student_name,
    f.owner_id as reviewer_id,
    ru.username as reviewer_name,
    rr.is_pass,
    rr.file_id,
    f.file_name,
    f.file_path,
    f.version,
    f.create_time,
    f.update_time
from 
    reviewer_review rr
join 
    student stu on rr.student_id = stu.student_id
join 
    user su on su.user_id = stu.student_id
join 
    file f on rr.file_id = f.file_id
join 
    user ru on f.owner_id = ru.user_id;

-- 学生成绩管理视图
create view student_grades as
select
    s.student_id,
    u.username as student_name,
    ar.score as advisor_score,
    rr.score as reviewer_score,
    dr.score as defense_score
from
    student s
join
    user u on s.student_id = u.user_id
left join
    advisor_review ar on s.student_id = ar.student_id
left join
    reviewer_review rr on s.student_id = rr.student_id
left join
    defense_review dr on s.student_id = dr.student_id;


-- 学生文件状态信息和成绩视图
CREATE VIEW student_submission_status AS
SELECT
    u.user_id,
    u.username,
    CASE
        WHEN MAX(ts.topic_id) IS NOT NULL THEN '已提交'
        ELSE '未提交'
        END AS topic_submission_status,
    COALESCE(MAX(ts.title), '') AS topic_title,
    CASE
        WHEN MAX(orp.report_id) IS NOT NULL THEN '已提交'
        ELSE '未提交'
        END AS opening_report_status,
    COALESCE(MAX(orp.opening_name), '') AS opening_report_name,
    CASE
        WHEN MAX(mr.report_id) IS NOT NULL THEN '已提交'
        ELSE '未提交'
        END AS middle_report_status,
    COALESCE(MAX(mr.middle_name), '') AS middle_report_name,
    CASE
        WHEN MAX(th.thesis_id) IS NOT NULL THEN '已提交'
        ELSE '未提交'
        END AS thesis_status,
    COALESCE(MAX(th.thesis_name), '') AS thesis_name,
    MAX(ar.score) AS advisor_review_score,
    MAX(rr.score) AS reviewer_review_score,
    MAX(dr.score) AS defense_review_score
FROM
    user u
        LEFT JOIN
    file f_ts ON u.user_id = f_ts.owner_id AND f_ts.file_type = '选题申报表'
        LEFT JOIN
    topic_submission ts ON f_ts.file_id = ts.file_id
        LEFT JOIN
    file f_or ON u.user_id = f_or.owner_id AND f_or.file_type = '开题报告'
        LEFT JOIN
    opening_report orp ON f_or.file_id = orp.file_id
        LEFT JOIN
    file f_mr ON u.user_id = f_mr.owner_id AND f_mr.file_type = '中期报告'
        LEFT JOIN
    middle_report mr ON f_mr.file_id = mr.file_id
        LEFT JOIN
    file f_th ON u.user_id = f_th.owner_id AND f_th.file_type = '论文'
        LEFT JOIN
    thesis th ON f_th.file_id = th.file_id
        LEFT JOIN
    student s ON u.user_id = s.student_id
        LEFT JOIN
    advisor_review ar ON s.student_id = ar.student_id
        LEFT JOIN
    reviewer_review rr ON s.student_id = rr.student_id
        LEFT JOIN
    defense_review dr ON s.student_id = dr.student_id
WHERE
    u.role = '学生'
GROUP BY
    u.user_id, u.username;





-- 学生选题申报视图
CREATE VIEW topicView AS
SELECT
    u.user_id,
    u.username,
    IF(MAX(ts.topic_id) IS NOT NULL, '已提交', '未提交') AS topic_submission_status,
    COALESCE(MAX(ts.title), '')                          AS topic_title,
    COALESCE(MAX(ts.topic_id), 0)                        AS topic_id
FROM
    user u
        LEFT JOIN
    file f_ts ON u.user_id = f_ts.owner_id AND f_ts.file_type = '选题申报表'
        LEFT JOIN
    topic_submission ts ON f_ts.file_id = ts.file_id
        LEFT JOIN
    student s ON u.user_id = s.student_id
WHERE
    u.role = '学生'
GROUP BY
    u.user_id, u.username;






-- 学生开题报告视图
CREATE VIEW openingView AS
SELECT
    u.user_id,
    u.username,
    IF(MAX(orp.report_id) IS NOT NULL, '已提交', '未提交') AS opening_report_status,
    COALESCE(MAX(orp.opening_name), '')                     AS opening_report_name,
    COALESCE(MAX(orp.report_id), 0)                         AS opening_report_id
FROM
    user u
        LEFT JOIN
    file f_or ON u.user_id = f_or.owner_id AND f_or.file_type = '开题报告'
        LEFT JOIN
    opening_report orp ON f_or.file_id = orp.file_id
        LEFT JOIN
    student s ON u.user_id = s.student_id
WHERE
    u.role = '学生'
GROUP BY
    u.user_id, u.username;




