ALTER TABLE employees
INSERT COLUMN role VARCHAR(255) AFTER profile_photo_url

# (
#     id                BIGINT AUTO_INCREMENT NOT NULL,
#     eeid              VARCHAR(255)          NOT NULL,
#     first_name        VARCHAR(255)          NULL,
#     last_name         VARCHAR(255)          NULL,
#     official_email_id VARCHAR(255)          NOT NULL,
#     personal_email_id VARCHAR(255)          NOT NULL,
#     date_of_birth     date                  NULL,
#     gender            VARCHAR(255)          NULL,
#     emp_status        VARCHAR(255)          NULL,
#     profile_photo_url VARCHAR(255)          NULL,
#     role              VARCHAR(255)          NULL,
#     department_id     BIGINT                NOT NULL,
#     CONSTRAINT pk_employees PRIMARY KEY (id)
# );
#
# ALTER TABLE employees
#     ADD CONSTRAINT uc_employees_eeid UNIQUE (eeid);
#
# ALTER TABLE employees
#     ADD CONSTRAINT FK_EMPLOYEES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES departments (id);