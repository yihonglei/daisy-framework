create table order_info (
  order_id int auto_increment primary key,
  order_name varchar(20) null comment '订单名',
  status int(3) default 0 comment '订单状态'
);