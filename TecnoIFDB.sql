CREATE DATABASE tecnoif;
USE tecnoif;

CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(50) NOT NULL,
                      email VARCHAR(50) NOT NULL,
                      telephone VARCHAR(20) NOT NULL,
                      cpf VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE address (
                         street_name VARCHAR(255) NOT NULL,
                         house_number VARCHAR(10) NOT NULL,
                         address_complement VARCHAR(255),
                         neighborhood VARCHAR(100) NOT NULL,
                         postal_code VARCHAR(20) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         state VARCHAR(100) NOT NULL,
                         id_user BIGINT NOT NULL,
                         FOREIGN KEY (id_user) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE payment (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         payment_type VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE service_order (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               description VARCHAR(100) NOT NULL,
                               status VARCHAR(30) NOT NULL,
                               emission_date DATE NOT NULL,
                               finalization_date DATE,
                               price DECIMAL(20,2) NOT NULL,
                               notes VARCHAR(50),
                               id_user BIGINT NOT NULL,
                               id_payment BIGINT NOT NULL,
                               FOREIGN KEY (id_user) REFERENCES user(id),
                               FOREIGN KEY (id_payment) REFERENCES payment(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO user (name, email, telephone, cpf)
VALUES ('Suporte', 'tecnoif@gmail.com', '16999999999', '000000');
