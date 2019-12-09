package modele;

public class Livre extends Media {
	private String resume_livre;
	private int tome_livre;
	private int ISBN;
	

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
		return "Livre [resume_livre=" + resume_livre + ", tome_livre=" + tome_livre + ", ISBN=" + ISBN + ", toString()="
				+ super.toString() + "]";
	}
}
