CREATE TABLE news (id int NOT NULL AUTO_INCREMENT PRIMARY KEY , title VARCHAR(255), link VARCHAR(255), time  VARCHAR(255),type VARCHAR(255),bigCategory VARCHAR(255),subCategory  VARCHAR(255),content longtext )ENGINE=InnoDB DEFAULT CHARACTER SET=gb2312;

create table news_meta as( select DISTINCT(subCategory),bigCategory,type from news order by subCategory);