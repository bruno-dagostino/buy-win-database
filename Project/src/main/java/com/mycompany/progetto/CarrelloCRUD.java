package com.mycompany.progetto;

import java.sql.*;

public class CarrelloCRUD
{
	private static final String SQL_LIST = "SELECT * FROM Carrello";
	private static final String SQL_LIST_BY_CLIENTE = "SELECT * FROM Carrello WHERE email_cliente=?";

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
	
	/* ASSOCIATO A UTENTE */
	public static void listByCliente(String email)
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST_BY_CLIENTE);
			)
		{
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			print(rs);
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* STAMPA */
	private static void print(ResultSet rs) throws SQLException
	{
		System.out.println();
		while(rs.next()) {
			System.out.print(rs.getString("email_cliente"));
			System.out.print("\t\t");
			System.out.print(rs.getString("id_prodotto"));
			System.out.print("\t\t");
			System.out.print(rs.getString("qty") + " pz");
			System.out.println();
		}
		System.out.println();
	}
}