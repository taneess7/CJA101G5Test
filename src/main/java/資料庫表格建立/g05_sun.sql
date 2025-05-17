CREATE DATABASE IF NOT EXISTS g05;
use g05;

DROP TABLE IF EXISTS member1;
DROP TABLE IF EXISTS direct_message;

set auto_increment_offset=10;

set auto_increment_increment=10; 

CREATE TABLE member1 (
	-- member table 
	MEMID	INT auto_increment NOT NULL,
	MEMEMAIL	  VARCHAR(45) NOT NULL,
	MEMACCOUNT VARCHAR(45) NOT NULL,
    MEMPASSWORD varchar(45) NOT NULL,
    MEMNICKNAME varchar(45) NOT NULL,
    MEMNAME varchar(45) NOT NULL,
    MEMPHONE varchar(10) NOT NULL,
    MEMGENDER tinyint NOT NULL,
    MEMCITY varchar(45) NOT NULL,
    MEMCITYAREA varchar(45) NOT NULL,
    MEMADDRESS varchar(45) NOT NULL,
    MEMCODE varchar(45) NOT NULL,
    MEMAVATAR blob,
    MEMTIME datetime NOT NULL,
    MEMSTATUS tinyint NOT NULL,
    MEMNOSPEAK tinyint NOT NULL,
    MEMNOPOST tinyint NOT NULL,
    MEMNOGROUP tinyint NOT NULL,
    MEMNOJOINGROUP tinyint NOT NULL,
    TOTALSTARNUM INT NOT NULL,
    TOTALREVIEWS INT NOT NULL,
	CONSTRAINT member1_MEMID_PK PRIMARY KEY (MEMID),  -- 會員編號為PK 
    constraint member1_MEMACCOUNT_UK UNIQUE (MEMACCOUNT)    -- 會員帳號為UK
) AUTO_INCREMENT = 10;

INSERT INTO member1 (MEMEMAIL,MEMACCOUNT,MEMPASSWORD,MEMNICKNAME,MEMNAME,MEMPHONE,MEMGENDER,MEMCITY,MEMCITYAREA,MEMADDRESS,MEMCODE,MEMAVATAR,MEMTIME,MEMSTATUS,MEMNOSPEAK,MEMNOPOST,MEMNOGROUP,MEMNOJOINGROUP,TOTALSTARNUM,TOTALREVIEWS)VALUES ('sds12121@gmail.com','aaa111','sss111','小明','明明明','0987654321','0','taipei','taiwan','台北市文山區','sasasa','','2013-11-11','0','0','1','1','1','1','1');
INSERT INTO member1 (MEMEMAIL,MEMACCOUNT,MEMPASSWORD,MEMNICKNAME,MEMNAME,MEMPHONE,MEMGENDER,MEMCITY,MEMCITYAREA,MEMADDRESS,MEMCODE,MEMAVATAR,MEMTIME,MEMSTATUS,MEMNOSPEAK,MEMNOPOST,MEMNOGROUP,MEMNOJOINGROUP,TOTALSTARNUM,TOTALREVIEWS) VALUES ('qwqwdq@gmail.com','bbb222','ddd444','小夫','夫碩順','0912345678','0','taipei','taiwan','台北市松山區','sasasa','','2015-12-16','0','0','1','1','1','1','1');



set auto_increment_offset=1;
set auto_increment_increment=1; 
create table direct_message (
DMID INT auto_increment NOT NULL,
MEMID INT NOT NULL,
SMGRID INT,
MESSCONTENT text NOT NULL,  
MESSTIME datetime NOT NULL,
MESSDIRECTION tinyint NOT NULL, 
constraint direct_message_DMID_PK primary key (DMID)
-- constraint direct_message_MEMID_FK foreign key (MEMID) references member1 (MEMID)   會員編號為FK，對應到會員表格
-- constraint direct_message_SMGRID_FK foreign key (SMGRID) references servermanager(SMGRID) 管理員編號為FK，對應到管理員表格

) auto_increment = 1;
INSERT INTO direct_message (MEMID,SMGRID,MESSCONTENT,MESSTIME,MESSDIRECTION) VALUES ('10','11','ewewew','2023-12-12','1');
INSERT INTO direct_message (MEMID,SMGRID,MESSCONTENT,MESSTIME,MESSDIRECTION) values ('20','12','ddddd','2024-01-22','0');

-- ALTER table direct_message drop foreign key direct_message_MEMID_FK; 移除表格的FK
