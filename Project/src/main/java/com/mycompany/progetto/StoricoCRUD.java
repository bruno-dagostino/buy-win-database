package com.mycompany.progetto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StoricoCRUD
{
	private static final String SQL_LIST = "SELECT * FROM Storico ORDER BY data DESC";
	
	private static final String SQL_LIST_BY_DATA = "SELECT * FROM Storico WHERE data = ?";
        
        private static final String SQL_INSERT = "INSERT INTO Storico VALUES (?, ?, ?)";
        
        private static final String SQL_CHECK_ELIGIBILITY = "SELECT S.email_cliente FROM Storico S WHERE S.email_cliente = ?";

	
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
	
	/* PER DATA */
	public static void listByData(String data)
	{
		try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_LIST_BY_DATA);

			)
		{
			stmt.setString(1, data);
			
			ResultSet rs = stmt.executeQuery();
			
			print(rs);
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        /* INSERIMENTO */
        public static void insert(String email, String nomePremio)
        {
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_INSERT);

			)
		{
                        if (isClienteEligible(email)) {
                            stmt.setDate(1, Date.valueOf(LocalDate.now()));
                            stmt.setString(2, email);
                            stmt.setString(3, nomePremio);
                            stmt.executeUpdate();
                            System.out.println("\nIl cliente " + email + " ha vinto il premio " + nomePremio + "\n");
                        } else {
                            System.out.println("\nIl cliente ha già vinto un premio nel mese corrente\n");
                        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        }
	
        /* CONTROLLO VITTORIA */
        private static boolean isClienteEligible(String email)
        {
            boolean flag = true;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_CHECK_ELIGIBILITY);

			)
		{
                        stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
                        if (rs.next())
                            flag = false;
                        
                        rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return flag;
        }
	
	/* STAMPA */
	private static void print(ResultSet rs) throws SQLException
	{
		System.out.println();
		
		if (rs.next())
			rs.beforeFirst();
		else
			return;
		String oldData = "";
		
		while(rs.next()) {
			if (!oldData.equals(rs.getString("data"))) {
				System.out.println("-----------");
				System.out.println("|" + rs.getString("data") + "|");
				System.out.println("-----------");
			}
			oldData = rs.getString("data");
			System.out.print(rs.getString("email_cliente"));
			System.out.print("\t\t");
			System.out.print(rs.getString("nomePremio"));
			System.out.println();
		}
		System.out.println();
	}
}
