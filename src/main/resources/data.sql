INSERT INTO Organization (version, name, FullName, inn, kpp, address) VALUES (0, 'Gazprom', 'OOO GAZPROM', 1234567, 12345678, 'ул.Боткина');

INSERT INTO Organization (version, name, FullName, inn, kpp, address) VALUES (0, 'SBERBANK', 'PAO SBERBANK', 6543123, 23467721, 'ул.Вавилова');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeFullView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 1, 'GAZPROM OfficeFullView 2');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeFullView 1');

INSERT INTO Office (version, org_Id, name) VALUES(0, 2, 'SBERBANK OfficeFullView 2');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 1, 'User 1');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 1, 'User 2');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 2, 'User 1');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 2, 'User 2');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 3, 'User 1');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 3, 'User 2');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 4, 'User 1');

INSERT INTO User (version, office_Id, firstName) VALUES(0, 4, 'User 2');

INSERT INTO Document (version, docCode, docName) VALUES (0, 1, 'Passport');

INSERT INTO Document (version, docCode, docName) VALUES (0, 2, 'Driver License');
