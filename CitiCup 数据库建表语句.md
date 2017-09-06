# CitiCup 数据库建表语句

```sql
# 用户表
CREATE TABLE user
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  name VARCHAR(20) DEFAULT '姓名',
  psw VARCHAR(32) NOT NULL,
  #   标识符
  token VARCHAR(32) UNIQUE,
  avatar VARCHAR(100) DEFAULT 'http://104.236.132.15:8081/CitiCup/avatar/default.jpg',
  PRIMARY KEY (phone)
);

CREATE TABLE user_info
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  age INT ,
  #   性别
  gender BOOL,
  income INT ,
  PRIMARY KEY (phone),
  FOREIGN KEY (phone) REFERENCES user(phone)
);

# 用户验证码表
CREATE TABLE ver_code
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  code VARCHAR(4),
  date DATETIME,
  PRIMARY KEY (phone)
);

CREATE TABLE insu_prefer
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  PRIMARY KEY (phone),
  FOREIGN KEY (phone) REFERENCES user(phone),
  insu_type VARCHAR(20),
  theme VARCHAR(20),
  pay_method VARCHAR(20)
);

CREATE TABLE fina_prefer
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  PRIMARY KEY (phone),
  FOREIGN KEY (phone) REFERENCES user(phone),
  #   产品期限
  duration VARCHAR(20),
  pro_type VARCHAR(20),
  level INT,
  revenue VARCHAR(20),
  sale_status VARCHAR(20)
);

CREATE TABLE risk_ability
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  PRIMARY KEY (phone),
  FOREIGN KEY (phone) REFERENCES user(phone),
  option1 INT,
  option2 INT,
  option3 INT,
  option4 INT,
  option5 INT,
  option6 INT,
  option7 INT,
  option8 INT,
  option9 INT,
  option10 INT,
  option11 INT,
  option12 INT,
  option13 INT,
  option14 INT,
  option15 INT,
  option16 INT,
  option17 INT,
  option18 INT,
  option19 INT,
  option20 INT
);

CREATE TABLE insu_test
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  PRIMARY KEY (phone),
  FOREIGN KEY (phone) REFERENCES user(phone),
  option1 VARCHAR(20),
  option2 VARCHAR(20),
  option3 VARCHAR(20),
  option4 VARCHAR(20),
  option5 VARCHAR(20),
  option6 VARCHAR(20),
  option7 VARCHAR(20),
  option8 VARCHAR(20),
  option9 VARCHAR(20),
  option10 VARCHAR(20)
);

CREATE TABLE insu_pro
(
  id INT AUTO_INCREMENT,
  name VARCHAR(50),
  company VARCHAR(50),
  type VARCHAR(20),
  pay_method VARCHAR(20),
  PRIMARY KEY (id)
);

CREATE TABLE fina_pro
(
  id INT AUTO_INCREMENT,
  name VARCHAR(50),
  company VARCHAR(50),
  min_amount VARCHAR(20),
  duration VARCHAR(20),
  #   发行日期
  issuint_date VARCHAR(20),
  #   预收益
  pre_earn FLOAT,
  #   可否赎回
  redeem BOOL,
  #   是否保本
  guaranteed BOOL,
  level INT,
  PRIMARY KEY (id)
);
```

