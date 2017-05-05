DELETE FROM communication;

INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopPointDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopPointDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'linesDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'linesDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'estimatedTimetable', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'subscribe', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'subscribe', 'TRUE');