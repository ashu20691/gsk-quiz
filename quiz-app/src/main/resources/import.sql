INSERT INTO `role` (`roleid`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` (`roleid`, `name`) VALUES ('2', 'ROLE_USER');


INSERT  INTO `user`(`email`,`enabled`,`fullname`,`password`,`roleid`) VALUES ('admin@gsk.com',b'1','admin','$2a$10$7Zs/o7VMgUZiFAdA18RfM.jEtkhrwh6r3u.h5JEkVqyNi77kF5SM6',1);

-- uncomment to insert dummy user
-- INSERT  INTO `user`(`userid`,`created_date`,`email`,`enabled`,`fullname`,`password`,`roleid`) VALUES (2,'2018-01-20 18:58:07','user1@gmail.com',b'1','first user','$2a$10$5UrnVKYllWSgHGcXJl/Ao.MrtrMFACCWGrhfx.6.3WW9Q.O7rDUBC',2);
