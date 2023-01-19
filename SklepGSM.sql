/* SQL Manager for MySQL                              5.8.0.53936 */
/* -------------------------------------------------------------- */
/* Host     : localhost                                           */
/* Port     : 3306                                                */
/* Database : SklepGSM                                            */


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'utf8mb4' */;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `SklepGSM`
    CHARACTER SET 'utf8mb4'
    COLLATE 'utf8mb4_general_ci';

USE `sklepgsm`;

/* Structure for the `klienci` table : */

CREATE TABLE `klienci` (
  `ID_Klienta` CHAR(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Hasło` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Płeć` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Data_Urodzenia` DATE NOT NULL,
  `Telefon` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Adres` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Rola` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY USING BTREE (`ID_Klienta`)
) ENGINE=InnoDB
ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci'
;

/* Structure for the `produkty` table : */

CREATE TABLE `produkty` (
  `ID_Produktu` CHAR(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Nazwa` TEXT COLLATE utf8mb4_general_ci NOT NULL,
  `Cena` INTEGER NOT NULL,
  `Stan_magazyn` INTEGER NOT NULL,
  PRIMARY KEY USING BTREE (`ID_Produktu`)
) ENGINE=InnoDB
ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci'
;

/* Structure for the `transakcje` table : */

CREATE TABLE `transakcje` (
  `ID_Transakcji` CHAR(5) COLLATE utf8mb4_general_ci NOT NULL,
  `ID_Produktu` CHAR(5) COLLATE utf8mb4_general_ci NOT NULL,
  `ID_Klienta` CHAR(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Data_Transakcji` DATE NOT NULL,
  `Ilość_szt` INTEGER NOT NULL,
  PRIMARY KEY USING BTREE (`ID_Transakcji`),
  KEY `transakcje_fk1` USING BTREE (`ID_Produktu`),
  KEY `transakcje_fk2` USING BTREE (`ID_Klienta`),
  CONSTRAINT `transakcje_fk1` FOREIGN KEY (`ID_Produktu`) REFERENCES `produkty` (`ID_Produktu`),
  CONSTRAINT `transakcje_fk2` FOREIGN KEY (`ID_Klienta`) REFERENCES `klienci` (`ID_Klienta`)
) ENGINE=InnoDB
ROW_FORMAT=DYNAMIC CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci'
;

/* Data for the `klienci` table  (LIMIT 0,500) */

INSERT INTO `klienci` (`ID_Klienta`, `Email`, `Hasło`, `Płeć`, `Data_Urodzenia`, `Telefon`, `Adres`, `Rola`) VALUES
  ('KL002','admin@admin.pl','admin','Mężczyzna','1999-06-25','690489779','Hawaii','Admin'),
  ('KL004','ala@gmail.com','olo321','Kobieta','1990-10-28','567255456','Ul. Konicza 45/2','Klient'),
  ('KL005','patryk@gsm.pl','patix123','Mężczyzna','1997-05-12','678945213','Os. Zielonka 45/12','Klient');
COMMIT;

/* Data for the `produkty` table  (LIMIT 0,500) */

INSERT INTO `produkty` (`ID_Produktu`, `Nazwa`, `Cena`, `Stan_magazyn`) VALUES
  ('PR001','iPhone 6 64GB',639,20),
  ('PR002','iPhone 6S 64GB',729,30),
  ('PR003','iPhone 7 32GB',1299,25),
  ('PR004','iPhone 7 PLUS 128GB',1806,10),
  ('PR005','iPhone 8 64GB',1299,30),
  ('PR006','iPhone 8 PLUS 64GB',1799,15),
  ('PR007','iPhone X 64GB',2399,30),
  ('PR008','iPhone XS MAX 64GB',4030,10),
  ('PR009','iPhone 11 64GB',2899,20),
  ('PR010','iPhone 11 PRO 256GB',4799,30),
  ('PR011','iPhone 11 PRO MAX 256GB',6049,20),
  ('PR012','iPhone 12 64GB',4199,10),
  ('PR013','iPhone 12 PRO 512GB',6699,30),
  ('PR014','iPhone 12 PRO MAX 512GB',7199,25),
  ('PR015','iPhone 12 MINI 64GB',3599,30),
  ('PR016','Samsung Galaxy S20',3899,10),
  ('PR017','Samsung Galaxy S20 ULTRA',5999,20),
  ('PR018','Samsung Galaxy S10',2180,30),
  ('PR019','Samsung Galaxy S10+ 128GB',3399,10),
  ('PR020','Samsung Galaxy S20FE',2899,15);
COMMIT;

/* Data for the `transakcje` table  (LIMIT 0,500) */

INSERT INTO `transakcje` (`ID_Transakcji`, `ID_Produktu`, `ID_Klienta`, `Data_Transakcji`, `Ilość_szt`) VALUES
  ('TR001','PR005','KL004','2021-01-02',10),
  ('TR002','PR009','KL004','2021-01-02',5),
  ('TR003','PR015','KL004','2021-01-02',5),
  ('TR004','PR019','KL005','2021-01-02',15),
  ('TR005','PR012','KL005','2021-01-02',5);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;