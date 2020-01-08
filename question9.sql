set serveroutput on
create or replace procedure delete_student(std_id in students.sid%type)
is
student_exist int;
student_enrolled int;
begin
select count(*) into student_exist from students where sid=std_id;
if student_exist=0
then
dbms_output.put_line('the sid is invalid');
else
select count(*) into student_enrolled from students s, enrollments e 
where s.sid = std_id and s.sid = e.sid;
if student_enrolled = 0
then
dbms_output.put_line('student is not enrolled in any courses');
delete from students where sid=std_id;
commit;
dbms_output.put_line('student deleted sucessfully');
else
delete from enrollments where sid = std_id;
delete from students s where s.sid = std_id;
commit;
dbms_output.put_line('student deleted sucessfully from both');
end if;
end if;
end;
 /
 show errors
