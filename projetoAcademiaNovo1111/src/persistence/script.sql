create table cliente(idCliente number(5) primary key,
					 nome varchar2(30));
					 
create table endereco(idEnd number(5) primary key,
					 rua varchar2(30),
					 id_cliente int,
					 foreign key(id_cliente) references cliente(idCliente));
					 
					 
insert into cliente values(1,'leonardo');
insert into cliente values(2,'jose');
insert into endereco values(100,'vera',1);
insert into endereco values(101,'Bom jardim',2);
commit;
					 
					 
create user academia
identified by coti
default tablespace users
quota 200m on users;

grant create table, create session, create procedure,
create view, create materialized view,
create sequence, create trigger, connect
to academia;

grant execute on utl_file to academia;

create or replace directory rel as 'c:\relatorio';

alter system set UTL_FILE_DIR='*' scope=spfile;
shutdown immediate;
startup;

conn / as  sysdba;

show parameters utl;

conn academia;





----------------------------------------

select  c.nome,e.rua from endereco e
inner join cliente c
on e.id_cliente=c.idCliente;



------------------------------

select c.nome, c.telefone, c.ativo, c.id_pacote,e.rua, e.bairro  from endereco e
inner join cliente c
on e.mat_Cliente=c.matCliente;








