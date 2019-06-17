package com.mycompany.progetto;

import java.sql.*;

public class ProdottoCRUD
{
	private static final String SQL_LIST = "SELECT * FROM Prodotto ORDER BY id";
	
	private static final String SQL_LIST_SCONTATI = "SELECT * FROM ProdottiScontatiV";

	private static final String SQL_MOST_ORDERED =  "SELECT * FROM ProdottiPiuVendutiV";
        
        private static final String SQL_GET_ID = "SELECT P.id FROM Prodotto P WHERE P.id = ?";
        
        private static final String SQL_GET_PRICE_BY_ID = "SELECT P.id, P.prezzo, P.prezzoScontato FROM Prodotto P WHERE P.id = ?";
	
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
	
	/* SCONTATI */
	public static void listScontati()
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST_SCONTATI);
				ResultSet rs = stmt.executeQuery();
			)
		{
			
			print(rs);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* PIU ORDINATI */
	public static void listPiuOrdinati()
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_MOST_ORDERED);
				ResultSet rs = stmt.executeQuery();
			)
		{
			
			print(rs);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        public static boolean isIDUsed(String id)
        {
            boolean flag = false;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_ID);
			)
		{
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
                        if (rs.next())
                            flag = true;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return flag;
        }
        
        public static float getPriceByID(String id)
        {
            float price = 0;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_PRICE_BY_ID);
			)
		{
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
                        rs.next();
                        if (rs.getFloat("prezzoScontato") != 0)
                            price = rs.getFloat("prezzoScontato");
                        else
                            price = rs.getFloat("prezzo");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return price;
        }
	
	/* STAMPA */
	private static void print(ResultSet rs) throws SQLException
	{
		System.out.println();
		while(rs.next()) {
			System.out.print(rs.getString("id"));
			System.out.print("\t\t");
			System.out.print(rs.getString("nome"));
			System.out.print("\t\t");
			System.out.print(rs.getString("desc"));
			System.out.print("\t\t");
			System.out.print(rs.getString("prezzo") + " EUR");
			if (rs.getString("prezzoScontato") != null) {
				System.out.print("\t\t");
				System.out.print(rs.getString("prezzoScontato") + " EUR");
				System.out.print("\t\t");
				System.out.print(rs.getString("dataInizioSconto"));
				System.out.print("\t\t");
				System.out.print(rs.getString("dataFineSconto"));
			} else {
				System.out.print("\t\t");
				System.out.print("N/A");
				System.out.print("\t\t\t");
				System.out.print("N/A");
				System.out.print("\t\t\t");
				System.out.print("N/A\t");
			}
			try {
				System.out.print("\t\t");
				System.out.print(rs.getString("nOrdinati") + " pz");
			} catch (Exception ex) { }
			
			System.out.println();
		}
		System.out.println();
	}
}
