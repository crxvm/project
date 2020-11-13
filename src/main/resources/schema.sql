CREATE TABLE IF NOT EXISTS Organization
(
    id       INTEGER                COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version  INTEGER     NOT NULL   COMMENT 'Служебное поле hibernate',
    name     VARCHAR(50) NOT NULL   COMMENT 'Имя',
    full_Name VARCHAR(50) NOT NULL   COMMENT 'Полное имя',
    inn      VARCHAR(25) NOT NULL   COMMENT 'ИНН',
    kpp      VARCHAR(9)  NOT NULL   COMMENT 'КПП',
    address  VARCHAR(50) NOT NULL   COMMENT 'Адрес',
    phone    VARCHAR(15)            COMMENT 'Телефонный номер',
    is_Active BOOLEAN                COMMENT 'Статус'

);

CREATE TABLE IF NOT EXISTS Office
(
    id       INTEGER                    COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    org_Id   INTEGER    NOT NULL        COMMENT 'Индентификатор организации',
    version  INTEGER    NOT NULL        COMMENT 'Служебное поле hibernate',
    name     VARCHAR(50)                COMMENT 'Имя',
    address  VARCHAR(50)                COMMENT 'Адрес',
    phone    VARCHAR(15)                COMMENT 'Телефонный номер',
    is_Active BOOLEAN                    COMMENT 'Статуc'

);

CREATE INDEX IX_Organization_Id ON Office (org_Id);
ALTER TABLE Office
    ADD FOREIGN KEY (org_Id) REFERENCES Organization (id);

CREATE TABLE IF NOT EXISTS User
(
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    office_Id       INTEGER     NOT NULL    COMMENT 'Идентификатор офиса',
    first_Name       VARCHAR(15) NOT NULL    COMMENT 'Имя',
    second_Name      VARCHAR(15)             COMMENT 'Фамилия',
    middle_Name      VARCHAR(15)             COMMENT 'Отчество',
    position        VARCHAR(50)             COMMENT 'Должность',
    phone           VARCHAR(15)             COMMENT 'Телефонный номер',
    citizenship_Id  INTEGER                 COMMENT 'Идентификатор страны',
    is_Identified    BOOLEAN                 COMMENT 'Статус'
);

CREATE INDEX IX_Office_Id ON User (office_Id);
ALTER TABLE User
    ADD FOREIGN KEY (office_Id) REFERENCES Office (id);

CREATE TABLE IF NOT EXISTS User_Document
(
    user_id INTEGER PRIMARY KEY ,
    version INTEGER,
    document_Id     INTEGER                 COMMENT 'Идентификатор документа из справочника',
    doc_Number       VARCHAR(10)             COMMENT 'Номер документа',
    doc_Date         DATE                    COMMENT 'Дата выдачи документа'

);

ALTER TABLE User_Document
    ADD FOREIGN KEY (user_id) REFERENCES User (id);


CREATE TABLE IF NOT EXISTS Document
(
    id          INTEGER         COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    doc_Code   VARCHAR(10)     COMMENT 'Код документа' ,
    doc_Name     VARCHAR(50)     COMMENT 'Название документа',
    UNIQUE (doc_Code, doc_Name)
);

CREATE TABLE IF NOT EXISTS Country
(
    id              INTEGER     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    citizenship_Code VARCHAR(5)  COMMENT 'Код страны',
    citizenship_Name VARCHAR(25) COMMENT 'Название страны',
    UNIQUE (citizenship_Code, citizenship_Name)
);


CREATE INDEX IX_Document_Id ON User_Document(document_Id);
ALTER TABLE User_Document
    ADD FOREIGN KEY (document_Id) REFERENCES Document (id);


CREATE INDEX IX_citizenshipId ON User (citizenship_Id);
ALTER TABLE User
    ADD FOREIGN KEY (citizenship_Id) REFERENCES Country (id);

