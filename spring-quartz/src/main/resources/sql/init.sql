--删除表空间  HS_SYS_DATA
declare
  v_rowcount integer;
begin
  select count(*) into v_rowcount from dual where exists(select * from dba_tablespaces a where a.TABLESPACE_NAME = upper('HS_SYS_DATA'));
   if v_rowcount > 0 then
 	  execute immediate 'DROP tablespace HS_SYS_DATA including contents and datafiles';
   end if;
end;
/

--创建表空间HS_SYS_DATA
create tablespace HS_SYS_DATA
datafile '/opt/data/sysdat01.dbf'
size 100M
autoextend on next 10m maxsize unlimited
extent management local uniform size 8m
segment space management auto;

--删除表空间  HS_SYS_IDX
declare
  v_rowcount integer;
begin
  select count(*) into v_rowcount from dual where exists(select * from dba_tablespaces a where a.TABLESPACE_NAME = upper('HS_SYS_IDX'));
   if v_rowcount > 0 then
 	  execute immediate 'DROP tablespace HS_SYS_IDX including contents and datafiles';
   end if;
end;
/

--创建表空间HS_SYS_IDX
create tablespace HS_SYS_IDX
datafile '/opt/data/sysidx01.dbf'
size 100M
autoextend on next 10m maxsize unlimited
extent management local uniform size 8m
segment space management auto;

--删除用户  hs_sys
declare
  v_rowcount integer;
begin
  select count(*) into v_rowcount from dual where exists(select * from all_users a where a.username = upper('hs_sys'));
   if v_rowcount > 0 then
 	  execute immediate 'DROP USER hs_sys CASCADE';
   end if;
end;
/

--创建用户  hs_sys
CREATE USER hs_sys IDENTIFIED BY  "Hundsun123" DEFAULT TABLESPACE HS_SYS_DATA TEMPORARY TABLESPACE TEMP;
--用户  hs_sys 赋权限
GRANT CONNECT TO hs_sys;
GRANT RESOURCE TO hs_sys;
GRANT DBA TO hs_sys;
GRANT UNLIMITED TABLESPACE TO hs_sys;
GRANT create any table TO hs_sys;
GRANT select any table TO hs_sys;
GRANT drop any table TO hs_sys;
GRANT create any index TO hs_sys;
GRANT delete any table TO hs_sys;
GRANT insert any table TO hs_sys;
GRANT update any table TO hs_sys;
GRANT create any directory TO hs_sys;


