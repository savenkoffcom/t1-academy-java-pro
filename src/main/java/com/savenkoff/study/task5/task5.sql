CREATE TABLE task5.users (
	id bigserial NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_un UNIQUE (username)
);

CREATE TABLE task5.products (
	id bigserial NOT NULL,
	acc_num varchar(20) NOT NULL,
	balance float4 NULL,
	type_product varchar(32) NOT NULL,
	user_id bigint NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY (id),
	CONSTRAINT products_fk FOREIGN KEY (user_id) REFERENCES task5.users(id)
);
CREATE INDEX products_user_id_idx ON task5.products (user_id);