set serveroutput on
create or replace procedure class_info(id in classes.classid%type,error_message OUT VARCHAR2,cur_info OUT sys_refcursor)
is
if_class_exist int;
if_student_exist int;
begin
select count(*) into if_class_exist from classes c where c.classid=id;
if if_class_exist = 0
then 
dbms_output.put_line('The cid is invalid');
error_message := 'The cid is invalid';
else
select count(*) into if_student_exist from enrollments e where e.classid=id;
if if_student_exist = 0
then
dbms_output.put_line('No student enrolled in the class');
error_message := 'No student enrolled in the class';
else
open cur_info for
select c1.classid,c2.title,c1.semester,c1.year,s.sid,s.lastname from classes c1,courses c2, enrollments e, students s where c1.classid=id and c1.dept_code=c2.dept_code and c1.course_no= c2.course_no and c1.classid=e.classid and e.sid=s.sid;
end if;
end if;
end;
/
show errors

