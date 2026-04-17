-- MEM_NAME = #{memName},

create or replace function fn_snake_to_camel (
   snake varchar2
) return varchar2 is
   v_tmp varchar2(20);
begin
   v_tmp := substr(
      replace(
         initcap('a' || lower(snake)),
         '_',
         ''
      ),
      2
   );
   return v_tmp;
end;

---------------------------------------------------------------------------

select column_name
       || ' = '
       || '#{'
       || fn_snake_to_camel(column_name)
       || '},' as camelcasename
  from cols
 where table_name = 'MEMBER';