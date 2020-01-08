create or replace trigger enrollments_trigger_delete
after delete on enrollments
for each row
begin
update classes set class_size=class_size-1 where classid=:old.classid;
end;
 /
