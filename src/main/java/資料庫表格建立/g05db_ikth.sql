CREATE DATABASE IF NOT EXISTS g05;
USE g05;

DROP TABLE IF EXISTS SHOPPING_CART;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1;

--團購參與者					
					
CREATE TABLE PARTICIPANTS (
	PAR_ID                INT NOT NULL PRIMARY KEY,
    MEM_ID                INT NOT NULL,
    GB_ID                 INT NOT NULL,
    PAR_PHONE             VARCHAR(10) NOT NULL,
    PAR_NAME              VARCHAR(45) NOT NULL,
    PAR_ADDRESS           VARCHAR(45) NOT NULL,
    PAR_LONGITUDE         Decimal NOT NULL,
    PAR_LATITUDE          Decimal NOT NULL,
    IS_LEADER             TINYINT NOT NULL COMMENT '0:是,1:否',
    PAR_PURCHASE_QUANTITY INT NOT NULL,
    PAYMENT_STATUS        TINYINT NOT NULL COMMENT '0:未付款,1:已付款',
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
);

--團購案					
					
CREATE TABLE GROUP_BUYING_CASES (
	GB_ID                        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    STOR_ID                      INT NOT NULL,
    GB_PROD_ID                   INT NOT NULL,
    MEM_ID                       INT NOT NULL,
    GB_START_TIME                Decimal NOT NULL,
    GB_END_TIME                  Decimal NOT NULL,
    GB_TITLE                     VARCHAR(45) NOT NULL,
    GB_DESCRIPTION               VARCHAR(255) NOT NULL,
    GB_STATUS                    TINYINT NOT NULL COMMENT '0:待開團,1:招募中,2:即將截止,3:已開團,4:已截止, 5:已取消,6:開團失敗',
    GB_CREATE_AT                 Decimal NOT NULL,
    GB_MIN_PRODUCT_QUANTITY      INT NOT NULL,
    CANCEL_REASON                VARCHAR(65) NOT NULL,
    CUMULATIVE_PURCHASE_QUANTITY INT NOT NULL,
    
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
   --  FOREIGN KEY (STOR_ID) REFERENCES STORE(STOR_ID),
   --  FOREIGN KEY (GB_PROD_ID) REFERENCES GROUP_PRODUCT(GB_PROD_ID),

);


--團購訂單					
					
CREATE TABLE GROUP_ORDERS (
	GB_OR_ID                     INT NOT NULL PRIMARY KEY,
    GB_ID                        INT NOT NULL,
    STOR_ID                      INT NOT NULL,
    GB_PROD_ID                   INT NOT NULL,
    JOIN_TIME                    Datetime NOT NULL,
    AMOUNT                       INT NOT NULL,
    QUANTITY                     INT NOT NULL,
    PAY_METHOD                   TINYINT NOT NULL COMMENT '0:信用卡,1:現金,2:第三方',
    ORDER_STATUS                 TINYINT NOT NULL COMMENT '0:未接單,1:接單,2:完成,3:取消)',
    PAYMENT_STATUS               TINYINT NOT NULL COMMENT '0:未付款,1:已付款',
    SHIPPING_STATUS              TINYINT NOT NULL COMMENT '0:未付款,1:已付款',
    PAR_NAME                     VARCHAR(45) NOT NULL,
    PAR_ADDRESS                  VARCHAR(45) NOT NULL,
    PAR_LONGITUDE                Decimal NOT NULL,
    PAR_END_TIME                 Decimal NOT NULL,
    PAR_PHONE                    VARCHAR(10) NOT NULL,
    DELIVERY_METHOD              TINYINT NOT NULL COMMENT '0:宅配,1:自取',
    COMMENT                      VARCHAR(255) NULL,
	RATING                       TINYINT UNSIGNED NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (STOR_ID) REFERENCES STORE(STOR_ID),
   --  FOREIGN KEY (GB_PROD_ID) REFERENCES GROUP_PRODUCT(GB_PROD_ID),

);


--收藏團購清單		 			
					
CREATE TABLE GROUP_BUYING_COLLECTION_LIST (
	GB_ID                     INT NOT NULL PRIMARY KEY,
    MEM_ID                    INT NOT NULL PRIMARY KEY,
    CREATE_AT                 Datetime NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),

);


--團購檢舉單					
					
CREATE TABLE GROUP_PURCHASE_REPORT (
	REPORT_ID                    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MEM_ID                       INT NOT NULL,
    GB_ID                        INT NOT NULL,
    REPORT_REASON                VARCHAR(255) NOT NULL,
    REPORT_DETAIL                VARCHAR(1000) NULL,
    REPORT_STATUS                TINYINT NOT NULL COMMENT '0:未審核 1:審核通過 2:審核未通過',
    CREATE_AT                    Datetime NOT NULL,
    UPDATE_AT                    Datetime NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),

);


