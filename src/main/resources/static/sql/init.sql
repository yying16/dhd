drop database if exists dhd;
create database dhd;
use dhd;
drop table if exists t_user;
create table t_user
(
    account   varchar(30)   not null comment '账号',
    password  varchar(30)   not null comment '密码',
    username  varchar(30)   not null comment '用户名',
    status    int default 0 not null comment '身份', /* 1表示管理员，0表示普通用户*/
    telephone varchar(30)   not null comment '手机号码',
    email     varchar(50)   not null comment '邮箱',
    primary key (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
create index index_account on t_user (account ASC);
insert into t_user
values ('robot', 'robot123', 'ROBOT', 0, '15852632691', 'robot@163.com'),
       ('zhangsan', 'zs123456', '张三', 0, '15915712354', 'zhangsan@163.com'),
       ('lisi', 'ls123456', '李四', 0, '13430241235', 'lisi@qq.com'),
       ('wangwu', 'ww123456', '王五', 0, '13645236589', 'wangwu@163.com'),
       ('chenliu', 'cl123456', '陈六', 0, '13316397963', 'chenliu@163.com'),
       ('xuqi', 'xq123456', '许七', 0, '13352679568', 'xuqi@163.com'),
       ('maba', 'mb123456', '马八', 0, '13654879632', 'maba@163.com'),
       ('zhengjiu', 'zj123456', '郑九', 0, '13912546983', 'zhengjiu@163.com'),
       ('huangshi', 'hs123456', '黄十', 0, '15815632498', 'huangshi@163.com');

drop table if exists t_game;
create table t_game
(
    game_id int auto_increment,
    offensive   varchar(30)   not null comment '先手账号',
    defensive  varchar(30)   not null comment '后手账号',
    status boolean default true comment '比赛是否正在进行中',
    result boolean comment '比赛结果',
    score nvarchar(30) comment '赛后比分',
    game_time varchar(30) comment '比赛时间',
    primary key (game_id),
    foreign key (offensive) references t_user (account),
    foreign key (defensive) references t_user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
create index index_game_id on t_game (game_id ASC);
