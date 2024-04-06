alter session set "_ORACLE_SCRIPT"=true;

CREATE USER finalProject IDENTIFIED BY finalProject; 
GRANT CREATE SESSION TO finalProject;
GRANT CREATE TABLE TO finalProject;
GRANT CREATE VIEW TO finalProject;
GRANT CREATE PROCEDURE TO finalProject;
GRANT CREATE SYNONYM TO finalProject;
GRANT CREATE TRIGGER TO finalProject;
GRANT CREATE SEQUENCE TO finalProject;
ALTER USER finalProject QUOTA UNLIMITED ON USERS;

connect finalProject/finalProject;


--cd C:\jakarta-tomcat-5.0.25\webapps\FinalProject\WEB-INF\classes
--set path=%path%;C:\Program Files\Java\jdk1.8.0_25\bin 
--javac movieJdbc.java 


--cd C:\jakarta-tomcat-5.0.25\webapps\Movie\WEB-INF\classes