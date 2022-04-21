CREATE TABLE if not exists citizens (
citizen_id BIGINT AUTO_INCREMENT PRIMARY KEY,
citizen_name VARCHAR(200) NOT NULL,
zip VARCHAR(4) NOT NULL,
age BIGINT NOT NULL,
email VARCHAR(200),
taj VARCHAR(10),
number_of_vaccination BIGINT,
last_vaccination DATETIME
);

CREATE TABLE if not exists vaccinations (
citizen_id BIGINT REFERENCES citizens(citizen_id),
vaccination_date DATETIME NOT NULL,
`status` VARCHAR(10),
note VARCHAR(200),
vaccination_type VARCHAR(20)
);

