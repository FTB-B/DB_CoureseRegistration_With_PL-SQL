create or replace package display_tables as
procedure show_students(students_curs out sys_refcursor);
procedure show_courses(courses_curs out sys_refcursor);
procedure show_prereq(prereq_curs out sys_refcursor);
procedure show_classes(classes_curs out sys_refcursor);
procedure show_enrollments(enrollments_curs out sys_refcursor);
end;
/
create or replace package body display_tables as
procedure show_students(students_curs out sys_refcursor) as
begin
open students_curs for
select * from students;
end show_students;


procedure show_courses(courses_curs out sys_refcursor) as
begin
open courses_curs for
select * from courses;
end show_courses;


procedure show_prereq(prereq_curs out sys_refcursor) as
begin
open prereq_curs for
select * from prerequisites;
end show_prereq;



procedure show_classes(classes_curs out sys_refcursor) as
begin
open classes_curs for
select * from classes;
end show_classes;


procedure show_enrollments(enrollments_curs out sys_refcursor) as
begin
open enrollments_curs for
select * from enrollments;
end show_enrollments;




end display_tables;
/
show errors

