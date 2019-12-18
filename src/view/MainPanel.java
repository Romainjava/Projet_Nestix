package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;


import javax.swing.JPanel;

import modele.Editeur;
import modele.Etat;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel{
	
	private ArrayList<Module> moduleList = new ArrayList<>();

	public MainPanel(JPanel fPanel) {
		super();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		//Add this to Father Panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		fPanel.add(this, gbc);

		//Layout
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0, 1.0};
	    gbl.rowWeights = new double[] {1.0, 1.0, 1.0};
	    this.setLayout(gbl);

	}
	
	public DualLinkModule addPanelPersonne(int x, int y,String[] metiers) {
		DualLinkModule panel_personne = new DualLinkModule("Personne", metiers);
		this.addModule(panel_personne, x, y);
		return panel_personne;
	}
	public DualLinkModule addPanelPersonne(String[] metiers) {
		return this.addPanelPersonne(0,0, metiers);
	}
	public ImageModule addPanelImage(int x, int y) {
		ImageModule panel_image= new ImageModule();
		this.addModule(panel_image, x, y);
		return panel_image;
	}
	public ImageModule addPanelImage() {
		return this.addPanelImage(2,0);
	}
	public LinkModule addPanelGenre(int x,int y) {
		LinkModule panel_genre = new LinkModule("Genre");
		this.addModule(panel_genre, x, y);
		return panel_genre;
	}
	
	public LinkModule addPanelGenre() {
		return this.addPanelGenre(1,0);
	}
	
	public ComboListField addPanelEditeur(int x,int y) {
		MigLayout mgl = new MigLayout("", "[grow]", "[]");
		System.out.println(Editeur.getAllNom().toString());
		Editeur.lectureToutListe();
		ComboListField comboListField = new ComboListField(Editeur.getAllNom());
		comboListField.setEditable(true);
		Module panel_comboListField= new Module();
		panel_comboListField.setLayout(mgl);
		panel_comboListField.add(new LabelCustom("Editeur"),"cell 0 0,alignx right");
		panel_comboListField.add(comboListField,"cell 1 0,alignx left");
		this.addModule(panel_comboListField, x, y);
		return comboListField;
		
	}
	
	public ComboListField addPanelEditeur() {
		return addPanelEditeur(2,1);
	}
	
	public TextAreaScrollField addPanelResume(int x,int y) {
		MigLayout mgl = new MigLayout("", "[grow]", "[][grow]");
		Module panel_resume=new Module();
		panel_resume.setLayout(mgl);
		panel_resume.add(new LabelCustom("Resume"),"cell 0 0,alignx center");
		TextAreaScrollField textAreaScrollField = new TextAreaScrollField(5, 10);
		panel_resume.add(textAreaScrollField,"cell 0 1,growx,growy");
		this.addModule(panel_resume, x, y, 2, 2);
		return textAreaScrollField;
	}
	
	public TextAreaScrollField addPanelResume() {
		return this.addPanelResume(0,1);
	}
	
	public ComboListField addPanelEtat(int x,int y) {
		ComboListField comboListField = new ComboListField(Etat.lectureTout());
		Module panel_comboListField= new Module();
		comboListField.setSelectedIndex(1);
		panel_comboListField.add(new LabelCustom("Etat"));
		panel_comboListField.add(comboListField);
		this.addModule(panel_comboListField, x, y);
		return comboListField;
	}
	
	public ComboListField addPanelEtat() {
		return this.addPanelEtat(2,1);
	}
	
	public GridBagConstraints addElement(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		return gbc;
	}
	public void addModule(Module pModule, int x, int y) {
		this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(pModule, gbc);	
	}
	public void addModule(Module pModule, int x, int y, int width, int height) {
		this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(pModule, gbc);	
	}
	
}
