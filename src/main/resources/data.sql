INSERT INTO Organization (version, name, full_Name, inn, kpp, address) VALUES (0, 'Gazprom', 'OOO GAZPROM', 1234567, 12345678, 'ул.Боткина');

INSERT INTO Organization (version, name, full_Name, inn, kpp, address) VALUES (0, 'SBERBANK', 'PAO SBERBANK', 6543123, 23467721, 'ул.Вавилова');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeView 2');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeView 2');

INSERT INTO country (version, citizenship_Code, citizenship_Name) VALUES
(0, '643', 'Российская Федерация');
INSERT INTO country (version, citizenship_Code, citizenship_Name) VALUES
(0, '826', 'Великобритания');
INSERT INTO country (version, citizenship_Code, citizenship_Name) VALUES
(0, '764', 'Тайланд');
INSERT INTO country (version, citizenship_Code, citizenship_Name) VALUES
(0, '860', 'Узбекистан');
INSERT INTO country (version, citizenship_Code, citizenship_Name) VALUES
(0, '392', 'Япония');

INSERT INTO Document (version, doc_Code, doc_Name) VALUES (0, 1, 'Passport');

INSERT INTO Document (version, doc_Code, doc_Name) VALUES (0, 2, 'Driver License');

INSERT INTO organization (version, name, full_Name, inn, kpp, address, phone, is_Active)
VALUES (0, 'TestName', 'TestFullName', 'TestInn', 'TestKpp', 'TestAddress', 'TestPhone', true);

INSERT INTO office (org_Id, version, name, address, phone, is_Active) VALUES (1, 0, 'TestName', 'TestAddress', 'TestPhone', true);

INSERT INTO user (version, office_Id, first_Name, second_Name, middle_Name, position, phone, citizenship_Id, is_Identified) VALUES
(0, 1, 'TestFName', 'TestSName', 'TestMName', 'TestPosition', 'TestPhone', 5, true);
INSERT INTO user_document (user_id, version, document_Id, doc_Number, doc_Date) VALUES
(1,0, 2, 'TestNumber', null);

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 1, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 1, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 2, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 2, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 3, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 3, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 4, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 4, 'User 2');

