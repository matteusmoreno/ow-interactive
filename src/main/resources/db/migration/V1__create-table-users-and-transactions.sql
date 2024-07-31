CREATE TABLE users(

    id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    birthday DATE NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(19, 2) NOT NULL,
    transaction_type ENUM('CREDIT', 'DEBIT', 'REVERSAL') NOT NULL,
    user_id BIGINT,
    created_at TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
);
