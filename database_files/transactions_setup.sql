--Create Database
create database transactions;

--use database
use transactions;

--Create tables
create table users(id int NOT NULL auto_increment,first_name varchar(50) NOT NULL,middle_name varchar(50),last_name varchar(50) NOT NULL,username varchar(50) NOT NULL,password varchar(50) NOT NULL,day_of_birth int NOT NULL,month_of_birth int NOT NULL,year_of_birth int NOT NULL,email varchar(100) NOT NULL,phone varchar(20) NOT NULL,nickname varchar(50),street varchar(50),street2 varchar(50),city varchar(50) NOT NULL,country varchar(20) NOT NULL,state varchar(20) NOT NULL,nationality varchar(20) NOT NULL,zip int NOT NULL,isLoggedIn boolean default false,PRIMARY KEY(id));
create table customer_payment_id(id int NOT NULL auto_increment,paysafe_id varchar(200) NOT NULL,fk_customer int NOT NULL,PRIMARY KEY(id),FOREIGN KEY(fk_customer) REFERENCES users(id));
create table configs(id int NOT NULL auto_increment,config_key varchar(200) NOT NULL,value varchar(500)NOT NULL,PRIMARY KEY(id));

--Configs inserts
insert into configs values (default,'api-public-key','Base 64 of Public API Key for AUTH');
insert into configs values (default,'api-private-key','Base 64 of Public API Key for AUTH');

