
CREATE TABLE client(
    id NUMBER PRIMARY KEY,
    name VARCHAR(100),
    lastname VARCHAR(100),
    age INTEGER
);

CREATE TABLE address(
    id NUMBER PRIMARY KEY,
    street_location VARCHAR(200),
    client_id NUMBER,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

insert into client(id, name, lastname, age) values (1,'jose', 'angel', 32);
insert into client(id, name, lastname, age) values (2,'maria', 'perez', 18);

insert into address(id, street_location, client_id) values (1, 'piantini , ensanche envaristo morales, # 23', 1);
insert into address(id, street_location, client_id) values (2, 'guaricano , villa mella, #5', 1);
insert into address(id, street_location, client_id) values (3, 'villa agricola , la 20, #30', 1);
insert into address(id, street_location, client_id) values (4, 'bella vista , DaemonMall, # 23', 2);
insert into address(id, street_location, client_id) values (5, 'lincold , frente a agora mall, # 23', 2);
commit;