package com.mycompany.progetto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrdineCRUD
{
		private static final String SQL_LIST = "SELECT * FROM Ordine ORDER BY data DESC";
		private static final String SQL_LIST_BY_CLIENTE = "SELECT * FROM Ordine WHERE email_cliente=? ORDER BY data DESC";
                private static final String SQL_INSERT = "INSERT INTO Ordine VALUES (?, ?, ?, ?, ?, ?)";
                private static final String SQL_GET_LAST_ORDER = "SELECT MAX(O.id) AS largestID FROM Ordine O";
                private static final String SQL_GET_NUM_ORDERS = "SELECT COUNT(O.id) AS nbOfOrders FROM Ordine O WHERE O.email_cliente = ? AND O.data = ?";
                private static final String SQL_UPDATE_SPESA_CLIENTE = "UPDATE Cliente C SET C.spesaMensile = ? WHERE C.email = ?";

		
		/* TUTTI */
		public static void list()
		{
			try (
					Connection c = DriverManager.getConnection(CEV.URL);
					PreparedStatement stmt = c.prepareStatement(SQL_LIST);
					ResultSet rs = stmt.executeQuery();
				)
			{
				
				print(rs);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/* ASSOCIATO A CLIENTE */
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
                
                /* INSERIMENTO */
                public static void insert(String email, String id_prodotto, int qty)
                {
                    int largestID;
                    float prezzoProdotto, spesaPreOrdine;
                    
                    try (
					Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
					PreparedStatement stmt = c.prepareStatement(SQL_GET_LAST_ORDER);
                                        PreparedStatement stmt2 = c.prepareStatement(SQL_INSERT);
                                        PreparedStatement stmt_updatespesa = c.prepareStatement(SQL_UPDATE_SPESA_CLIENTE);
				)
			{
                                        if (ClienteCRUD.isEmailUsed(email) && ProdottoCRUD.isIDUsed(id_prodotto)) {
                                            c.setAutoCommit(false);
                                            
                                            ResultSet rs = stmt.executeQuery();
                                            rs.next();
                                            largestID = rs.getInt("largestID");
                                            rs.close();
                                            
                                            stmt2.setInt(1, largestID + 1);
                                            stmt2.setString(2, email);
                                            stmt2.setString(3, id_prodotto);
                                            prezzoProdotto = ProdottoCRUD.getPriceByID(id_prodotto);
                                            stmt2.setFloat(4, prezzoProdotto);
                                            stmt2.setInt(5, qty);
                                            stmt2.setDate(6, Date.valueOf(LocalDate.now()));
                                            stmt2.executeUpdate();                                            
                                                                                        
                                            spesaPreOrdine = ClienteCRUD.getSpesaMensile(email);
                                            stmt_updatespesa.setFloat(1, spesaPreOrdine + prezzoProdotto*qty);
                                            stmt_updatespesa.setString(2, email);
                                            stmt_updatespesa.executeUpdate();
                                            
                                            System.out.println("\nL'ordine è stato aggiunto al cliente " + email + "\n");
                                            System.out.println("\nLa spesa mensile del cliente " + email + " è stata aggiornata\n");
                                            
                                            c.commit();
                                        } else {
                                            System.err.println("\nERRORE: Email cliente o id prodotto non presente nel database\n");
                                        }
                                        
			} catch (SQLException e) {
				e.printStackTrace();
			}
                }
                
                /* OTTIENI NUMERO DI ORDINI PER CLIENTE */
                private static int getNumOrders(String email)
                {
                    int nbOfOrders = 0;
                    
                    try (
					Connection c = DriverManager.getConnection(com.mycompany.progetto.CEV.URL);
					PreparedStatement stmt = c.prepareStatement(SQL_GET_NUM_ORDERS);
				)
			{
                                        stmt.setString(1, email);
                                        stmt.setDate(2, Date.valueOf(LocalDate.now()));
                                        ResultSet rs = stmt.executeQuery();
                                        rs.next();
                                        nbOfOrders = rs.getInt("nbOfOrders");
                                        
			} catch (SQLException e) {
				e.printStackTrace();
			}
                    
                    return nbOfOrders;
                }
		
		
		/* STAMPA */
		private static void print(ResultSet rs) throws SQLException
		{
			System.out.println();
			while(rs.next()) {
				System.out.print(rs.getString("id"));
				System.out.print("\t\t");
				System.out.print(rs.getString("email_cliente"));
				System.out.print("\t\t");
				System.out.print(rs.getString("id_prodotto"));
				System.out.print("\t\t");
				System.out.print(rs.getString("qty") + " pz");
				System.out.print("\t\t");
				System.out.print(rs.getString("prezzo") + " EUR");
				System.out.print("\t\t");
				System.out.print(rs.getString("data"));
				System.out.println();
			}
			System.out.println();
		}
}
