INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, AVATAR) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'), './assets/tmp/img/avatar.jpg');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, AVATAR) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'), './assets/tmp/img/avatar.jpg');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE, AVATAR) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, PARSEDATETIME('01-01-2016','dd-MM-yyyy'), './assets/tmp/img/avatar.jpg');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID) VALUES (1, 1, 1);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID) VALUES (2, 1, 2);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID) VALUES (3, 2, 1);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID) VALUES (4, 3, 1);

INSERT INTO BRAND (ID, NAME, FULL_NAME, CONTACT, PROVINCE_ID, CREATE_TIME, UPDATE_TIME, CREATE_USER_ID, NOTE) VALUES ( 1, '品牌测试', '全称', '13333333333', '110000000000', PARSEDATETIME('01-01-2016','dd-MM-yyyy'), PARSEDATETIME('01-01-2016','dd-MM-yyyy'), 1, '阿萨德挥洒核对后撒UI的哈斯U回复个安师傅个安师傅个安师傅is阿回复个暗示法会上');
