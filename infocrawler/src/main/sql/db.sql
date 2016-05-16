CREATE DATABASE info_crawler;

USE info_crawler;

CREATE TABLE item (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL UNIQUE,
  content VARCHAR(20000) NOT NULL,
  url VARCHAR(100) NOT NULL,
  resource VARCHAR(20) NOT NULL,
  time VARCHAR(20) NOT NULL
)

#查找重复行
select title,count(*) from item group by title having count(*) > 1;

#删除所有重复行
delete from item where title in (select a.title from (select title from item group by title having count(title) > 1) a);