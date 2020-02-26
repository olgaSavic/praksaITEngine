insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(100, 'dragan@gmail.com', 'Dragan', 'Borkovac', false, '$2a$10$jkNiHqkZpBqH5ZabDOLla.clW9IrNlgQVhpZWIGWB96gN5My1j7cy', 'BLOGER');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(200, 'nemanja@gmail.com', 'Nemanja', 'Vujovic', false, '$2a$10$jkNiHqkZpBqH5ZabDOLla.clW9IrNlgQVhpZWIGWB96gN5My1j7cy', 'BLOGER');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(300, 'pavle@gmail.com', 'Pavle', 'Trifkovic', false, '$2a$10$jkNiHqkZpBqH5ZabDOLla.clW9IrNlgQVhpZWIGWB96gN5My1j7cy', 'BLOGER');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(400, 'miljan@gmail.com', 'Miljan', 'Cabrilo', false, '$2a$10$jkNiHqkZpBqH5ZabDOLla.clW9IrNlgQVhpZWIGWB96gN5My1j7cy', 'BLOGER');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(500, 'srdjan@gmail.com', 'Srdjan', 'Ilic', false, '$2a$10$jkNiHqkZpBqH5ZabDOLla.clW9IrNlgQVhpZWIGWB96gN5My1j7cy', 'BLOGER');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(600, 'olga@gmail.com', 'Olga', 'Savic', false, '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'ADMIN');
insert into db_praksa.user(id, email, first_name, last_name, is_deleted, pass, role)
	values(700, 'tata@gmail.com', 'Petar', 'Savic', false, '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'ADMIN');
	

insert into db_praksa.blog(id, blog_body, blog_title, is_deleted, user_id)
	values(100, 'Body of the first blog', 'First Blog', false, 500);
insert into db_praksa.blog(id, blog_body, blog_title, is_deleted, user_id)
	values(200, 'Body of the second blog', 'Second Blog', false, 500);
insert into db_praksa.blog(id, blog_body, blog_title, is_deleted, user_id)
	values(300, 'Body of the third blog', 'Third Blog', false, 500);