DROP DATABASE IF EXISTS internetshop;

CREATE DATABASE internetshop;

USE internetshop;


CREATE TABLE users (
  id       INTEGER PRIMARY KEY  AUTO_INCREMENT,
  username VARCHAR(40),
  surname     VARCHAR(40),
  login VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(40),
  email    VARCHAR(30) ,
  logo VARCHAR(200),
  role VARCHAR(10)
);
CREATE TABLE location(
id INTEGER PRIMARY KEY ,
country VARCHAR(50)
);
CREATE TABLE material(
id INTEGER PRIMARY KEY,
material VARCHAR(50)
);
CREATE TABLE item_type(
id INTEGER PRIMARY KEY,
type VARCHAR(50)
);
CREATE TABLE color(
id INTEGER PRIMARY KEY,
color VARCHAR(50)
);

CREATE TABLE item(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(200),
price DECIMAL,
weight INTEGER,
age INTEGER,
location_idlocation INT,
material_idmaterial INT,
itemtype_iditemtype INT,
color_idcolor INT,
FOREIGN KEY (location_idlocation) REFERENCES location(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (material_idmaterial) REFERENCES material(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (itemtype_iditemtype) REFERENCES item_type(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (color_idcolor) REFERENCES color(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE orders(
id INT PRIMARY KEY AUTO_INCREMENT,
status VARCHAR(30),
details VARCHAR(200),
order_date DATE,
user_id INT,
FOREIGN KEY(user_id) REFERENCES users(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE ordered_items(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
item_id INT,
order_id INT,
quantity INT,
FOREIGN KEY(item_id) REFERENCES item(id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
FOREIGN KEY(order_id) REFERENCES orders(id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


INSERT INTO users values (1,"admin","admin","admin","111111","admin@mail.com","non","user");

insert into location values(1,"China");
insert into location values(2,"India");
insert into location values(3,"Ukraine");

insert into material values(1,"pottery");
insert into material values(2,"glass");
insert into material values(3,"metal");
insert into material values(4,"cast iron");

insert into item_type values(1,"classic");
insert into item_type values(2,"electrical");

insert into color values (1,"brown");
insert into color values (2,"silver");
insert into color values (3,"bronze");
insert into color values (4,"golden");


/*name,price,weight,age,location,material,type,color*/
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Mega",364,1780,120,1,1,1,1);
insert into item values (default,"teapot Zodiac signs",1999,6700,300,2,1,1,3);
insert into item values (default,"Yixing Zisha Handmade Bamboo",10,1150,1,1,1,1,1);
insert into item values (default,"Original Soviet Porcelain Teapot Seal",100,435,70,3,1,1,4);
insert into item values (default," Bronze Gilt Hand-Carved People Teapot",270,816,200,1,3,1,2);
insert into item values (default," Siemens",37,650,1,1,3,2,2);
insert into item values (default," LG",22,650,1,1,3,2,2);
insert into item values (default," Samsung",18,650,1,1,3,2,2);