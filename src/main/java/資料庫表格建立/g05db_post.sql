create database if not exists g05;

use g05;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1; 

drop table if exists discuss_post;

-- 討論區貼文
create table DISCUSS_POST (
POST_ID INT NOT NULL AUTO_INCREMENT,   -- 貼文編號，主鍵，自動遞增
MEM_ID INT NOT NULL,                   -- 會員編號，外鍵
POST_CATE_ID INT NOT NULL,             -- 貼文類別編號，外鍵
POST_DATE datetime NOT NULL,           -- 貼文時間
primary key (POST_ID),
-- foreign key (MEM_ID) REFERENCES POST_CATEGORY(POST_CATE_ID),
-- foreign key (POST_DATA) REFERENCES
foreign key (POST_CATE_ID) REFERENCES MEMBER(MEM_ID)
);