CREATE TABLE T_CLIENT (
    id int PRIMARY KEY auto_increment,
    name varchar(500),
    cpf varchar(500),
    email varchar(200)
);

INSERT INTO T_CLIENT VALUES(1, 'Gustavo','4805414411', 'Gustavo@hotmail.com');
INSERT INTO T_CLIENT VALUES(2, 'Vinicius','4805414411', 'Vinicius@hotmail.com');
INSERT INTO T_CLIENT VALUES(3, 'Henrique','4805414411', 'Henrique@hotmail.com');
