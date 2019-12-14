package modele;

import java.util.ArrayList;

public class Image extends Info {

	private String path_image;
	private String alt_image;
	
	public void setProp(int pId, String pNom, String pPath_image, String pAlt_image) {
		this.id = pId;
		this.nom = pNom;
		this.path_image = pPath_image;
		this.alt_image = pAlt_image;
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

	@Override
	protected String getTableName() {
		return "image";
	}

	public String getPath_image() {
		return path_image;
	}

	public void setPath_image(String path_image) {
		this.path_image = path_image;
	}

	public String getAlt_image() {
		return alt_image;
	}

	public void setAlt_image(String alt_image) {
		this.alt_image = alt_image;
	}

}
