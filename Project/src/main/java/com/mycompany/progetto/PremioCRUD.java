package com.mycompany.progetto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PremioCRUD
{
	private static final String SQL_LIST = "SELECT * FROM Premio ORDER BY fasciaSpesa ASC";
        private static final String SQL_GET_PREMIO_BY_SPESA = "SELECT P.nome FROM Premio P WHERE P.fasciaSpesa <= ?";
	
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
        
        public static String getNameBySpesa(float spesa)
        {
            String nomePremio = null;
            
            try (
				Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
				PreparedStatement stmt = c.prepareStatement(SQL_GET_PREMIO_BY_SPESA);
			)
		{

                        stmt.setFloat(1, spesa);
                        ResultSet rs = stmt.executeQuery();
                        rs.last();
                        nomePremio = rs.getString("nome");
                        
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return nomePremio;
        }
	
	/* STAMPA */
	private static void print(ResultSet rs) throws SQLException
	{
		System.out.println();
		while(rs.next()) {
			System.out.print(rs.getString("nome"));
			System.out.print("\t\t");
			System.out.print(rs.getString("fasciaSpesa"));
			System.out.println();
		}
		System.out.println();
	}
}
