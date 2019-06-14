create table if not exists room
(
	id integer not null
		constraint room_pkey
			primary key,
	name varchar(25) not null,
	room_type varchar(6) not null
);

alter table room owner to postgres;

create table if not exists users
(
	id integer not null
		constraint users_pkey
			primary key,
	name varchar(20) not null,
	surname varchar(20) not null
);

alter table users owner to postgres;

create table if not exists movie
(
	id integer not null
		constraint movie_pkey
			primary key,
	description varchar(1000),
	premier_end date not null,
	premier_start date not null,
	title varchar(100) not null
);

alter table movie owner to postgres;

create table if not exists seans
(
	id integer not null
		constraint seans_pkey
			primary key,
	movie_id integer not null
		constraint fkf36tepm3h4to1b4euiei2wp8s
			references movie,
	room_id integer not null
		constraint fkerqok4sdvo3w0tj0661t8em3r
			references room,
	start_dttm timestamp not null
);

alter table seans owner to postgres;

create table if not exists reservation
(
	id varchar(36) not null,
	place integer not null,
	row_numer integer not null,
	seans_id integer not null
		constraint fkqkuh3k672ch6fuglkw99xtcie
			references seans,
	ticket varchar(7) not null,
	user_id integer not null
		constraint fkrea93581tgkq61mdl13hehami
			references users,
	status varchar(8) not null,
	reservation_dttm timestamp default now() not null,
	voucher varchar(10),
	constraint reservation_pkey
		primary key (id, place, row_numer)
);

alter table reservation owner to postgres;

create unique index reservation_voucher_uindex
	on reservation (voucher);