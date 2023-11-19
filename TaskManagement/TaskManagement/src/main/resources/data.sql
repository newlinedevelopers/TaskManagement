insert into user_roles(role) values('ADMIN');
insert into user_roles(role) values('USER');

insert into user_credentials(username, password, role_id) values('admin', '$2a$12$bM.P3T8cuDymORngxL7vUO.z8WZnytwlTfGEaOyvETdcWmlXw5q06', 1);
--$2a$12$bM.P3T8cuDymORngxL7vUO.z8WZnytwlTfGEaOyvETdcWmlXw5q06 == 'admin'