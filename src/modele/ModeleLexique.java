package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.mysql.cj.jdbc.PreparedStatement;
//import com.mysql.jdbc.ResultSetMetaData;
//import com.mysql.jdbc.Statement;
//import com.mysql.cj.jdbc.Pr;

public class ModeleLexique extends ConnexionBDD {
/*
	public static ArrayList<Lexique> affichageLexique() {
		ArrayList<Lexique> table = new ArrayList<>();
		try {
			Connection connection = startConnection();
			Statement statement = (Statement) connection.createStatement();
			String query = "SELECT * FROM lexique";
			ResultSet result = statement.executeQuery(query);
			//ResultSetMetaData resultMetaData = (ResultSetMetaData) result.getMetaData();
			while(result.next()){
				Lexique lexique = new Lexique();
				lexique.setId(result.getInt("id"));
				lexique.setMot(result.getString("mot"));
				lexique.setDefinition(result.getString("definition"));
				table.add(lexique);
			}
			result.close();
			closeConnection(connection);
		} catch (SQLException e) {
			System.err.println("Erreur " + e.getMessage());
		}
		return table;
	}

	public static boolean ajoutEntree(Lexique lexique) {
		boolean verif=false;
		try {
			Connection connection = startConnection();
			String query = "INSERT INTO lexique(mot,definition) VALUES(?,?)";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, lexique.getMot());
			statement.setString(2, lexique.getDefinition());
			int excuteInsert = statement.executeUpdate();
			verif =(excuteInsert==1);
			closeConnection(connection);
		}
		catch (SQLException e) {
			System.err.println("err "+e.getMessage());
		}
		return verif;
	}
	public static boolean readEntree(Lexique lexique) {
		boolean verif = false;
		try {
			Connection connection = startConnection();
			String query ="SELECT mot, definition FROM lexique WHERE mot=?";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, lexique.getMot());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				verif=true;
				lexique.setMot(result.getString("mot"));
				lexique.setDefinition(result.getString("definition"));
			}
			result.close();
			closeConnection(connection);

		} catch (SQLException e) {
			System.err.println("err "+e.getMessage());	
		}
		return verif;
	}
	public static boolean updateEntree(Lexique lexique,String motModifier) {
		boolean success =false;
		try {
			Connection connection = startConnection();
			String query = "UPDATE lexique SET mot =?,definition=? WHERE mot=?";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, motModifier);
			statement.setString(2, lexique.getDefinition());
			statement.setString(3, lexique.getMot());
			int executeUpdate = statement.executeUpdate();
			if(executeUpdate == 1) {
				success =true;
			}
			closeConnection(connection);

		} catch (SQLException e) {
			System.err.println("err "+e.getMessage());	
		}
		return success;
	}
	public static boolean deleteEntree(Lexique lexique) {
		boolean success=true;
		try {
			Connection connection = startConnection();
			String query = "DELETE FROM lexique WHERE mot=?";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, lexique.getMot());
			int executeUpdate = statement.executeUpdate();
			success=(executeUpdate==1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return success;
	}
	*/
}