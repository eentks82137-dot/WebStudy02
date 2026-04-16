select '#{'
       || substr(
   replace(
      initcap(lower('a' || column_name)),
      '_',
      ''
   ),
   2
)
       || '}, '
  from cols
 where table_name = upper('member');