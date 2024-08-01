CREATE TABLE users (
	id bigserial NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_un UNIQUE (username)
);

CREATE TABLE products (
	id bigserial NOT NULL,
	acc_num varchar(20) NOT NULL,
	balance float4 NULL,
	type_product integer NOT NULL,
	user_id bigint NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY (id),
	CONSTRAINT products_fk FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE INDEX products_user_id_idx ON products (user_id);

INSERT INTO users
        (id, username)
    VALUES
        (1, 'user_1'),
        (2, 'user_2'),
        (3, 'user_3')
;

INSERT INTO products
        (acc_num, balance, type_product, user_id)
    VALUES
        ('00001',100, 0, 1),
        ('00002',100.01, 0, 1),
        ('00003',1000000, 0, 1),
        ('00010',2, 0, 2),
        ('00011',20000, 0, 2)
;