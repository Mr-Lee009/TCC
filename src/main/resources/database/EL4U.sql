create database EL4U;
use EL4U;

CREATE TABLE test
(
    ID          INT(11)       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME        VARCHAR(1024) NOT NULL,
    LINK_MP3    VARCHAR(1024),
    CREATE_DATE datetime,
    UPDATE_DATE datetime,
    CREATE_BY   VARCHAR(50),
    UPDATE_BY   VARCHAR(50),
    ANSER_TEST  text
);

create table group_question
(
    ID       int(10) not null primary key auto_increment,
    ID_TEST  int(10),
    LINK_IMG varchar(256),
    LINK_MP3 varchar(256),
    CONTENT  text
);
alter table group_question
    add foreign key (ID_TEST) references test (ID);

alter table question
    add column ID_GROUP_QUESTION int(10);
alter table question
    add constraint pk_group_question foreign key (ID_TEST) references group_question (ID);

alter table question
    drop foreign key pk_group_question;

delete
from group_question;
create table question
(
    ID             INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_TEST        INT(11),
    LINK_MP3       VARCHAR(1024),
    LINK_IMG       VARCHAR(1024),
    QUESTION       VARCHAR(1024),
    ANSWER_A       VARCHAR(1024),
    ANSWER_B       VARCHAR(1024),
    ANSWER_C       VARCHAR(1024),
    ANSWER_D       VARCHAR(1024),
    CORRECT_ANSWER VARCHAR(1),
    QUESTION_ORDER int,
    FOREIGN KEY (ID_TEST) REFERENCES test (id)
);
create table user
(
    ID           INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    USERNAME     VARCHAR(256),
    PASSWORD     VARCHAR(256),
    LINK_IMG     VARCHAR(1024),
    PHONE_NUMBER VARCHAR(1024),
    EMAIL        VARCHAR(1024),
    RULE         VARCHAR(1024),
    STATUST      VARCHAR(1024),
    DELETED      VARCHAR(1024),
    CREATE_DATE  datetime,
    CREATE_BY    VARCHAR(50)
);
create table thongke
(
    ID             INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_USER        INT(11),
    ID_TEST        INT(11),
    CORRECT_ANSWER INT,
    WRONG_ANSWER   INT,
    CREATE_DATE    datetime,
    READING        INT,
    LISTENING      INT,
);
alter table question
    add column ID_GROUP_QUESTION INT(11);
select *
from test
select *
from group_question;
select *
from question;

DELIMITER $$
create procedure createGroupQuestionByIdTest(
    idTest int)
begin
    declare stt int default 0; -- khai bao bien
    set stt = 0;
    WHILE (stt < 45)
        DO
            INSERT INTO `el4u`.`group_question` (`ID_TEST`, `LINK_IMG`, `LINK_MP3`, `CONTENT`)
            VALUES (idTest, 'HINH ANH', 'LINK FILE NGHE', 'NOI DUNG');
            set stt = stt + 1;
        END WHILE;
    select * from group_question;
end $$
DELIMITER $$
drop procedure createQuestionByIdGroup $$
create procedure createQuestionByIdGroup(idGroup int,
                                         questionOrder int,
                                         numberQuestion int)
begin
    declare stt int default 0; -- khai bao bien
    set stt = 0;
    WHILE (stt < numberQuestion)
        DO
            INSERT INTO `el4u`.`question` (`LINK_MP3`, `LINK_IMG`, `QUESTION`, `ANSWER_A`, `ANSWER_B`, `ANSWER_C`,
                                           `ANSWER_D`, `CORRECT_ANSWER`, `QUESTION_ORDER`, `ID_GROUP_QUESTION`)
            VALUES ('LINK MP3 ', 'LINK PNG', 'QUESTION', 'A', 'B', 'C', 'D', 'A', questionOrder, idGroup);
            set questionOrder = questionOrder + 1;
            set stt = stt + 1;
        END WHILE;
end $$
DELIMITER $$
create procedure getQuestionByIdTest(
    idTest int)
begin
    select * from question where ID_GROUP_QUESTION IN (select ID from group_question where ID_TEST = idTest);
end $$

select *
from question;
call getQuestionByIdTest(1);
call createGroupQuestionByIdTest(1);
call createQuestionByIdGroup(97, 48, 3);

select *
from question;
select *
from group_question;
delete table question;

--==================================================
select *
from information_schema.columns
where table_schema = 'EL4U'
order by table_name, ordinal_position