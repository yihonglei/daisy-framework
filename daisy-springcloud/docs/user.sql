create table user (
  user_id int auto_increment primary key,
  user_name varchar(20) null comment '姓名',
  age int(3) default 0 comment '年龄'
);