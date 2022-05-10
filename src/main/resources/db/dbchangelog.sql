
CREATE TABLE client(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    lastname VARCHAR(100),
    age INTEGER
);

CREATE TABLE address(
    id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(200),
    client_id NUMBER,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

insert into client( name, lastname, age) values ('jose', 'angel', 32);
insert into client( name, lastname, age) values ('maria', 'perez', 18);

insert into address( location, client_id) values ( 'piantini , ensanche envaristo morales, # 23', 1);
insert into address( location, client_id) values ( 'guaricano , villa mella, #5', 1);
insert into address( location, client_id) values ( 'villa agricola , la 20, #30', 1);
insert into address( location, client_id) values ( 'bella vista , DaemonMall, # 23', 2);
insert into address( location, client_id) values ( 'lincold , frente a agora mall, # 23', 2);
commit;
