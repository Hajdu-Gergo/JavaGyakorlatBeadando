CREATE DATABASE IF NOT EXISTS `SprintUp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `SprintUp`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL DEFAULT 'ROLE_USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'Admin', 'admin@gmail.com', '$2a$10$QEaf3I.eLiZC4F4pDnqmC.sTysFlJ59wgROmw3ATxceFs/wgg0LvK', 'ROLE_ADMIN'),
(3, 'User', 'user@gmail.com', '$2a$10$exVjZOnYQ3oFdNTFP7qVHOoL8K2XhKpWXY3r8duw8v9pTNxmC0qbm', 'ROLE_USER');

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

-- Tábla létrehozása
CREATE TABLE diakok (
                        diakaz INT PRIMARY KEY,
                        nev VARCHAR(100),
                        szulido DATETIME
);

-- Adatok beszúrása
INSERT INTO diakok (diakaz, nev, szulido) VALUES
(1, 'Kos Péter', '1987-11-05 00:00:00'),
(2, 'Port Imre', '1990-05-05 00:00:00'),
(3, 'Kovács Imre', '1984-03-02 00:00:00'),
(4, 'Horváth Emil', '1988-05-02 00:00:00'),
(5, 'Kapos Petra', '1985-12-23 00:00:00'),
(6, 'Csóka Anna', '1981-11-30 00:00:00'),
(7, 'Nyúl Tamás', '1988-02-16 00:00:00'),
(8, 'Ordasi Emma', '1989-01-03 00:00:00'),
(9, 'Koppány Olga', '1984-02-28 00:00:00'),
(10, 'Kozma Patrícia', '1983-06-01 00:00:00');

-- Tábla létrehozása
CREATE TABLE munkahelyek (
                             mhelyid INT,
                             diakaz INT,
                             allas VARCHAR(100),
                             datum DATETIME,
                             oradij INT,
                             oraszam INT,
                             kozepiskolas BOOLEAN
);

-- Adatok beszúrása
INSERT INTO munkahelyek (mhelyid, diakaz, allas, datum, oradij, oraszam, kozepiskolas) VALUES
  (1, 1, 'kézbesítő', '2003-07-02 00:00:00', 400, 4, 0),
  (1, 2, 'ügyfélszolgálati munkatárs', '2003-07-03 00:00:00', 300, 4, 0),
  (2, 1, 'kisegítő', '2003-07-01 00:00:00', 500, 4, 1),
  (2, 2, 'eladó', '2003-07-01 00:00:00', 350, 4, 0),
  (1, 1, 'kézbesítő', '2003-07-03 00:00:00', 450, 6, 0),
  (1, 2, 'futár', '2003-07-06 00:00:00', 300, 5, 1),
  (2, NULL, 'eladó', '2003-07-07 00:00:00', 400, 6, 0),
  (9, NULL, 'kisegítő', '2003-06-19 00:00:00', 300, 4, 0),
  (9, 6, 'takarító', '2003-06-19 00:00:00', 400, 4, 0),
  (9, 6, 'takarító', '2003-06-20 00:00:00', 400, 4, 0),
  (9, 8, 'kisegítő', '2003-06-21 00:00:00', 300, 6, 1),
  (9, 8, 'kisegítő', '2003-06-22 00:00:00', 300, 6, 1),
  (9, 6, 'takarító', '2003-06-23 00:00:00', 400, 4, 0),
  (6, 4, 'ruhatáros', '2003-09-11 00:00:00', 400, 4, 0),
  (6, 7, 'ruhatáros', '2003-09-11 00:00:00', 400, 4, 0),
  (6, 5, 'pincér', '2003-06-11 00:00:00', 450, 6, 0),
  (6, 5, 'pincér', '2003-06-12 00:00:00', 450, 6, 0),
  (8, 9, 'eladó', '2003-07-21 00:00:00', 500, 4, 0),
  (8, 9, 'eladó', '2003-07-22 00:00:00', 500, 4, 0),
  (8, 10, 'takarító', '2003-08-10 00:00:00', 350, 6, 1),
  (8, 10, 'takarító', '2003-08-11 00:00:00', 350, 6, 1),
  (11, 5, 'eladó', '2003-05-10 00:00:00', 400, 4, 0),
  (11, 6, 'raktáros', '2003-05-20 00:00:00', 300, 4, 0),
  (11, 5, 'eladó', '2003-06-23 00:00:00', 300, 6, 0),
  (10, NULL, 'takarító', '2003-07-29 00:00:00', 250, 4, 1),
  (10, NULL, 'takarító', '2003-07-30 00:00:00', 250, 4, 1),
  (10, 3, 'takarító', '2003-07-31 00:00:00', 300, 4, 1),
  (10, 3, 'takarító', '2003-08-01 00:00:00', 300, 4, 1),
  (10, 8, 'eladó', '2003-09-12 00:00:00', 300, 6, 1),
  (10, 8, 'eladó', '2003-09-13 00:00:00', 300, 6, 1),
  (7, 8, 'ruhatáros', '2003-08-20 00:00:00', 200, 4, 1),
  (7, 8, 'ruhatáros', '2003-08-21 00:00:00', 200, 4, 1),
  (7, NULL, 'ruhatáros', '2003-08-22 00:00:00', 200, 4, 1),
  (7, NULL, 'ruhatáros', '2003-08-23 00:00:00', 200, 4, 1),
  (4, NULL, 'raktáros', '2003-10-10 00:00:00', 300, 6, 0),
  (4, NULL, 'raktáros', '2003-10-11 00:00:00', 300, 6, 0),
  (4, NULL, 'raktáros', '2003-10-12 00:00:00', 300, 6, 0),
  (5, 7, 'eladó', '2003-06-05 00:00:00', 400, 4, 0),
  (5, 7, 'eladó', '2003-06-06 00:00:00', 400, 4, 0),
  (5, 7, 'eladó', '2003-06-07 00:00:00', 400, 4, 0),
  (11, NULL, 'eladó', '2003-07-07 00:00:00', 400, 6, 0);

-- Tábla létrehozása
CREATE TABLE munkahelyek_neve (
                                  mhelyid INT PRIMARY KEY,
                                  nev VARCHAR(100),
                                  telepules VARCHAR(100)
);

-- Adatok beszúrása
INSERT INTO munkahelyek_neve (mhelyid, nev, telepules) VALUES
   (1, 'Bicaj Futárszolgálat', 'Csepűfalva'),
   (2, 'Soós Pékség', 'Szombati'),
   (3, 'Pokol Vagyonvédelem', 'Komád'),
   (4, 'Vass Kereskedés', 'Zombrád'),
   (5, 'Kati Virágbolt', 'Komád'),
   (6, 'Fekete Kávéház', 'Szombati'),
   (7, 'Körúti Kávéház', 'Csepűfalva'),
   (8, 'Királyi Cukrászat', 'Zombrád'),
   (9, 'Konty Fodrászat', 'Szombati'),
   (10, 'Falat Pékség', 'Komád'),
   (11, 'Korr Vaskereskedés', 'Csepűfalva');

--Kapcsolat tábla létrehozása
CREATE TABLE kapcsolat (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Egyedi azonosító
    nev VARCHAR(255) NOT NULL,          -- Név oszlop
    uzenet TEXT NOT NULL,               -- Üzenet oszlop
    datum TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Dátum automatikusan az aktuális idő
);

