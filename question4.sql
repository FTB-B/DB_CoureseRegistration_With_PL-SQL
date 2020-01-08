set serveroutput on
create or replace procedure student_info(studentid in students.sid%type, error_message OUT VARCHAR2,cur_info OUT sys_refcursor)
is
if_student_exist int;
course_reg int;
begin
select count(*) into if_student_exist  from students s where s.sid=studentid;
if if_student_exist = 0
then
 dbms_output.put_line('the sid is invalid');
error_message := 'The sid is invalid';
else
select count(*) into course_reg from students s,enrollments e, classes c where s.sid =studentid and e.sid = s.sid and e.classid = c.classid;
if course_reg = 0
then
 dbms_output.put_line('the student has not taken any course');
error_message := 'The student has not taken any courses';
else
open cur_info for
select s.sid, s.lastname, s.status, c.classid, concat(c.dept_code,c.course_no) coursID  from students s,enrollments 
e, classes c where s.sid=studentid and e.sid = s.sid and e.classid = c.classid;
end if;
end if; 
end;
/
show errors
