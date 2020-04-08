show databases;
create database jobboard;
use jobboard;

create table job(
id varchar(40),
category varchar(70),
companyname varchar(90),
jobtype varchar(60),
jobposition varchar(60),
salary int(30),
jobdescription varchar(6000),
experiencelevel varchar(60),
state varchar(30),
Primary key(id));

create table joborders (
OrderId varchar(40) DEFAULT NULL,
AddressDate date DEFAULT NULL,
companyname varchar(40) DEFAULT NULL,
username varchar(40) DEFAULT NULL,
salary int(11) DEFAULT NULL,
category varchar(70) DEFAULT NULL,
jobid varchar(20) default NULL,
jobtype varchar(30) DEFAULT NULL,
jobposition varchar(60) DEFAULT NULL,
state varchar(30) DEFAULT NULL
);


create table userprofile
(username varchar(30),
firstname varchar(30),
lastname varchar(30),
education varchar(30),
summary varchar(300),
companyname varchar(30),
experiencePeriod varchar(30),
skills varchar(300),
certifications varchar(50),
projects varchar(300));

CREATE TABLE registration (
username varchar(40) DEFAULT NULL,
password varchar(40) DEFAULT NULL,
firstname varchar(40) DEFAULT NULL,
lastname varchar(40) DEFAULT NULL,
gender varchar(40) DEFAULT NULL,
usertype varchar(40) DEFAULT NULL
);


create table follow(
follower varchar(40),
following varchar(40),
firstname varchar(40),
lastname varchar(40),
Primary key(follower,following)
);

create table posts(
postid varchar(5),
username varchar(40),
text varchar(300),
firstname varchar(40),
lastname varchar(40)
);



select * from registration;
select * from job;
select * from joborders;
select * from userprofile;
select * from follow;
select * from posts;

