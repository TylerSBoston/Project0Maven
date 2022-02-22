
Drop View view_transactions;
Drop Table "Transactions";
Drop Table "Accounts";
Drop Table "AccountTypes";
Drop Table "Customers";
Drop Table "Employees";
Drop Table "Users";
-- Creates and Replaced tables, dont use if live

Create Table "Users" (
	"ID" 			integer 	generated always as identity,
	"firstName" 	varchar(30)	not null,
	"lastName" 		varchar(30)	not null,
	"email" 		varchar(50)	,
	"phone" 		varchar(20)	,
	"userName" 		varchar(30)	not null,
	"password" 		varchar(30)	not null,
	Primary key("ID"));
	

	
Create Table "Customers" (
	"ID" 			integer 	generated always as identity,
	"userID"		integer		not null,
	"active"		integer		not null,
	Primary Key("ID"),
	Constraint "fk_Users" Foreign Key("userID") References "Users"("ID"));

Create Table "Employees" (
	"ID" 			integer 	generated always as identity,
	"userID"		integer		not null,
	Primary Key("ID"),
	Constraint "fk_Users" Foreign Key("userID") References "Users"("ID"));
	
Create Table "AccountTypes" (
	"ID" 			integer 	not null,
	"type"			varchar(15)	not null,
	Primary Key("ID"));
	
Create Table "Accounts" (
	"ID" 			integer 	generated always as identity,
	"balance"		decimal		not null default 0,
	"customerID" 	Integer		not null,
	"routingNumber" varchar(9)	not null default '111111111',
	"accountNumber" varchar(17)	not null unique,
	"accountTypeID" integer		not null,
	"accountName"	varchar(30) not null,
	Primary Key("ID"),
	Constraint "fk_Customers" Foreign Key("customerID") References "Customers"("ID"),
	Constraint "fk_AccountTypes" Foreign Key("accountTypeID") References "AccountTypes"("ID"));


	
Create Table "Transactions" (
	"ID" 			integer 	generated always as identity,
	"targetID"		integer		not null,
	"sendingID"		integer		not null,
	"amount"		decimal		not null,
	"date"			date		not null,
	Primary Key("ID"),
	Constraint "fk_AccountsTarget" Foreign Key("targetID") References "Accounts"("ID"),
	Constraint "fk_AccountsSending" Foreign Key("sendingID") References "Accounts"("ID"));
	
Insert Into "AccountTypes"("ID","type")
	Values  (0,'checkings'),
			(1,'savings'),
			(2,'student'),
			(3,'investment');
			
create or replace view view_transactions (sending,sending_user, amount, recieving,recieving_user,date,sender_id,recieving_id) 	
	as select sender."accountName" as sending,sending_u."userName" as sending_user, "Transactions"."amount" as amount, reciever."accountName" 
	as recieving,recieving_u."userName" as recieving_user, "Transactions"."date" as date,sending_c."ID" as sender_id , recieving_c."ID" as recieving_id
	from "Transactions" 
	inner join "Accounts" as sender 
	on sender."ID" = "Transactions"."sendingID" 
	inner join "Accounts" as reciever
	on reciever."ID" = "Transactions"."targetID"
	inner join "Customers" as sending_c
	on sending_c."ID" = sender."customerID"
	inner join "Customers" as recieving_c
	on recieving_c."ID" = reciever."customerID"
	inner join "Users" as sending_u
	on sending_u."ID" = sending_c."userID"
	inner join "Users" as recieving_u
	on recieving_u."ID" = recieving_c."userID"
	order by date;

			
			
Insert Into "Users"("firstName","lastName","email","phone","userName","password")
	values 	('employee','employee','employee@email.com','1231231234','employee','1234');
			
Insert Into "Employees"("userID")
	values 	(1);
	
CREATE OR REPLACE PROCEDURE public."AddCustomer"(IN f_name character varying, IN l_name character varying, IN e_mail character varying, IN phone_number character varying, IN u_name character varying, IN pass character varying)
    LANGUAGE 'sql'
   
   
   
   -- Procedures, not all are used
AS $BODY$
INSERT INTO "Users"("firstName","lastName","email","phone","userName","password")
Values(f_name,l_name,e_mail,phone_number,u_name,pass);

 
INSERT INTO "Customers"("userID","active")
Values(((Select "ID" from "Users" where "userName" = u_name AND "password" = pass )),0);
$BODY$;


CREATE OR REPLACE PROCEDURE public."CustomerLogin"(INOUT user_name character varying, IN pass_word character varying, OUT customer_id integer, OUT status integer)
    LANGUAGE 'sql'
    
AS $BODY$
select "Users"."userName" as user_name,  "Customers"."ID" as customer_id,"Customers"."active" as status 
from "Users","Customers"  
where "userName" = user_name and "password" = pass_word and "Customers"."userID" = "Users"."ID" 

 
$BODY$;
	
CREATE OR REPLACE PROCEDURE public."EmployeeLogin"(INOUT user_name character varying, IN pass_word character varying, OUT employee_id integer)
    LANGUAGE 'sql'
    
AS $BODY$
select "Users"."userName" as user_name,  "Employees"."ID" as employee_id
from "Users","Employees"  
where "userName" = user_name and "password" = pass_word and "Employees"."userID" = "Users"."ID" 

 
$BODY$;

CREATE OR REPLACE PROCEDURE public.addtransaction(IN amount numeric, IN sender integer, IN routing character varying, IN account character varying)
    LANGUAGE 'sql'
    
AS $BODY$
update "Accounts" Set balance = balance - amount where "ID" = sender;

update "Accounts" set balance = balance + amount where "routingNumber" = routing and "accountNumber" = account;
 
insert into "Transactions" ("amount","targetID","sendingID","date") 
values  (amount,(select "ID" from "Accounts" where "routingNumber" = routing and "accountNumber" = account),sender,(select CURRENT_DATE));
 
$BODY$;

CREATE OR REPLACE PROCEDURE public."createAccount"(IN customer_id integer, IN type_id integer, IN initial_balance numeric, IN account_name character varying)
    LANGUAGE 'sql'
    
AS $BODY$
insert into "Accounts" ("balance","customerID","accountNumber","accountTypeID","accountName")
    values ("initial_balance","customer_id",
           (SELECT array_to_string(ARRAY(SELECT chr((48 + round(random() * 9)) :: integer)  FROM generate_series(1,9)), ''))
           ,"type_id",account_name); 
 
 
$BODY$;

CREATE OR REPLACE PROCEDURE public."getAccounts"(IN customer_id integer, OUT balance numeric, OUT account_name character varying, OUT routing character varying, OUT account character varying, OUT account_type integer, OUT account_id integer)
    LANGUAGE 'sql'
    
AS $BODY$
select "balance" as balance,"accountName" as account_name,"routingNumber" as routing,"accountNumber" as account, "accountTypeID" as account_type, "ID" as account_id
from "Accounts"
where customer_id = "Accounts"."customerID" 
 
$BODY$;

CREATE OR REPLACE PROCEDURE public."getBalance"(OUT bal numeric, IN account integer)
    LANGUAGE 'sql'
    
AS $BODY$
select "Accounts"."balance"  as bal from "Accounts" where "Accounts"."ID" = account
 
$BODY$;

CREATE OR REPLACE PROCEDURE public."getUnVerifiedCustomers"(OUT customer_id integer, OUT first_name character varying, OUT last_name character varying, OUT e_mail character varying, OUT phone character varying)
    LANGUAGE 'sql'
    
AS $BODY$
select "Customers"."ID" as customer_id,
       "Users"."firstName" as first_name,
       "Users"."lastName"  as last_name,
       "Users"."email"     as e_mail,
       "Users"."phone"     as phone
from "Customers","Users"
where "Customers"."userID" = "Users"."ID" and "Customers"."active" = 0; 
$BODY$;

CREATE OR REPLACE PROCEDURE public.set_registration(IN customer_id integer, IN target_value integer)
    LANGUAGE 'sql'
    
AS $BODY$
update "Customers" set active = target_value where "ID" = customer_id 
$BODY$;