CREATE DATABASE IF NOT EXISTS g05;
USE g05;

DROP TABLE IF EXISTS SHOPPING_CART;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1;

-- 以下開始就是各自負責的表格

-- 購物車商品
CREATE TABLE SHOPPING_CART (
    SHOP_ID         INT NOT NULL AUTO_INCREMENT,      -- 購物車商品編號，主鍵，自動遞增
    MEM_ID          INT NOT NULL,                     -- 會員編號，外鍵
    PROD_ID         INT NOT NULL,                     -- 商品編號，外鍵
    PRIMARY KEY (SHOP_ID)
    -- FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
    -- FOREIGN KEY (PROD_ID) REFERENCES PRODUCT(PROD_ID)
);

-- 會員持有優惠券
CREATE TABLE MEMBER_COUPON (
    MEM_COU_ID       INT NOT NULL AUTO_INCREMENT,       -- 會員優惠券編號，主鍵，自動遞增
    COU_ID           INT NOT NULL,                      -- 優惠券編號，外鍵
    MEM_ID           INT NOT NULL,                      -- 會員編號，外鍵
    USE_STATUS       TINYINT NOT NULL,                  -- 使用狀態 (0:未使用, 1:已使用)
    PRIMARY KEY (MEM_COU_ID)
    -- FOREIGN KEY (COU_ID) REFERENCES COUPON(COU_ID),
    -- FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID)
);

-- 訂單
CREATE TABLE ORDERS (
    ORD_ID           INT NOT NULL AUTO_INCREMENT,         -- 訂單編號，主鍵，自動遞增
    MEM_ID           INT NOT NULL,                        -- 會員編號，外鍵
    STOR_ID          INT NOT NULL,                        -- 店家編號，外鍵
    ORD_DATE         DATETIME NOT NULL,                   -- 訂單成立時間
    ORD_SUM          INT NOT NULL,                        -- 訂單總金額
    PAYMENT_STATUS   TINYINT NOT NULL,                    -- 付款狀態 (0:未付款, 1:已付款, 2:退款中)
    PAY_METHOD       TINYINT NOT NULL,                    -- 付款方式 (0:信用卡, 1:現金, 2:第三方)
    DELIVER          TINYINT NOT NULL,                    -- 取餐方式 (0:外送, 1:自取)
    ORDER_STATUS     TINYINT NOT NULL,                    -- 訂單狀態 (0:未接單, 1:接單, 2:完成, 3:取消)
    ACT_ID           INT,                                 -- 活動編號，外鍵，可為NULL
    COU_ID           INT,                                 -- 優惠券編號，外鍵，可為NULL
    CANCEL_REASON    VARCHAR(255),                        -- 取消原因，可為NULL
    COMMENT          VARCHAR(255),                        -- 評價，可為NULL
    RATING           TINYINT UNSIGNED,                    -- 星等，可為NULL
    PROMO_DISCOUNT   INT,                                 -- 活動優惠金額，可為NULL
    COUPON_DISCOUNT  INT,                                 -- 優惠券優惠金額，可為NULL
    ACTUAL_PAYMENT   INT,                                 -- 實付金額，可為NULL
    ORD_ADDR         VARCHAR(255),                        -- 外送地址，可為NULL
    PRIMARY KEY (ORD_ID)
    -- FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID),
    -- FOREIGN KEY (STOR_ID) REFERENCES STORE(STOR_ID),
    -- FOREIGN KEY (ACT_ID) REFERENCES ACT(ACT_ID),
    -- FOREIGN KEY (COU_ID) REFERENCES COUPON(COU_ID)
);

-- 訂單明細
CREATE TABLE ORDER_DETAILS (
    ORD_DET_ID      INT NOT NULL AUTO_INCREMENT,         -- 訂單明細編號，主鍵，自動遞增
    ORD_ID          INT NOT NULL,                        -- 訂單編號，外鍵
    PROD_ID         INT NOT NULL,                        -- 商品編號，外鍵
    PROD_QTY        INT NOT NULL,                        -- 商品數量
    PROD_PRICE      INT NOT NULL,                        -- 商品單價
    ORD_DESC        VARCHAR(255),                        -- 訂單備註，可為NULL
    PRIMARY KEY (ORD_DET_ID)
    -- FOREIGN KEY (ORD_ID) REFERENCES ORDERS(ORD_ID),
    -- FOREIGN KEY (PROD_ID) REFERENCES PRODUCT(PROD_ID)
);
