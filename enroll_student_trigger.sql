create or replace trigger enrollments_trigger_insert
after insert on enrollments
for each row
begin
update classes set class_size=class_size+1 where classid= :new.classid;
end;
 /