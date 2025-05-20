CREATE DATABASE IF NOT EXISTS g05;
use g05;

DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS DIRECT_MESSAGE;

set auto_increment_offset=1;

set auto_increment_increment=1; 

CREATE TABLE MEMBER (
	-- member table 
	MEM_ID	INT auto_increment NOT NULL,
	MEM_EMAIL	  VARCHAR(45) NOT NULL,
	MEM_ACCOUNT VARCHAR(45) NOT NULL,
    MEM_PASSWORD varchar(45) NOT NULL,
    MEM_NICKNAME varchar(45) NOT NULL,
    MEM_NAME varchar(45) NOT NULL,
    MEM_PHONE varchar(10) NOT NULL,
    MEM_GENDER tinyint NOT NULL comment '0:男,1:女,2:不透露',
    MEM_CITY varchar(45) NOT NULL,
    MEM_CITYAREA varchar(45) NOT NULL,
    MEM_ADDRESS varchar(45) NOT NULL,
    MEM_CODE varchar(45) NOT NULL,
    MEM_AVATAR longblob,
    MEM_TIME datetime NOT NULL,
    MEM_STATUS tinyint NOT NULL default 1 comment '0:停權,1:正常使用',
    MEM_NO_SPEAK tinyint NOT NULL default 1 comment '0:禁言,1:解除禁言',
    MEM_NO_POST tinyint NOT NULL default 1 comment '0:禁止發文,1:解除禁止發文',
    MEM_NO_GROUP tinyint NOT NULL default 1 comment '0:禁止開團,1:解除禁止開團',
    MEM_NO_JOINGROUP tinyint NOT NULL default 1 comment '0:禁止跟團,1:解除禁止跟團',
    TOTAL_STAR_NUM INT NOT NULL,
    TOTAL_REVIEWS INT NOT NULL,
	CONSTRAINT MEMBER_MEM_ID_PK PRIMARY KEY (MEM_ID),  -- 會員編號為PK 
    constraint MEMBER_MEM_ACCOUNT_UK UNIQUE (MEM_ACCOUNT)    -- 會員帳號為UK
) AUTO_INCREMENT = 1;

INSERT INTO MEMBER (MEM_EMAIL,MEM_ACCOUNT,MEM_PASSWORD,MEM_NICKNAME,MEM_NAME,MEM_PHONE,MEM_GENDER,MEM_CITY,MEM_CITYAREA,MEM_ADDRESS,MEM_CODE,MEM_AVATAR,MEM_TIME,MEM_STATUS,MEM_NO_SPEAK,MEM_NO_POST,MEM_NO_GROUP,MEM_NO_JOINGROUP,TOTAL_STAR_NUM,TOTAL_REVIEWS)VALUES ('sds12121@gmail.com','aaa111','sss111','小明','明明明','0987654321','0','taipei','taiwan','台北市文山區','sasasa','','2013-11-11','0','0','1','1','1','1','1');
INSERT INTO MEMBER (MEM_EMAIL,MEM_ACCOUNT,MEM_PASSWORD,MEM_NICKNAME,MEM_NAME,MEM_PHONE,MEM_GENDER,MEM_CITY,MEM_CITYAREA,MEM_ADDRESS,MEM_CODE,MEM_AVATAR,MEM_TIME,MEM_STATUS,MEM_NO_SPEAK,MEM_NO_POST,MEM_NO_GROUP,MEM_NO_JOINGROUP,TOTAL_STAR_NUM,TOTAL_REVIEWS) VALUES ('qwqwdq@gmail.com','bbb222','ddd444','小夫','夫碩順','0912345678','0','taipei','taiwan','台北市松山區','sasasa','','2015-12-16','0','0','1','1','1','1','1');



set auto_increment_offset=1;
set auto_increment_increment=1; 
create table DIRECT_MESSAGE (
DM_ID INT auto_increment NOT NULL,
MEM_ID INT NOT NULL,
SMGR_ID INT,
MESS_CONTENT longtext NOT NULL,  
MESS_TIME datetime NOT NULL,
MESS_DIRECTION tinyint NOT NULL, 
constraint DIRECT_MESSAGE_DMID_PK primary key (DM_ID)
-- constraint DIRECT_MESSAGE_MEMID_FK foreign key (MEM_ID) references MEMBER (MEM_ID)   會員編號為FK，對應到會員表格
-- constraint DIRECT_MESSAGE_SMGRID_FK foreign key (SMGR_ID) references SERVERMANAGER(SMGR_ID) 管理員編號為FK，對應到管理員表格

) auto_increment = 1;
INSERT INTO DIRECT_MESSAGE (MEM_ID,SMGR_ID,MESS_CONTENT,MESS_TIME,MESS_DIRECTION) VALUES ('10','11','ewewew','2023-12-12','1');
INSERT INTO DIRECT_MESSAGE (MEM_ID,SMGR_ID,MESS_CONTENT,MESS_TIME,MESS_DIRECTION) values ('20','12','ddddd','2024-01-22','0');

-- ALTER table direct_message drop foreign key direct_message_MEMID_FK; 移除表格的FK
