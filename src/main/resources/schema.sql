CREATE TABLE IF NOT EXISTS Organization
(
    id       INTEGER                COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version  INTEGER     NOT NULL   COMMENT 'Служебное поле hibernate',
    name     VARCHAR(50) NOT NULL   COMMENT 'Имя',
    FullName VARCHAR(50) NOT NULL   COMMENT 'Полное имя',
    inn      VARCHAR(25) NOT NULL   COMMENT 'ИНН',
    kpp      VARCHAR(9)  NOT NULL   COMMENT 'КПП',
    address  VARCHAR(50) NOT NULL   COMMENT 'Адрес',
    phone    VARCHAR(15)            COMMENT 'Телефонный номер',
    isActive BOOLEAN COMMENT 'Статус'

);

COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office
(
    id       INTEGER                    COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    org_Id   INTEGER    NOT NULL        COMMENT 'Индентификатор организации',
    version  INTEGER    NOT NULL        COMMENT 'Служебное поле hibernate',
    name     VARCHAR(50)                COMMENT 'Имя',
    address  VARCHAR(50)                COMMENT 'Адрес',
    phone    VARCHAR(15)                COMMENT 'Телефонный номер',
    isActive BOOLEAN                    COMMENT 'Статуc',

);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User
(
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    office_Id       INTEGER     NOT NULL    COMMENT 'Идентификатор офиса',
    firstName       VARCHAR(15) NOT NULL    COMMENT 'Имя',
    secondName      VARCHAR(15)             COMMENT 'Фамилия',
    middleName      VARCHAR(15)             COMMENT 'Отчество',
    position        VARCHAR(50)             COMMENT 'Должность',
    phone           VARCHAR(15)             COMMENT 'Телефонный номер',
    isActive        BOOLEAN                 COMMENT 'Статуc',
    docCode         VARCHAR(10)             COMMENT 'Код документа',
    docName         VARCHAR(50)             COMMENT 'Название документа',
    docNumber       VARCHAR(10)             COMMENT 'Номер документа',
    docDate         DATE                    COMMENT 'Дата выдачи документа',
    citizenshipCode VARCHAR(5)              COMMENT 'Код страны',
    isIdentified    BOOLEAN                 COMMENT 'Статус'
);

COMMENT ON TABLE User IS 'Пользователь';

CREATE TABLE IF NOT EXISTS Document
(
    docNumber VARCHAR(10)   COMMENT 'Номер документа' PRIMARY KEY,
    docName   VARCHAR(50)   COMMENT 'Название документа',
);

CREATE TABLE IF NOT EXISTS Country
(
    citizenshipCode VARCHAR(5)  COMMENT 'Код страны' PRIMARY KEY,
    citizenshipName VARCHAR(25) COMMENT 'Название страны',

);

CREATE INDEX IX_Organization_Id ON Office (org_Id);
ALTER TABLE Office
    ADD FOREIGN KEY (org_Id) REFERENCES Organization (id);

CREATE INDEX IX_Office_Id ON User (office_Id);
ALTER TABLE User
    ADD FOREIGN KEY (office_Id) REFERENCES Office (id);

CREATE INDEX IX_Document_Number ON User (docNumber);
ALTER TABLE User
    ADD FOREIGN KEY (docNumber) REFERENCES Document (docNumber);

CREATE INDEX IX_citizenshipCode ON User (citizenshipCode);
ALTER TABLE User
    ADD FOREIGN KEY (citizenshipCode) REFERENCES Country (citizenshipCode);









