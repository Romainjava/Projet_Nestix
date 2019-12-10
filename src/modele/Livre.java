package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Livre extends Media {
	private String resume_livre;
	private int tome_livre;
	private int ISBN;
	
	
	public String[] toRowData() {
		String[] data = {this.titre_media, this.ISBN + "", this.annee_sortie_media};
		return data;
	}
	
	public String[] toHeaderData() {
		String[] data = {"Titre","ISBN", "Genre", "Annee"};
		return data;
	}
	
	public String getResume_livre() {
		return resume_livre;
	}

	public void setResume_livre(String resume_livre) {
		this.resume_livre = resume_livre;
	}

	public int getTome_livre() {
		return tome_livre;
	}

	public void setTome_livre(int tome_livre) {
		this.tome_livre = tome_livre;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Livre [resume_livre=" + resume_livre + ", tome_livre=" + tome_livre + ", ISBN=" + ISBN + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lireUn(int id) {
		boolean success = true;
		try {
			String query = "SELECT * FROM nestix_media JOIN nestix_livre ON id_media = livre_id WHERE id_media = " + id;
			Statement state = ConnexionBDD.startConnection().createStatement();
			ResultSet result = state.executeQuery(query);
			
			ResultSetMetaData resultMeta = result.getMetaData();
		    System.out.println("\n**********************************");
		    for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
		      System.out.print(resultMeta.getColumnName(i).toUpperCase() + " *");
		    }
		    System.out.println("\n**********************************");
		    while(result.next()){         
		      for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
		    	  if(result.getObject(i) != null) {
		    		  System.out.print(result.getObject(i).toString() + "\t |");
		    	  }else {
		    		  System.out.print("NULL" + "\t |");
		    	  }
		      }
		      System.out.println("\n---------------------------------");
		    }
		    result.absolute(1);
		    if(result.getObject(12) != null) {
		    	System.out.println(result.getObject(12).toString());
		    	setISBN(result.getInt(12));
		    }else {
		    	System.out.println("NULL");
		    	setISBN(result.getInt(12));
		    }
		    
		    System.out.println(getISBN());
	
		    result.close();
		    state.close();
			
		} catch (Exception e) {
			System.out.println("erreur");
			success = false;
		}
		return success;
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static void main(String[] args) {
		Livre livre = new Livre();
		
		livre.lireUn(1);
		
//		ConnexionBDD.startConnection();
//		musique.setTitre_media("test");
//		Genre genre = new Genre();
//		genre.setNom_genre("rock");
//		Genre genre2 = new Genre();
//		genre2.setNom_genre("pop");
//		musique.addGenre(genre);
//		musique.addGenre(genre2);
//		ConnexionBDD.startConnection();
//		musique.setAnnee_sortie_media("1060");
//		for (String iterable_element : musique.toRowData()) {
//			System.out.println(iterable_element);
//		}
	}

}
