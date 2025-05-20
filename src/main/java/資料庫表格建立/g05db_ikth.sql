CREATE DATABASE IF NOT EXISTS g05;
USE g05;

DROP TABLE IF EXISTS PARTICIPANTS;

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
    PAR_LONGITUDE         DECIMAL(10,6) NOT NULL,
    PAR_LATITUDE          DECIMAL(10,6) NOT NULL,
    IS_LEADER             TINYINT NOT NULL COMMENT '0:是,1:否',
    PAR_PURCHASE_QUANTITY INT NOT NULL,
    PAYMENT_STATUS        TINYINT NOT NULL COMMENT '0:未付款,1:已付款',
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
);

INSERT INTO PARTICIPANTS 
(PAR_ID, MEM_ID, GB_ID, PAR_PHONE, PAR_NAME, PAR_ADDRESS,PAR_LONGITUDE, PAR_LATITUDE, IS_LEADER, PAR_PURCHASE_QUANTITY, PAYMENT_STATUS)
VALUES
(1, 1, 1, '0912345678', '林小明', '台北市信義區松壽路12號', 121.5645, 25.0330, 1, 3, 1),
(2, 2, 1, '0923456789', '陳美美', '新北市板橋區中山路一段3號', 121.4578, 25.0123, 1, 2, 0),
(3, 3, 2, '0934567890', '張大偉', '台中市西屯區台灣大道三段456號', 120.6417, 24.1795, 1, 5, 1),
(4, 4, 2, '0960123456', '李小花', '高雄市左營區自由三路789號', 120.3064, 22.6783, 1, 1, 0),
(5, 5, 3, '0977123456', '王志強', '台南市中西區民權路二段101號', 120.2045, 23.0001, 1, 4, 1);



DROP TABLE IF EXISTS GROUP_BUYING_CASES;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
--set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
--set auto_increment_increment=1;

--團購案										
CREATE TABLE GROUP_BUYING_CASES (
	GB_ID                        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    STOR_ID                      INT NOT NULL,
    GB_PROD_ID                   INT NOT NULL,
    MEM_ID                       INT NOT NULL,
    GB_START_TIME                DATETIME NOT NULL,
    GB_END_TIME                  DATETIME NOT NULL,
    GB_TITLE                     VARCHAR(45) NOT NULL,
    GB_DESCRIPTION               VARCHAR(255) NOT NULL,
    GB_STATUS                    TINYINT NOT NULL COMMENT '0:待開團,1:招募中,2:即將截止,3:已開團,4:已截止, 5:已取消,6:開團失敗',
    GB_CREATE_AT                 DATETIME NOT NULL,
    GB_MIN_PRODUCT_QUANTITY      INT NOT NULL,
    CANCEL_REASON                VARCHAR(65) NOT NULL,
    CUMULATIVE_PURCHASE_QUANTITY INT NOT NULL,
    
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
   --  FOREIGN KEY (STOR_ID) REFERENCES STORE(STOR_ID),
   --  FOREIGN KEY (GB_PROD_ID) REFERENCES GROUP_PRODUCT(GB_PROD_ID),

);

INSERT INTO GROUP_BUYING_CASES 
(STOR_ID, GB_PROD_ID, MEM_ID, GB_START_TIME, GB_END_TIME,GB_TITLE, GB_DESCRIPTION, GB_STATUS, GB_CREATE_AT,GB_MIN_PRODUCT_QUANTITY, CANCEL_REASON, CUMULATIVE_PURCHASE_QUANTITY) 
VALUES
(1, 1, 1, '2025-05-01 10:00:00', '2025-05-07 23:59:59','爆汁烤雞大團購', '人氣烤雞限時搶購，週末聚餐首選！', 1, '2025-04-25 09:00:00', 20, '', 12),
(1, 1, 1, '2025-05-03 08:00:00', '2025-05-10 23:59:59','韓式炸雞酥酥來了', '香辣入味，配啤酒絕配', 0, '2025-04-28 13:45:00', 15, '', 0),
(1, 1, 1, '2025-04-15 09:00:00', '2025-04-22 23:59:59', '深夜拉麵暖心團', '每日式濃湯拉麵包裝方便，隨時來一碗！', 3, '2025-04-10 11:20:00', 10, '', 18),
(1, 1, 1, '2025-05-05 12:00:00', '2025-05-12 23:59:59', '團購甜點小宇宙', '手工布丁、奶酪甜而不膩超療癒', 2, '2025-04-29 15:00:00', 25, '', 8),
(1, 1, 1, '2025-04-01 10:00:00', '2025-04-07 23:59:59', '冷凍鍋貼百元吃飽', '外酥內餡多，一包10顆全家搶著吃！', 5, '2025-03-25 08:30:00', 30, '因供應商取消合作，無法繼續開團', 0);



DROP TABLE IF EXISTS GROUP_ORDERS;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
--set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
--set auto_increment_increment=1;

--團購訂單										
CREATE TABLE GROUP_ORDERS (
	GB_OR_ID                     INT NOT NULL PRIMARY KEY,
    GB_ID                        INT NOT NULL,
    STOR_ID                      INT NOT NULL,
    GB_PROD_ID                   INT NOT NULL,
    JOIN_TIME                    DATETIME NOT NULL,
    AMOUNT                       INT NOT NULL,
    QUANTITY                     INT NOT NULL,
    PAY_METHOD                   TINYINT NOT NULL COMMENT '0:信用卡,1:現金,2:第三方',
    ORDER_STATUS                 TINYINT NOT NULL COMMENT '0:未接單,1:接單,2:完成,3:取消)',
    PAYMENT_STATUS               TINYINT NOT NULL COMMENT '0:未付款,1:已付款',
    SHIPPING_STATUS              TINYINT NOT NULL COMMENT '0:未出貨,1:已出貨',
    PAR_NAME                     VARCHAR(45) NOT NULL,
    PAR_ADDRESS                  VARCHAR(45) NOT NULL,
    PAR_LONGITUDE                DECIMAL(10,6) NOT NULL,
    PAR_LATITUDE                 DECIMAL(10,6) NOT NULL,
    PAR_PHONE                    VARCHAR(10) NOT NULL,
    DELIVERY_METHOD              TINYINT NOT NULL COMMENT '0:宅配,1:自取',
    COMMENT                      VARCHAR(255) NULL,
	RATING                       TINYINT UNSIGNED NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (STOR_ID) REFERENCES STORE(STOR_ID),
   --  FOREIGN KEY (GB_PROD_ID) REFERENCES GROUP_PRODUCT(GB_PROD_ID),

);

INSERT INTO GROUP_ORDERS 
(GB_OR_ID, GB_ID, STOR_ID, GB_PROD_ID, JOIN_TIME, AMOUNT, QUANTITY, PAY_METHOD,   ORDER_STATUS, PAYMENT_STATUS, SHIPPING_STATUS, PAR_NAME, PAR_ADDRESS,   PAR_LONGITUDE, PAR_LATITUDE, PAR_PHONE, DELIVERY_METHOD, COMMENT, RATING) 
VALUES
(1, 1, 1, 1, '2025-05-15 10:23:00', 300, 2, 0, 1, 1, 0,  '林小芳', '台北市大安區和平東路一段10號', 121.543, 25.026, '0911222333', 0, '炸雞好吃不膩', 5),
(2, 2, 2, 2, '2025-05-15 11:00:00', 180, 1, 1, 0, 0, 0,  '陳大雄', '新北市板橋區文化路二段55號', 121.468, 25.013, '0922333444', 1, NULL, 4),
(3, 3, 3, 3, '2025-05-15 12:15:00', 500, 5, 2, 2, 1, 1,  '吳艾琳', '桃園市中壢區中央西路100號', 121.221, 24.956, '0933444555', 0, '配送迅速，推薦', 5),
(4, 1, 1, 1, '2025-05-15 13:45:00', 150, 1, 0, 0, 0, 0,  '張宥蓉', '台中市北區學士路20號', 120.683, 24.154, '0944555666', 1, NULL, 3),
(5, 4, 4, 4, '2025-05-15 15:00:00', 360, 3, 1, 1, 1, 1,  '黃冠宇', '高雄市前鎮區瑞隆路88號', 120.312, 22.609, '0955666777', 0, '口味不錯但等比較久', 4);



DROP TABLE IF EXISTS GROUP_BUYING_COLLECTION_LIST;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
--set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
--set auto_increment_increment=1;

--收藏團購清單		 								
CREATE TABLE GROUP_BUYING_COLLECTION_LIST (
	GB_ID                     INT NOT NULL PRIMARY KEY,
    MEM_ID                    INT NOT NULL PRIMARY KEY,
    CREATE_AT                 DATETIME NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),

);

INSERT INTO GROUP_BUYING_COLLECTION_LIST 
(GB_ID, MEM_ID, CREATE_AT) 
VALUES
(1, 1, '2025-05-15 09:00:00'),
(2, 2, '2025-05-15 10:12:00'),
(3, 3, '2025-05-15 11:25:00'),
(4, 4, '2025-05-15 12:40:00'),
(5, 5, '2025-05-15 13:50:00');



DROP TABLE IF EXISTS GROUP_PURCHASE_REPORT;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
--set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
--set auto_increment_increment=1;

--團購檢舉單								
CREATE TABLE GROUP_PURCHASE_REPORT (
	REPORT_ID                    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MEM_ID                       INT NOT NULL,
    GB_ID                        INT NOT NULL,
    REPORT_REASON                VARCHAR(255) NOT NULL,
    REPORT_DETAIL                VARCHAR(1000) NULL,
    REPORT_STATUS                TINYINT NOT NULL COMMENT '0:未審核 1:審核通過 2:審核未通過',
    CREATE_AT                    DATETIME NOT NULL,
    UPDATE_AT                    DATETIME NOT NULL,
    
    
   --  FOREIGN KEY (GB_ID) REFERENCES GROUP_BUYING_CASES(GB_ID),
   --  FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),

);


INSERT INTO GROUP_PURCHASE_REPORT (
  MEM_ID, GB_ID, REPORT_REASON, REPORT_DETAIL, REPORT_STATUS, CREATE_AT, UPDATE_AT
) VALUES
(1, 1, '商品與描述不符', '圖片顯示為炸雞桶，但實際收到的是小份炸雞，與團購頁描述差距過大。', 0, '2025-05-15 10:00:00', '2025-05-15 10:00:00'),
(2, 2, '延遲出貨', '原本說三天出貨，結果等了兩週還沒收到。', 0, '2025-05-15 10:20:00', '2025-05-15 10:20:00'),
(3, 3, '商品變質', '收到的壽司有異味，看起來不新鮮，擔心食安問題。', 0, '2025-05-15 11:00:00', '2025-05-15 11:00:00'),
(4, 4, '賣家失聯', '付款後聯繫不到店家，已過開團時間仍無回應。', 0, '2025-05-15 12:30:00', '2025-05-15 12:30:00'),
(5, 5, '價格與實際不符', '團購寫的是優惠價，結帳卻顯示原價，懷疑有誤導行為。', 0, '2025-05-15 13:10:00', '2025-05-15 13:10:00');
