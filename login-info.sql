USE `banking`;

DROP TABLE IF EXISTS `banking_account`;
DROP TABLE IF EXISTS `banking_card`;
DROP TABLE IF EXISTS `transaction_history`;
DROP TABLE IF EXISTS `account_card_response`;
DROP TABLE IF EXISTS `user_info`;


CREATE TABLE `user_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL UNIQUE,
    `password` char(68) NOT NULL,
	`authority` varchar(50),
    `enabled` TINYINT(1) NOT NULL DEFAULT 1,
    `fullname` varchar(50) NOT NULL,
    `phone_number` int NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user_info` VALUES
 (1, 'admin','{bcrypt}$2a$10$B0e1wQdHMzQSqPV/Zgvf7Oz8qp/a6w6hprA6G1mxERZP.7WC0YBau', 'ROLE_ADMIN', true,'Nguyen Van A',123),
 (2, 'manager','{bcrypt}$2a$10$W8712PS5DQVBiRafqjmw9uOZU8r7dA9B35tbVWbbraNnXy7OCxpQi', 'ROLE_MANAGER', true,'Nguyen Van B',234),
 (3, 'user','{bcrypt}$2a$10$YwxJiCmsgOdqiqkOLmeB5.dEc/pIUIu1J/CWLTbh19/6ldic5UoKa', 'ROLE_USER', true, 'Nguyen Van C',345);


CREATE TABLE `banking_card` (
    `card_code` int(11) NOT NULL AUTO_INCREMENT,
	`is_active` boolean,
	`due_date` timestamp,
	`user_info_id` int(11) DEFAULT NULL,
    FOREIGN KEY (`user_info_id`) REFERENCES `user_info`(`id`),
    PRIMARY KEY (`card_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `banking_card` VALUES
 (1, true, '2030-01-01 0:00:00', 1),
 (2, true, '2030-01-01 0:00:00', 2),
 (3, true, '2030-01-01 0:00:00', 3);



CREATE TABLE `transaction_history` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `transaction_category` varchar(30),
    `amount` float,
    `time` timestamp,
	`user_info_id` int(11) DEFAULT NULL,
    FOREIGN KEY (`user_info_id`) REFERENCES `user_info`(`id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `transaction_history` VALUES
 (1, 'Transfer', -1000, '2023-06-20 0:00:00', 1),
 (2, 'Receive', +2000, '2023-06-21 0:00:00', 2),
 (3, 'Pay the bill', -3000, '2023-06-22 0:00:00', 3);
 
 
 CREATE TABLE `banking_account` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `account_balance` float DEFAULT NULL,
    `transaction_history_id` int DEFAULT NULL,
    `user_info_id` int(11) DEFAULT NULL,
    FOREIGN KEY (`user_info_id`) REFERENCES `user_info`(`id`),
	FOREIGN KEY (`transaction_history_id`) REFERENCES `transaction_history`(`id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `banking_account` VALUES
 (1, 1000, 1, 1),
 (2, 1000, 2, 2),
 (3, 1000, 3, 3);

