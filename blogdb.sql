drop database if exists BlogDB;

create database BlogDB;

use BlogDB;

create table Post(
	PostID int primary key AUTO_INCREMENT,
	Content varchar(5000) not null,
	Approved boolean,
	PostTime datetime not null
);

create table PostTag(
	PostID int,
	Tag varchar(30),
	primary key (PostID, Tag),
	foreign key fk_PostTag_PostID (PostID)
		references Post (PostID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
