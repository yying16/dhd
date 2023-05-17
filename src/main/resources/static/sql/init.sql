drop database if exists dhd;
create database dhd;
use dhd;
drop table if exists t_user;
create table t_user
(
    account   varchar(30)   not null comment '�˺�',
    password  varchar(30)   not null comment '����',
    username  varchar(30)   not null comment '�û���',
    status    int default 0 not null comment '���', /* 1��ʾ����Ա��0��ʾ��ͨ�û�*/
    telephone varchar(30)   not null comment '�ֻ�����',
    email     varchar(50)   not null comment '����',
    primary key (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
create index index_account on t_user (account ASC);
insert into t_user
values ('robot', 'robot123', 'ROBOT', 0, '15852632691', 'robot@163.com'),
       ('zhangsan', 'zs123456', '����', 0, '15915712354', 'zhangsan@163.com'),
       ('lisi', 'ls123456', '����', 0, '13430241235', 'lisi@qq.com'),
       ('wangwu', 'ww123456', '����', 0, '13645236589', 'wangwu@163.com'),
       ('chenliu', 'cl123456', '����', 0, '13316397963', 'chenliu@163.com'),
       ('xuqi', 'xq123456', '����', 0, '13352679568', 'xuqi@163.com'),
       ('maba', 'mb123456', '���', 0, '13654879632', 'maba@163.com'),
       ('zhengjiu', 'zj123456', '֣��', 0, '13912546983', 'zhengjiu@163.com'),
       ('huangshi', 'hs123456', '��ʮ', 0, '15815632498', 'huangshi@163.com');

drop table if exists t_game;
create table t_game
(
    game_id int auto_increment,
    offensive   varchar(30)   not null comment '�����˺�',
    defensive  varchar(30)   not null comment '�����˺�',
    status boolean default true comment '�����Ƿ����ڽ�����',
    result boolean comment '�������',
    score nvarchar(30) comment '����ȷ�',
    game_time varchar(30) comment '����ʱ��',
    primary key (game_id),
    foreign key (offensive) references t_user (account),
    foreign key (defensive) references t_user (account)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
create index index_game_id on t_game (game_id ASC);
