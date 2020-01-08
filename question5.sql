set serveroutput on
create or replace procedure 
course_info(deptcode in
prerequisites.dept_code%type,cnum in
prerequisites.course_no%type,count1 in number,
result out varchar2)
is
if_course_exist number;
count2 number;
pre_code prerequisites.dept_code%type;
pre_num prerequisites.course_no%type;
CURSOR cur_info is
select pre_dept_code, pre_course_no  FROM
Prerequisites WHERE dept_code=deptcode and course_no = cnum;
detail cur_info%rowtype;

begin
select count(*) into if_course_exist from prerequisites p where p.dept_code=deptcode and p.course_no=cnum;
open cur_info;
fetch cur_info into detail;
while(cur_info%found) loop
count2:=count1+1;
course_info(detail.pre_dept_code, detail.pre_course_no,count2,result);
if(result is NULL) then
result := detail.pre_dept_code || detail.pre_course_no;
else
result := result || ',' || detail.pre_dept_code ||
detail.pre_course_no;
end if;

fetch cur_info into detail;
end loop;
if(if_course_exist=0 and count1=0)then
 result := ' does not exist';
end if;
end;
/
show errors
