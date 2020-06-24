-- Create table
create table USER
(
  MOBILE LONG not null,
  USERNAME VARCHAR(30) not null,
  Name VARCHAR(20) not null,
  LASTNAME VARCHAR(20) not null,
  EMAIL VARCHAR(30) not null,
  PASSWORD  VARCHAR(128) not null,
  USERTYPE VARCHAR(10) not null,
  CREATEDDATE DATE NOT NULL,
  UPDATEDDATE DATE NOT NULL,
  ACTIVE BIT not null,
  PRIMARY KEY(MOBILE)
) ;
