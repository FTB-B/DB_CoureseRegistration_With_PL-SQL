create or replace package insert_student_package as
 procedure insert_student(studentid in students.sid%type,Fname in 
students.firstname%type, Lname in students.lastname%type, Stat in 
students.status%type,gp in students.gpa%type,Mail in 
students.email%type);
 end;
 /
 show errors
 create or replace package body insert_student_package as
 procedure insert_student(studentid in students.sid%type,Fname in
students.firstname%type, Lname in students.lastname%type, Stat in
students.status%type,gp in students.gpa%type,Mail in
students.email%type)
 is
 begin 
    insert into 
students("SID","FIRSTNAME","LASTNAME","STATUS","GPA","EMAIL") 
values(studentid,Fname,Lname,Stat,gp,Mail);
COMMIT;
end insert_student;
end insert_student_package;
/
show errors

