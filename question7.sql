set serveroutput on 
create or replace procedure enroll_student(std_id in students.sid%type,cl_id in classes.classid%type,error_message OUT VARCHAR2) 
is 
student_exist int; 
class_exist int; 
count_wrongs int;
check_student int;
count_enroll int;
dep_code classes.dept_code%type;
cnum classes.course_no%type;
pre_classes varchar2(100);
pre_count int;
pre_count2 int;
begin 
select count(*) into student_exist from students s where std_id=s.sid; 
select count(*) into class_exist from classes c where cl_id = c.classid;
select count(*) into count_wrongs from classes c where c.class_size+1>limit and cl_id = c.classid; 
select count(*) into check_student from enrollments e where e.sid=std_id and e.classid = cl_id;
select count(*) into count_enroll from enrollments e where e.sid=std_id;
select dept_code,course_no into dep_code,cnum from classes where classid=cl_id;
course_info(dep_code, cnum, 0,pre_classes);

select count(*) into pre_count from enrollments e,classes c where sid = std_id and e.classid = c.classid 
and INSTR(pre_classes, c.dept_code || c.course_no) != 0 and e.lgrade not in ('A','A-','B+','B','B-','C+','C');

select count(*) into pre_count2 from enrollments e,classes c where sid = std_id and e.classid = c.classid 
and INSTR(pre_classes, c.dept_code || c.course_no) = 0;
if 
student_exist=0 
then
error_message := 'invalid sid';
dbms_output.put_line('sid not Found');

elsif class_exist=0 
then
error_message := 'invalid cid';
dbms_output.put_line('cid not found');

elsif count_wrongs > 0 
then
error_message := 'The class is full';
dbms_output.put_line('The class is full');

elsif check_student>0 
then
error_message := 'The student has already take the course';
dbms_output.put_line('The student is already in this class');

elsif count_enroll>3 
then
error_message := 'Students cannot be enrolled in more than four classes in the same semester';
dbms_output.put_line('Students cannot be enrolled in more than four classes in the same semester');

elsif pre_count2>0 
then
error_message := 'Student has not completed the Prerequisites';
dbms_output.put_line('Student has not completed the Prerequisites');
elsif pre_count>0 
then
error_message := 'Student has not completed the Prerequisites';
dbms_output.put_line('Pass: Student has not completed the Prerequisites');

else
if count_enroll=2 
then
dbms_output.put_line('You are over loaded');
insert into enrollments values (std_id,cl_id,null);
error_message := 'Successfully enrolled and you are over loaded';
else
error_message := 'Successfully enrolled';
insert into enrollments values (std_id,cl_id,null);
end if;
end if;
end;
/
show errors