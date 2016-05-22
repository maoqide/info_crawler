CREATE DATABASE info_crawler;

USE info_crawler;

CREATE TABLE item (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  title    VARCHAR(200)   NOT NULL UNIQUE,
  content  VARCHAR(20000) NOT NULL,
  url      VARCHAR(100)   NOT NULL,
  resource VARCHAR(20)    NOT NULL,
  time     VARCHAR(20)    NOT NULL
)

  #查找重复行
    SELECT
      title,
      count(*)
    FROM item
    GROUP BY title
    HAVING count(*) > 1;

#删除所有重复行
DELETE FROM item
WHERE title IN (SELECT a.title
                FROM (SELECT title
                      FROM item
                      GROUP BY title
                      HAVING count(title) > 1) a);