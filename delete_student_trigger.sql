create or replace trigger delete_student_trigger
after delete on students
for each row
begin
delete from enrollments where sid= :old.sid;
end;
/
show errors