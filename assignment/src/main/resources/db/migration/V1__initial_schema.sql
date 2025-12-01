CREATE TABLE account (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_number VARCHAR(50) NOT NULL
);

CREATE TABLE operation_type (
    operation_type_id BIGINT PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE transaction (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_type_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES account(account_id),
    CONSTRAINT fk_transaction_operation_type FOREIGN KEY (operation_type_id) REFERENCES operation_type(operation_type_id)
);


MERGE INTO operation_type (operation_type_id, code, description)
KEY(operation_type_id)
VALUES
    (1, 'CASH_PURCHASE', 'Cash Purchase'),
    (2, 'INSTALLMENT_PURCHASE', 'Installment Purchase'),
    (3, 'WITHDRAWAL', 'Withdrawal'),
    (4, 'PAYMENT', 'Payment');