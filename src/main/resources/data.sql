INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'Admin', 'Istrator', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'admin', 'admin', '2023-03-12 00:00:00', False);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('admin', 'ADMIN');
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('admin', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'Manager', 'Alex', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'manager', 'admin', '2023-08-15 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('manager', 'MANAGER');
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('manager', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User1', 'Lastname1', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user1', 'admin', '2023-03-30 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user1', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User2', 'Lastname2', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user2', 'admin', '2023-05-20 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user2', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User3', 'Lastname3', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user3', 'admin', '2023-03-28 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user3', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User4', 'Lastname4', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user4', 'admin', '2023-11-27 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user4', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User5', 'Lastname5', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user5', 'admin', '2023-12-12 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user5', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User6', 'Lastname6', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user6', 'admin', '2023-07-20 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user6', 'USER');
INSERT INTO USERX (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE, PREMIUM) VALUES(TRUE, 'User7', 'Lastname7', '$2a$10$sk/DirzmRy2gEJb65Zp7hOfbZIf42PtrKvlxGAywzSbLtgmnJE5ku', 'user7', 'admin', '2023-06-24 00:00:00', True);
INSERT INTO USERX_USERX_ROLE (USERX_USERNAME, ROLES) VALUES ('user7', 'USER');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-01-27 00:00:00', TRUE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-02-15 00:00:00', FALSE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-03-06 00:00:00', TRUE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-03-15 00:00:00', FALSE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-04-26 00:00:00', TRUE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-05-14 00:00:00', FALSE, 'manager');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-12-14 00:00:00', TRUE, 'manager');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-01-04 00:00:00', TRUE, 'user1');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-03-18 00:00:00', FALSE, 'user1');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-04-27 00:00:00', TRUE, 'user1');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-05-03 00:00:00', FALSE, 'user1');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-12-05 00:00:00', TRUE, 'user1');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-01-19 00:00:00', TRUE, 'user2');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-02-26 00:00:00', FALSE, 'user2');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-03-10 00:00:00', TRUE, 'user2');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-03-18 00:00:00', FALSE, 'user2');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-12-04 00:00:00', TRUE, 'user2');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-12-20 00:00:00', FALSE, 'user2');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-07-06 00:00:00', TRUE, 'user3');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-07-17 00:00:00', FALSE, 'user3');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-09-04 00:00:00', TRUE, 'user3');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-29 00:00:00', FALSE, 'user3');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-11-06 00:00:00', TRUE, 'user3');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-02 00:00:00', TRUE, 'user4');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-06 00:00:00', FALSE, 'user4');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-10 00:00:00', TRUE, 'user4');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-15 00:00:00', FALSE, 'user4');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-19 00:00:00', TRUE, 'user4');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-11-19 00:00:00', FALSE, 'user4');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-05-01 00:00:00', TRUE, 'user5');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-07-28 00:00:00', FALSE, 'user5');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-08-04 00:00:00', TRUE, 'user5');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-04 00:00:00', FALSE, 'user5');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-26 00:00:00', TRUE, 'user5');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-05-14 00:00:00', TRUE, 'user6');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-05-19 00:00:00', FALSE, 'user6');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-06-04 00:00:00', TRUE, 'user6');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-07-04 00:00:00', FALSE, 'user6');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-08-07 00:00:00', TRUE, 'user6');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-12-10 00:00:00', FALSE, 'user6');

INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-08-30 00:00:00', TRUE, 'user7');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-08-26 00:00:00', FALSE, 'user7');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-10-15 00:00:00', TRUE, 'user7');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-11-14 00:00:00', FALSE, 'user7');
INSERT INTO PREMIUM_HISTORY (CHANGE_DATE, NEW_PREMIUM_STATUS, USER_ID) VALUES ('2023-11-24 00:00:00', TRUE, 'user7');

INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user1', 'PAYED', 10,'2023-05-14 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user1', 'PAYED', 10, '2023-06-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user2', 'PAYED', 10,'2023-07-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user2', 'PAYED', 10,'2023-08-07 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user2', 'PAYED', 10,'2023-09-10 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user2', 'PAYED', 10,'2023-12-19 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user3', 'FAILED', 10,'2023-03-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user3', 'PAYED', 10,'2023-10-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user4', 'PAYED', 10,'2023-08-07 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user4', 'PAYED', 10,'2023-09-10 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user4', 'PAYED', 10,'2023-04-14 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user5', 'FAILED', 10 ,'2023-05-19 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user5', 'PAYED', 10,'2023-06-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user6', 'PAYED', 10,'2023-07-04 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user7', 'PAYED', 10,'2023-02-01 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user7', 'FAILED', 10,'2023-01-01 00:00:00');
INSERT INTO Payment_History (user_id, Payment_Status, CHARGED_DAYS, CHANGE_DATE) VALUES ('user7', 'PAYED', 10,'2023-11-01 00:00:00');

