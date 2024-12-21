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
    st.username as advisor_name
from 
    user u
join 
    student s on u.user_id = s.student_id
join
    user st on s.advisor_id = st.user_id;

-- 教职工详细信息视图
create view staff_details as
select 
    u.user_id,
    u.username,
    u.email,
    u.phone,
    s.position
from 
    user u
join 
    staff s on u.user_id = s.staff_id;

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
    rr.is_pass as reviewer_score,
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