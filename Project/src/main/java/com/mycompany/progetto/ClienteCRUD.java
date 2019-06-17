package com.mycompany.progetto;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ClienteCRUD
{

	private static final String SQL_LIST = "SELECT * FROM Cliente";

	private static final String SQL_LIST_CLASSIFICA = "SELECT * FROM ClassificaClientiV";
	
	private static final String SQL_GET_POS = "SELECT COUNT(*) AS pos FROM ClassificaClientiV C WHERE C.spesaMensile >= (SELECT C2.spesaMensile FROM Cliente C2 WHERE email = ?);";
	
	private static final String SQL_LIST_VINCITORI = SQL_LIST_CLASSIFICA + " LIMIT 100";
	
	private static final String SQL_UPDATE_MENSILE = "UPDATE Cliente SET spesaMensile = 0.00";
	
	private static final String SQL_GET_PREMIO = "SELECT * FROM ClassificaClientiV WHERE email = ?";
        
        private static final String SQL_INSERT = "INSERT INTO Cliente VALUES (?, '$2a$15$EB4nvQIhA6hccngRDwSJSeL7iPeRWNlD4H5oPPgKPUOyO0CfVkFoW', 'Nome', 'Cognome', 'Indirizzo', '0000000000', 0.00)";
        
        private static final String SQL_GET_EMAIL = "SELECT C.email FROM Cliente C WHERE C.email = ?";
        
        private static final String SQL_GET_SPESA_MENSILE = "SELECT C.spesaMensile FROM Cliente C WHERE C.email = ?";
        
        private static final String SQL_UPDATE_SPESA_MENSILE = "UPDATE Cliente C SET spesaMensile = ? WHERE C.email = ?";

	
	/* TUTTI */
	public static void list()
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST);
				ResultSet rs = stmt.executeQuery();
			)
		{

			print(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* CLASSIFICA */
	public static void listClassifica()
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST_CLASSIFICA);
				ResultSet rs = stmt.executeQuery();
			)
		{
			print(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* VINCITORI */
	public static void listVincitori()
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST_VINCITORI);
				ResultSet rs = stmt.executeQuery();
			)
		{

			print(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* POSIZIONE */
	public static void printPosition(String email)
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_POS);
			)
		{
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if (rs.getInt("pos") != 0) {
				System.out.print("Il cliente è in posizione " + rs.getInt("pos"));
				System.out.println(" e ha come premio: " + getPremio(email));
			}
			else
				System.out.println("Il cliente non fa parte della classifica");
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* VINCITORI E RESET */
	public static void listVincitoriAndReset()
	{
		listVincitori();
		ZonedDateTime now = Instant.now().atZone(ZoneId.of("Europe/Rome"));
		
		/* TODO -> DONE: BUG: QUESTIONE ANNO -> SE E SOLO SE SONO UGUALI, MINORI POSSONO ESSERE */
		if (now.getMonthValue() == now.plusDays(1).getMonthValue()) {
			System.err.println("\nERRORE: Impossibile effettuare reset, il mese non è ancora finito\n");
			return;
		}
		
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_UPDATE_MENSILE);
			)
		{
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* OTTIENI PREMIO ASSOCIATO AL CLIENTE */
	private static String getPremio(String email)
	{
		String premio = "";
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_PREMIO);

			)
		{
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			premio = rs.getString("nomePremio");
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return premio;
	}
        
        /* CONTROLLA SE IL CLIENTE ESISTE */
        public static boolean isEmailUsed(String email)
        {
            boolean flag = false;
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_EMAIL);
                    
			)
		{	
                        stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
                        if (rs.next())
                            flag = true;
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return flag;
        }
        
        /* INSERIMENTO */
        public static void insert(String email)
        {
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
                                PreparedStatement stmt = c.prepareStatement(SQL_INSERT);
                    
			)
		{	
                        if (isEmailUsed(email)) {
                            System.err.println("\nERRORE: Email già in uso\n");
                        } else {
                            stmt.setString(1, email);
                            stmt.executeUpdate();
                        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        }
        
        /* OTTIENI SPESA MENSILE PER CLIENTE */
        public static float getSpesaMensile(String email)
        {
            float spesaMensile = 0;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
                                PreparedStatement stmt = c.prepareStatement(SQL_GET_SPESA_MENSILE);
                    
			)
		{	
                    
                        stmt.setString(1, email);
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        spesaMensile = rs.getFloat("spesaMensile");
                        rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return spesaMensile;
        }
        
        /* AGGIORNA SPESA MENSILE PER CLIENTE */
        public static void updateSpesaMensile(String email, float spesa)
        {
            float spesaMensile;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
                                PreparedStatement stmt2 = c.prepareStatement(SQL_UPDATE_SPESA_MENSILE);
			)
		{	
                    
                        spesaMensile = getSpesaMensile(email) + spesa;
                        stmt2.setFloat(1, spesaMensile);
                        stmt2.setString(2, email);
                        stmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        }
	
	/* STAMPA */
	private static void print(ResultSet rs) throws SQLException
	{
		System.out.println();
		while(rs.next()) {
			System.out.print(rs.getString("email"));
			System.out.print("\t\t");
			System.out.print(rs.getString("password"));
			System.out.print("\t\t");
			System.out.print(rs.getString("nome"));
			System.out.print("\t\t");
			System.out.print(rs.getString("cognome"));
			System.out.print("\t\t");
			System.out.print(rs.getString("indirizzo"));
			System.out.print("\t\t");
			System.out.print(rs.getString("telefono"));
			
			System.out.print("\t\t");
			System.out.print(rs.getString("spesaMensile") + " EUR");
			
			try {
				System.out.print("\t\t");
				System.out.print(rs.getString("nomePremio"));
			} catch (Exception ex) { }
			
			System.out.println();
		}
		System.out.println();
	}
	
}
