create index idx_username on user(username);

create index idx_student_advisor_id on student(advisor_id);

create index idx_staff_position on staff(position);

create index idx_file_type on file(file_type);  

create index idx_topic_file_id on topic_submission(file_id);

create index idx_opening_report_file_id on opening_report(file_id);

create index idx_middle_report_file_id on middle_report(file_id);


create index idx_thesis_file_id on thesis(file_id);

create index idx_task_book_student_id on task_book(student_id);
create index idx_task_book_file_id on task_book(file_id);

create index idx_advisor_review_student_id on advisor_review(student_id);
create index idx_advisor_review_file_id on advisor_review(file_id);

create index idx_reviewer_review_student_id on reviewer_review(student_id);
create index idx_reviewer_review_file_id on reviewer_review(file_id);

create index idx_defense_review_student_id on defense_review(student_id);
create index idx_defense_review_file_id on defense_review(file_id);