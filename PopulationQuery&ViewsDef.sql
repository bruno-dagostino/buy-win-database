USE CompraEVinci;


/* CLIENTE */
INSERT INTO Cliente VALUES("email0@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 120);
INSERT INTO Cliente VALUES("email1@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 135.99);
INSERT INTO Cliente VALUES("email2@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 0);
INSERT INTO Cliente VALUES("email3@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 1183);
INSERT INTO Cliente VALUES("email4@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 0);
INSERT INTO Cliente VALUES("email5@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 0);
INSERT INTO Cliente VALUES("email6@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 0);
INSERT INTO Cliente VALUES("email7@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 289);
INSERT INTO Cliente VALUES("email8@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 150);
INSERT INTO Cliente VALUES("email9@dominio.it", "$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW", "Nome", "Cognome", "Indirizzo", "0000000000", 59);

/* PRODOTTO */
INSERT INTO Prodotto VALUES("ID0", "Nome Prodotto 0", "Descrizione Prodotto 0", 10.0, NULL, NULL, NULL);
INSERT INTO Prodotto VALUES("ID1", "Nome Prodotto 1", "Descrizione Prodotto 1", 100.0, 80.0,  "2017-07-07", "2017-07-25");
INSERT INTO Prodotto VALUES("ID2", "Nome Prodotto 2", "Descrizione Prodotto 2", 58.99, 49.99,  "2017-07-07", "2017-08-28");
INSERT INTO Prodotto VALUES("ID3", "Nome Prodotto 3", "Descrizione Prodotto 3", 60.0, 12.0,  "2017-07-02", "2017-07-30");
INSERT INTO Prodotto VALUES("ID4", "Nome Prodotto 4", "Descrizione Prodotto 4", 58.0,  50.0, "2016-05-05", "2016-05-25");
INSERT INTO Prodotto VALUES("ID5", "Nome Prodotto 5", "Descrizione Prodotto 5", 1000.0, 900.0,  "2017-05-01", "2017-09-12");
INSERT INTO Prodotto VALUES("ID6", "Nome Prodotto 6", "Descrizione Prodotto 6", 150.0, 99.99,  "2017-12-01", "2018-01-01");
INSERT INTO Prodotto VALUES("ID7", "Nome Prodotto 7", "Descrizione Prodotto 7", 6.0, NULL, NULL, NULL);
INSERT INTO Prodotto VALUES("ID8", "Nome Prodotto 8", "Descrizione Prodotto 8", 150.50, 81.0,  "2017-08-01", "2017-08-02");
INSERT INTO Prodotto VALUES("ID9", "Nome Prodotto 9", "Descrizione Prodotto 9", 189.0, NULL, NULL, NULL);
INSERT INTO Prodotto VALUES("IDA", "Nome Prodotto A", "Descrizione Prodotto A", 123.0, NULL, NULL, NULL);
INSERT INTO Prodotto VALUES("IDB", "Nome Prodotto B", "Descrizione Prodotto B", 160.0, NULL, NULL, NULL);

/* ORDINE */
INSERT INTO Ordine VALUES (0,'email0@dominio.it','ID0',10.00,1,'2017-07-10');
INSERT INTO Ordine VALUES (1,'email1@dominio.it','ID1',80.00,2,'2017-07-10');
INSERT INTO Ordine VALUES (1,'email1@dominio.it','ID2',49.99,1,'2017-07-10');
INSERT INTO Ordine VALUES (2,'email0@dominio.it','ID0',10.00,1,'2017-07-10');
INSERT INTO Ordine VALUES (3,'email0@dominio.it','ID1',100.00,7,'2017-07-10');
INSERT INTO Ordine VALUES (4,'email3@dominio.it','ID5',900.00,5,'2017-07-13');
INSERT INTO Ordine VALUES (4,'email3@dominio.it','IDA',123.00,12,'2017-07-13');
INSERT INTO Ordine VALUES (4,'email3@dominio.it','IDB',160.00,63,'2017-07-13');
INSERT INTO Ordine VALUES (5,'email9@dominio.it','ID4',58.00,4,'2017-07-14');
INSERT INTO Ordine VALUES (6,'email8@dominio.it','ID8',150.00,2,'2017-07-14');
INSERT INTO Ordine VALUES (7,'email1@dominio.it','ID7',6.00,1,'2017-07-15');
INSERT INTO Ordine VALUES (8,'email7@dominio.it','ID9',189.00,1445,'2017-07-18');
INSERT INTO Ordine VALUES (8,'email7@dominio.it','ID1',100.00,12,'2017-07-18');

/* CARRELLO */
INSERT INTO Carrello VALUES("email0@dominio.it", "ID0", 12);
INSERT INTO Carrello VALUES("email5@dominio.it", "ID1", 50);
INSERT INTO Carrello VALUES("email0@dominio.it", "ID2", 1);
INSERT INTO Carrello VALUES("email8@dominio.it", "ID11", 2);


/* PREMIO */
INSERT INTO Premio VALUES ('Premio 1', '50');
INSERT INTO Premio VALUES ('Premio 2', '100');
INSERT INTO Premio VALUES ('Premio 3', '200');
INSERT INTO Premio VALUES ('Premio 4', '600');
INSERT INTO Premio VALUES ('Premio 5', '1000');
INSERT INTO Premio VALUES ('Premio 6', '1800');


/* STORICO */
INSERT INTO Storico VALUES ('2017-06-01', 'email0@dominio.it', 'Premio 1');
INSERT INTO Storico VALUES ('2017-06-01', 'email1@dominio.it', 'Premio 1');
INSERT INTO Storico VALUES ('2017-05-01', 'email5@dominio.it', 'Premio 2');
INSERT INTO Storico VALUES ('2017-05-01', 'email2@dominio.it', 'Premio 4');
INSERT INTO Storico VALUES ('2017-05-01', 'email8@dominio.it', 'Premio 6');
INSERT INTO Storico VALUES ('2017-05-01', 'email3@dominio.it', 'Premio 5');
INSERT INTO Storico VALUES ('2017-04-01', 'email7@dominio.it', 'Premio 4');
INSERT INTO Storico VALUES ('2017-04-01', 'email5@dominio.it', 'Premio 3');
INSERT INTO Storico VALUES ('2017-04-01', 'email3@dominio.it', 'Premio 1');



/* SPESA MINIMA PER ENTRARE IN CLASSIFICA = 50 */
/* VISTA CLASSIFICA + PREMI ASSEGNATI */
CREATE VIEW ClassificaClientiV AS
SELECT C.email, C.password, C.nome, C.cognome, C.indirizzo, C.telefono, C.spesaMensile, P.nome as nomePremio
FROM Cliente C, Premio P
WHERE
C.spesaMensile >  50 AND
C.spesaMensile >= (SELECT MAX(fasciaSpesa) FROM Premio P2 WHERE P2.fasciaSpesa <= C.spesaMensile) AND
P.fasciaSpesa = (SELECT MAX(fasciaSpesa) FROM Premio P3 WHERE P3.fasciaSpesa <= C.spesaMensile)
ORDER BY C.spesaMensile DESC;


/* VISTA PRODOTTI PIU VENDUTI DEL MESE */
CREATE VIEW ProdottiPiuVendutiV AS
SELECT *
FROM Prodotto P
JOIN

(
SELECT id_prodotto, SUM(Ordine.qty) AS nOrdinati
FROM Ordine
WHERE MONTH(data) = MONTH(CURDATE())
GROUP BY id_prodotto
) AS tmp

ON id_prodotto = P.id ORDER BY nOrdinati DESC;

/* VISTA PRODOTTI SCONTATI */
CREATE VIEW ProdottiScontatiV AS
SELECT *
FROM Prodotto
WHERE dataInizioSconto <= CURDATE() AND dataFineSconto >= CURDATE()
ORDER BY dataFineSconto ASC








	/* SENZA RIDONDANZA */
	/*private static final String SQL_LIST_VINCITORI = "SELECT * FROM Cliente JOIN ClientiVincitori ON email=email_cliente";
	
	private static final String SQL_LIST_CLASSIFICA = 	"SELECT * FROM Cliente" + 
														" JOIN" + 
														" (SELECT email_cliente, SUM(prezzo) AS speso FROM Ordine GROUP BY email_cliente) AS tmp" +
														" ON email=email_cliente ORDER BY speso DESC";*/
	