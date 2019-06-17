package com.mycompany.progetto;

import java.util.Scanner;

public final class CEV {

	private static final String MENU =
			"1) Visualizzazione ordini\n" + 									/* FATTO */
			"2) Visualizzazione clienti\n" + 									/* FATTO */
			"3) Visualizzazione classifica attuale\n" + 						/* FATTO */
			"4) Visualizzazione prodotti\n" + 									/* FATTO */
			"5) Visualizzazione prodotti più venduti del mese\n" +				/* FATTO */
			"6) Visualizzazione carrello\n" +									/* FATTO */
			"7) Visualizzazione sconti attivi\n" + 								/* FATTO */
			"8) Visualizza posizione cliente in classifica\n" +					/* FATTO */
			"9) Visualizza premi\n" +											/* FATTO */
			"10) Inserisci cliente\n" +                                             /* FATTO */
                        "11) Inserisci ordine\n" +
                        "S) Visualizzazione storico\n" +									/* FATTO */
			"D) Visualizzazione classifica vincitori e reset classifica\n" +	/* FATTO */
                        "M) Stampa menu\n" +
			"L) Logout"
			;
	
	public static final String URL = "jdbc:mysql://localhost/CompraEVinci?user=root&password=root&useSSL=false&serverTimezone=UTC";
	
	private static void printMenu()
	{
		System.out.println("Azioni disponibili:");
		System.out.println(MENU);	
	}
	
	private static void routeOperatore(String op, Scanner scanner)
	{
		String emailCliente;
		switch (op) {
		case "1":
			System.out.print("Inserire email cliente (* per tutti): ");
			emailCliente = scanner.next();
			if (emailCliente.equals("*"))
				OrdineCRUD.list();
			else
				OrdineCRUD.listByCliente(emailCliente);
			break;
		case "2":
			ClienteCRUD.list();
			break;
		case "3":
			ClienteCRUD.listClassifica();
			break;
		case "4":
			ProdottoCRUD.list();
			break;
		case "5":
			ProdottoCRUD.listPiuOrdinati();
			break;
		case "6":
			System.out.print("Inserire email cliente (* per tutti): ");
			emailCliente = scanner.next();
			if (emailCliente.equals("*"))
				CarrelloCRUD.list();
			else
				CarrelloCRUD.listByCliente(emailCliente);
			break;
		case "7":
			ProdottoCRUD.listScontati();
			break;
		case "8":
			System.out.print("Inserire email cliente: ");
			emailCliente = scanner.next();
			ClienteCRUD.printPosition(emailCliente);
			break;
		case "9":
			PremioCRUD.list();
			break;
                case "10":
                        System.out.print("Inserire email cliente: ");
                        emailCliente = scanner.next();
                        ClienteCRUD.insert(emailCliente);
                        break;
                case "11":
                        System.out.print("Inserisci email cliente, id prodotto e quantità separati dallo spazio: ");
                        emailCliente = scanner.next();
                        String id_prodotto = scanner.next();
                        int qty = scanner.nextInt();
                        OrdineCRUD.insert(emailCliente, id_prodotto, qty);
                        break;
		case "S":
			System.out.print("Inserire YYYY-MM-DD (* per tutti): ");
			String data = scanner.next();
			if (data.equals("*"))
				StoricoCRUD.list();
			else
				StoricoCRUD.listByData(data);
			break;
		case "D":
			ClienteCRUD.listVincitoriAndReset();
			break;
		case "M":
			printMenu();
			break;
		case "L":
			System.out.println("Stai per essere disconnesso dalla console...");
			break;
		default:
			System.err.println("\nERRORE: La scelta selezionata non è valida\n");
			break;
		}
	}
	
	public static void main(String[] args)
	{
		String choice = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Benvenuto nel pannello operatore di CompraEVinci!");
		printMenu();
		
		do {
			System.out.print("\nSeleziona l'operazione da effettuare: ");
			
			choice = scanner.next();
			
			
			routeOperatore(choice, scanner);
	       
		} while (!choice.equals("L"));

		System.out.println("Disconnessione avvenuta con successo!");
		scanner.close();
	}

}
