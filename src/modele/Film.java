package modele;

public class Film extends Media {
	
	private String resume_film;
	private int duree_film;

	public String getResume_film() {
		return resume_film;
	}

	public void setResume_film(String resume_film) {
		this.resume_film = resume_film;
	}

	public int getDuree_film() {
		return duree_film;
	}

	public void setDuree_film(int duree_film) {
		this.duree_film = duree_film;
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lecture() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suppression() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Film [resume_film=" + resume_film + ", duree_film=" + duree_film + ", toString()=" + super.toString()
				+ "]";
	}

}
