
drop database if exists shopcart;
create database shopcart default character set utf8;

use shopcart

drop table if exists UserInfo;
drop table if exists ProductType;
drop table if exists Products;
drop table if exists Transactions;

create table UserInfo(
	umail 		varchar(20) 	not null,
	uid 		int primary 	key  not null ,
	uname 		varchar(12) 	not null,
	upassword 	varchar(12) 	not null,
	uaddress 	varchar(50),
	utele 		varchar(20),
	umobile 	varchar(13),
	uzip 		varchar(6)
);

create table ProductType(
	ptid 		int 		primary key not null auto_increment,
	ptname 		varchar(30) 	not null,
	ptnote 		varchar(30)
);

create table Products(
	pid 		int 		primary key not null auto_increment,
	pname 		varchar(20) 	not null,
	pdescription 	varchar(200) 	not null,
	pdate 		date 		not null,
	pprice 		double 		not null,
	pphoto 		blob,
	pamount 	int 		not null,
	pnotes 		varchar(20),
	pprority 	tinyint,
	pdiscount 	int 		not null,
	ptypeid 	int 		not null,
	FOREIGN KEY(ptypeid) REFERENCES ProductType(ptid)
);

create table Transactions(
	tid 		int 	primary key not null auto_increment,
	tuid 		int 	not null,
	tpid 		int 	not null,
	tdate 		date	not null,
	ttime 		time 	not null,
	tshiped 	int 	default 0,
	tshipdate 	date,
	tshiptime 	time,
	tamount 	int 	not null,
	FOREIGN KEY(tuid) REFERENCES UserInfo(pid),
	FOREIGN KEY(tpid) REFERENCES Products(uid)
);


