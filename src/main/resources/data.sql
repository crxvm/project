INSERT INTO Organization (version, name, full_Name, inn, kpp, address) VALUES (0, 'Gazprom', 'OOO GAZPROM', 1234567, 12345678, 'ул.Боткина');

INSERT INTO Organization (version, name, full_Name, inn, kpp, address) VALUES (0, 'SBERBANK', 'PAO SBERBANK', 6543123, 23467721, 'ул.Вавилова');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeFullView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeFullView 2');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeFullView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeFullView 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 1, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 1, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 2, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 2, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 3, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 3, 'User 2');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 4, 'User 1');

INSERT INTO User (version, office_Id, first_Name) VALUES(0, 4, 'User 2');

INSERT INTO Document (version, doc_Code, doc_Name) VALUES (0, 1, 'Passport');

INSERT INTO Document (version, doc_Code, doc_Name) VALUES (0, 2, 'Driver License');

INSERT INTO userdocument(user_id, version, document_Id, doc_Number, doc_Date) VALUES
(1, 0, 1, 123, null);


