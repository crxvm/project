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
    isActive BOOLEAN                COMMENT 'Статус'

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
    isActive BOOLEAN                    COMMENT 'Статуc'

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
    document_Id     INTEGER                 COMMENT 'Идентификатор документа из справочника',
    docNumber       VARCHAR(10)             COMMENT 'Номер документа',
    docDate         DATE                    COMMENT 'Дата выдачи документа',
    citizenship_Id  INTEGER                 COMMENT 'Идентификатор страны',
    isIdentified    BOOLEAN                 COMMENT 'Статус'
);

COMMENT ON TABLE User IS 'Пользователь';

CREATE TABLE IF NOT EXISTS Document
(
    id          INTEGER         COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    docCode   VARCHAR(10)     COMMENT 'Код документа' ,
    docName     VARCHAR(50)     COMMENT 'Название документа',
    UNIQUE (docCode, docName)
);

CREATE TABLE IF NOT EXISTS Country
(
    id              INTEGER     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    citizenshipCode VARCHAR(5)  COMMENT 'Код страны',
    citizenshipName VARCHAR(25) COMMENT 'Название страны',
    UNIQUE (citizenshipCode, citizenshipName)
);

CREATE INDEX IX_Organization_Id ON Office (org_Id);
ALTER TABLE Office
    ADD FOREIGN KEY (org_Id) REFERENCES Organization (id);

CREATE INDEX IX_Office_Id ON User (office_Id);
ALTER TABLE User
    ADD FOREIGN KEY (office_Id) REFERENCES Office (id);

CREATE INDEX IX_Document_Number ON User (docNumber);
ALTER TABLE User
    ADD FOREIGN KEY (document_Id) REFERENCES Document (id);

CREATE INDEX IX_citizenshipCode ON User (citizenship_Id);
ALTER TABLE User
    ADD FOREIGN KEY (citizenship_Id) REFERENCES Country (id);

