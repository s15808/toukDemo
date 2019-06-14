INSERT INTO public.movie (id, description, premier_end, premier_start, title) VALUES (1, 'Big green man', '2020-06-04', '2019-06-05', 'Hulk');
INSERT INTO public.movie (id, description, premier_end, premier_start, title) VALUES (2, 'I`m batman', '2019-05-07', '2019-08-13', 'Batman');
INSERT INTO public.movie (id, description, premier_end, premier_start, title) VALUES (3, 'Avengers', '2019-05-13', '2019-09-24', 'Avengers');

INSERT INTO public.room (id, name, room_type) VALUES (2, 'ROOM PLATINUM', 'LARGE');
INSERT INTO public.room (id, name, room_type) VALUES (3, 'ROOM GOLD', 'MEDIUM');
INSERT INTO public.room (id, name, room_type) VALUES (1, 'ROOM SILVER', 'SMALL');

INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (1, 1, 1, now()+ INTERVAL '3 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (3, 1, 3, now()+ INTERVAL '4 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (4, 2, 1, now()+ INTERVAL '3 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (5, 2, 2, now()+ INTERVAL '5 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (8, 3, 2, now()+ INTERVAL '4 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (9, 3, 3, now()+ INTERVAL '3 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (10, 1, 1, now()+ INTERVAL '7 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (11, 1, 2, now()+ INTERVAL '5 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (13, 2, 1, now()+ INTERVAL '3 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (15, 2, 3, now()+ INTERVAL '4 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (17, 3, 2, now()+ INTERVAL '5 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (18, 3, 3, now()+ INTERVAL '6 hour');
INSERT INTO public.seans (id, movie_id, room_id, start_dttm) VALUES (19, 1, 1, now()+ INTERVAL '14 minute');