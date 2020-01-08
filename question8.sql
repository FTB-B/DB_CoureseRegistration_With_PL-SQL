set serveroutput on 
create or replace procedure delete_enrollment(std_id in students.sid%type,cl_id in classes.classid%type, error_message OUT VARCHAR2) 
is 
student_exist int; 
class_exist int; 
enroll_exist int; 
course_count int;
student_count int;
dep_code classes.dept_code%type;
cnum classes.course_no%type;
pre_count int;
begin 
select count(*) into student_exist from students s where std_id=s.sid; 
select count(*) into class_exist from classes c where cl_id = c.classid; 
select count(*) into enroll_exist from enrollments e where e.sid=std_id and e.classid=cl_id; 
if 
student_exist=0 
then
error_message := 'SID is invalid!';
dbms_output.put_line('sid not Found');
commit;
elsif class_exist=0 
then
error_message := 'CID is invalid!';
dbms_output.put_line('cid not found');
commit;
elsif enroll_exist=0 
then
error_message := 'Student is not enrolled in the class';
dbms_output.put_line('Student is not enrolled in the class');
commit;
else

select dept_code,course_no into dep_code, cnum from classes where classid=cl_id;

select count(*) into pre_count from classes cl,prerequisites p where cl.classid in 
(select classid from enrollments e where e.classid != cl_id and e.sid=std_id) 
and cl.dept_code=p.dept_code and cl.course_no = p.course_no and
p.pre_dept_code=dep_code and
p.pre_course_no=cnum;
if(pre_count=0) then
delete from enrollments e where e.sid = std_id and e.classid=cl_id;
error_message := 'The student dropped successfully';
dbms_output.put_line('The student dropped successfully');
else
error_message := 'The drop can not be done since it is prerequisite for other class';
dbms_output.put_line('The drop can not be done since it is prerequisite for other class');
end if;
end if;

end;
/
show errors
