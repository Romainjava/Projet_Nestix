package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {

	public static Connection startConnection() {		
		String url = "jdbc:mysql://grp1.needemand.com:3306/c4commune?autoReconnect=true";
		String utilisateur = "c4grp1";
		String motDePasse = "CDAgrp134";    
		Connection co = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			co = (Connection) DriverManager.getConnection(url, utilisateur, motDePasse);
			if (!co.isClosed()) {
				System.out.println(
						"Connexion au serveur... OK"
						);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return co;	    
	}
	
	public static void closeConnection(Connection co) {
		if (co != null) {
			try {
				co.close();
			} catch (SQLException e) {
				System.err.println("Erreur fermreture: " + e.getMessage());
			}
		}
	}

}
