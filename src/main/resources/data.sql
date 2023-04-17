insert into customer
	(customer_code,customer_name,customer_pass,
	customer_birth, customer_job,  customer_mail,  customer_tel,
	customer_post,  customer_add, role)
VALUES(customer_seq.NEXTVAL,
'장보고',
'$2y$10$8Ova4FvnUX3IbcuHul2Tz.8u4U.hT0Ph2WlANHb54gQlciiDLii.W',
'1965-09-28',
'수산업',
'fish@daum.net',
'010-1111-2222',
'12345',
'서울시 구로구 구로3동 구로맨션 101호','ROLE_USER'
);

insert into customer
	(customer_code,customer_name,customer_pass,
	customer_birth, customer_job,  customer_mail,  customer_tel,
	customer_post,  customer_add, role)
VALUES(customer_seq.NEXTVAL,
'이순신',
'$2y$10$8Ova4FvnUX3IbcuHul2Tz.8u4U.hT0Ph2WlANHb54gQlciiDLii.W',
'1970-01-30',
'군인',
'egija@naver.com',
'010-1234-5678',
'12345',
'인천시 부평구 장제로 153번길','ROLE_USER'
);

insert into customer
	(customer_code,customer_name,customer_pass,
	customer_birth, customer_job,  customer_mail,  customer_tel,
	customer_post,  customer_add, role)
VALUES(customer_seq.NEXTVAL,
'펭수',
'$2y$10$8Ova4FvnUX3IbcuHul2Tz.8u4U.hT0Ph2WlANHb54gQlciiDLii.W',
'2021-08-08',
'ebs연습생',
'peng@ebs.com',
'010-8888-8888',
'12345',
'경기도 고양시 일산동 장항동 1775','ROLE_USER'
);

insert into customer
	(customer_code,customer_name,customer_pass,
	customer_birth, customer_job,  customer_mail,  customer_tel,
	customer_post,  customer_add, role)
VALUES(customer_seq.NEXTVAL,
'ADMIN',
'$2y$10$8Ova4FvnUX3IbcuHul2Tz.8u4U.hT0Ph2WlANHb54gQlciiDLii.W',
'2021-08-08',
'관리자',
'admin@naver.com',
'010-5555-8888',
'12345',
'경기도 고양시 일산동 장항동 1775','ROLE_ADMIN'
);





INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '아바타: 물의 길', 10000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '어벤져스 : 엔드게임', 5000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '토르 : 라그나로크', 1000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '닥터 스트레인지: 대혼돈의 멀티버스', 7000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '어벤져스', 7000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '토르: 러브 앤 썬더', 5000);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '맨 오브 스틸', 500);
INSERT INTO MOVIE(MOVIE_ID, TITLE, PRICE) VALUES (MOVIE_SEQ.NEXTVAL, '앤트맨과 와스프', 9000);


