# CitiCup 数据库建表语句

```sql
# 用户表
CREATE TABLE User
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  name VARCHAR(20) DEFAULT '姓名',
  psw VARCHAR(32) NOT NULL,
  token VARCHAR(32) UNIQUE,
  PRIMARY KEY (phone)
);

# 用户验证码表
CREATE TABLE Code
(
  phone VARCHAR(11) NOT NULL UNIQUE ,
  code VARCHAR(4),
  date DATETIME,
  PRIMARY KEY (phone)
)

```

