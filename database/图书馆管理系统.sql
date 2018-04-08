create database LibrarySystem;
use librarySystem;

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `username`  varchar(20) BINARY NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `state` int(2) DEFAULT '1',
  PRIMARY KEY (`aid`)
);



CREATE TABLE `authorization` (
  `aid` int(11) NOT NULL,
  `bookSet` int(2) DEFAULT '0',
  `readerSet` int(2) DEFAULT '0',
  `borrowSet` int(2) DEFAULT '0',
  `typeSet` int(2) DEFAULT '0',
  `backSet` int(2) DEFAULT '0',
  `forfeitSet` int(2) DEFAULT '0',
  `sysSet` int(2) DEFAULT '0',
  `superSet` int(2) DEFAULT '0',
  PRIMARY KEY (`aid`),
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`)
);


CREATE TABLE `readertype` (
  `readerTypeId` int(11) NOT NULL,
  `readerTypeName` varchar(255) NOT NULL,
  `maxNum` int(5) NOT NULL,
  `bday` int(5) NOT NULL,
  `penalty` double NOT NULL,
  `renewDays` int(5) NOT NULL,
  PRIMARY KEY (`readerTypeId`)
);





CREATE TABLE `reader` (
  `readerId` varchar(255) NOT NULL,
  `pwd` varchar(64) NOT NULL,
  `name` varchar(20) NOT NULL,
  `paperNO` varchar(20) UNIQUE NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `readerTypeId` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`readerId`),
  CONSTRAINT  FOREIGN KEY (`readerTypeId`) REFERENCES `readertype` (`readerTypeId`) ON DELETE SET NULL,
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`)
);





CREATE TABLE `booktype` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(20) NOT NULL,
  PRIMARY KEY (`typeId`)
);




CREATE TABLE `book` (
  `bookId` int(11) NOT NULL,
  `bookName` varchar(20) NOT NULL,
  `ISBN` varchar(20)  UNIQUE NOT NULL,
  `autho` varchar(20) DEFAULT  NULL,
  `num` int(11) NOT NULL,
  `currentNum` int(5) NOT NULL,
  `press` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `putdate` datetime DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`),
  CONSTRAINT  FOREIGN KEY (`typeId`) REFERENCES `booktype` (`typeId`) ON DELETE SET NULL
) ;




CREATE TABLE `borrowinfo` (
  `borrowId` int(11) NOT NULL,
  `borrowDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `overday` int(11) DEFAULT '0',
  `state` int(2) DEFAULT '0',
  `readerId` varchar(255) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
	`penalty` double NOT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`borrowId`),
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`),
  CONSTRAINT  FOREIGN KEY (`readerId`) REFERENCES `reader` (`readerId`) ON DELETE CASCADE,
  CONSTRAINT  FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`) ON DELETE CASCADE
);



CREATE TABLE `backinfo` (
  `borrowId` int(11) NOT NULL,
  `backDate` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`borrowId`),
  CONSTRAINT  FOREIGN KEY (`borrowId`) REFERENCES `borrowinfo` (`borrowId`) ON DELETE CASCADE,
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`)
);











CREATE TABLE `forfeitinfo` (
  `borrowId` int(11) NOT NULL,
  `forfeit` double DEFAULT NULL,
  `isPay` int(2) DEFAULT '0',
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`borrowId`),
  CONSTRAINT  FOREIGN KEY (`borrowId`) REFERENCES `borrowinfo` (`borrowId`) ON DELETE CASCADE,
  CONSTRAINT  FOREIGN KEY (`aid`) REFERENCES `admin` (`aid`)
);



INSERT INTO ADMIN (aid,username,name,pwd,state) VALUES(1,'admin','cairou','ISMvKXpXpadDiUoOSoAfww==','1');

INSERT INTO authorization VALUES('1','1','1','1','1','1','1','1','1');










