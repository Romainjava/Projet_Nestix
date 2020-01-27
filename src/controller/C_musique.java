package controller;

//-- imports swing
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

//-- imports awt
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//-- imports util
import java.util.ArrayList;

//-- imports internes modeles
import modele.Artiste;
import modele.Genre;
import modele.I_recherche;
import modele.Metier;
import modele.Musique;

//-- imports internes vues
import view.AsidePanel;
import view.ComboListField;
import view.DualLinkModule;
import view.FooterPanel;
import view.HeaderPanel;
import view.LinkModule;
import view.MainPanel;
import view.PlaceholderTextField;


/**
 * - Classe Controleur de la vue aux données de Musiques.
 *
 * @author Kévin
 */
public class C_musique {


    //-- Atributs de la classe C_musique --\\
    //--
    private Musique musique = new Musique();
    private JPanel musiques_panel;
    private ArrayList<I_recherche> musiques = new ArrayList<>();
    private JTable musique_results_table;
    private ArrayList<PlaceholderTextField> musique_titre_textfield;
    private String[] header = {"Titre", "Duree(en secondes)", "Album", "Univers", "Annee de sortie"};
    private HeaderPanel musique_header;
    private AsidePanel musiques_aside;
    private ComboListField musique_module_etat;
    private DualLinkModule musique_module_personne;
    private LinkModule musique_module_genre;


    /**
     * - Partage le tableau des musiques à la classe interne.
     *
     * @return le tableau des musiques
     */
    private JTable getMusique_results_table() {
        return musique_results_table;
    }


    /**
     * - Constructeur du controller des musiques.
     * Construit le header, le main, le footer et l'aside de la page.
     *
     * @param musiques_panel la page musique.
     */
    public C_musique(JPanel musiques_panel) {
        this.musiques_panel = musiques_panel;
        ajouteHeader();
        ajouteTab();
        ajoutMainPanel();
        footerPanel();
    }


    /**
     * - Création de la partie header de la page musique.
     * définit la place que vont prendre les éléments ainsi que leurs labels.
     * définit le contenu des placeholders.
     */
    private void ajouteHeader() {
        double[] elmsSize = {1.0, 1.0, 1.0, 1.0, 1.0};
        musique_header = new HeaderPanel(this.musiques_panel, "Cet onglet permet de renseigner des musiques", header,
                elmsSize);
        ArrayList<PlaceholderTextField> liste = musique_header.getJtextArrray();
        this.musique_titre_textfield = liste;
        PlaceholderTextField musique_duree_textfield = liste.get(1);
        musique_duree_textfield.setPlaceholder("Durée en secondes");
    }


    /**
     * - Création de la partie main de la page musique.
     * Ajout des modules personnes, genre, etat et image.
     * Gère la liaison des artistes et des genres avec les musiques.
     */
    private void ajoutMainPanel() {
        MainPanel musique_main = new MainPanel(this.musiques_panel);
        this.musique_module_personne = musique_main.addPanelPersonne(new String[]{"interprete", "compositeur"});
        musique_main.addPanelImage();
        musique_module_genre = musique_main.addPanelGenre();
        musique_module_etat = musique_main.addPanelEtat();


        //-- Lie un artiste et une musique lors de l'appui sur bouton '+' --\\
        //--
        musique_module_personne.getMore_btn().addActionListener(e -> {
            if (!musique_module_personne.empty()) {
                if (musique.getId() != 0) {
                    musique_module_personne.addTextListField();
                    Artiste artiste = new Artiste();
                    Metier metier = new Metier();
                    artiste.creationRapide(musique_module_personne.getText_list()
                            .get(musique_module_personne.getText_list().size() - 1));
                    metier.setInfo(musique_module_personne.getCombo_list()
                            .get(musique_module_personne.getCombo_list().size() - 1));
                    artiste.setMetiers_artiste(metier);
                    musique.addArtiste(artiste);
                    musique.ajoutLiaisonArtisteMetierMedia();
                    actualiseTab();
                } else {
                    JOptionPane.showMessageDialog(musique_main,
                            "Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un artiste");
                }
            }
        });


        //-- Délie un artiste et une musique lors de l'appui sur bouton '-' --\\
        //--
        musique_module_personne.getLess_btn().addActionListener(e -> {
            if (musique_module_personne.getContent_list().getSelectedIndices().length > 0) {
                if (musique.getId() != 0) {
                    musique.supprimeLiaisonArtisteMetierMedia();
                    actualiseTab();
                } else {
                    JOptionPane.showMessageDialog(musique_main,
                            "Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un artiste");
                }
            } else {
                JOptionPane.showMessageDialog(musique_main, "Veuillez selectionner un element dans la liste ");
            }
        });


        //-- Lie un genre et une musique lors de l'appuie sur le bouton '+' --\\
        //--
        musique_module_genre.getMore_btn().addActionListener(e -> {
            if (!musique_module_genre.empty()) {
                if (musique.getId() != 0) {
                    musique_module_genre.addTextListField();
                    Genre genre = new Genre();
                    genre.setInfo(musique_module_genre.getText_list()
                            .get(musique_module_genre.getText_list().size() - 1));
                    musique.addGenre(genre);
                    musique.ajoutLiasonMediaGenre();
                    actualiseTab();
                } else {
                    JOptionPane.showMessageDialog(musique_main,
                            "Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un genre");
                }
            }
        });


        //-- Delie un genre et une musique lors de l'appuie sur le bouton '-' --\\
        //--
        musique_module_genre.getLess_btn().addActionListener(e -> {
            if (musique_module_genre.getContent_list().getSelectedIndices().length > 0) {
                if (musique.getId() != 0) {
                    musique.supprimeLiasonMediaGenre();
                    actualiseTab();
                } else {
                    JOptionPane.showMessageDialog(musique_main,
                            "Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un genre");
                }
            } else {
                JOptionPane.showMessageDialog(musique_main, "Veuillez selectionner un element dans la liste ");
            }
        });
    }


    /**
     * - Création de la partie aside de la page musique.
     * Ajoute au tableau les bon noms de colonnes.
     * Récupère les données des musiques dans la BDD.
     * Ajoute de l'évènement du click sur le tableau pour récupérer les données d'une musique.
     */
    private void ajouteTab() {
        musiques_aside = new AsidePanel(this.musiques_panel);
        musiques_aside.setEntetes(musique.toHeaderData());
        musiques = musique.lectureTout(50);
        musiques_aside.setDonnees(musiques);
        this.musique_results_table = musiques_aside.getTable_result();
        musique_results_table.addMouseListener(new MouseAdapterTableau(this));

    }


    /**
     * - Création de la partie footer de la page musique.
     * Définit le nom et la taille des boutons dans le footer.
     * Définit le comportement des boutons.
     */
    private void footerPanel() {
        String[] textBouton = {"Creer", "Modifier", "Supprimer", "Reset"};
        double[] elmsSizeFooter = {1.0, 1.0, 1.0, 1.0};
        FooterPanel musique_footer_panel = new FooterPanel(this.musiques_panel, textBouton, elmsSizeFooter);


        //-- Bouton créer --\\
        //--
        musique_footer_panel.getBoutonTab().get(0).addActionListener(e -> {
            if (verifChamp()) {
                //-- Si la musique est bien crée.
                if (musique.creation()) {
                    JOptionPane.showMessageDialog(musiques_panel, "Insertion faites", "Validation",
                            JOptionPane.INFORMATION_MESSAGE);
                    actualiseTab();
                }
            }
        });


        //-- Bouton Modifier --\\
        //--
        musique_footer_panel.getBoutonTab().get(1).addActionListener(e -> {
            if (verifChamp()) {
                //-- Si une ligne à bien été modifié.
                if (musique.modification()) {
                    JOptionPane.showMessageDialog(musiques_panel, "Modification faites", "Modifie",
                            JOptionPane.INFORMATION_MESSAGE);
                    actualiseTab();
                } else {
                    JOptionPane.showMessageDialog(musiques_panel, "Erreur lors de la modification",
                            "Echec insertion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //-- Bouton supprimer --\\
        //--
        musique_footer_panel.getBoutonTab().get(2).addActionListener(e -> {
            //-- Si L'id de la musique en cour de traitement n'est pas = à 0 les champs valides.
            if (musique.getId() != 0 && verifChamp()) {
                musique.suppression(musique.getId());
                actualiseTab();
            }
        });


		//-- Bouton reset --\\
		//--
		musique_footer_panel.getBoutonTab().get(3).addActionListener(e -> {
			musique = new Musique();
			for (PlaceholderTextField text : musique_titre_textfield) {
				text.setText("");
			}
			musique_module_etat.setSelectedIndex(1);
			musique_module_genre.resetTextListField();
			musique_module_personne.resetTextListField();
		});
    }


    /**
     * - Methode de vérifications des champs de textes.
     * Vérifie que les champs du header soit correctements remplis.
     * Ajoute les données des champs du header ainsi que l'état et le genre dans l'objet musique.
     *
     * @return false si un des champs n'a pas bien été rempli.
     */
    private boolean verifChamp() {
        boolean success = true;

        //-- Si le champ titre est vide
        if (musique_titre_textfield.get(0).getText().equals("")) {
            success = false;
            JOptionPane.showMessageDialog(musiques_panel, "Veuillez saisir un titre");
        } else {
            musique.setOeuvre(musique_titre_textfield.get(0).getText().toLowerCase());
            try {
                //-- Si la durée de la musique est supérieur à 0
                if (musique_titre_textfield.get(1).getText().length() > 0) {
                    musique.setDuree_musique(Integer.parseInt(musique_titre_textfield.get(1).getText()));
                }
            } catch (NumberFormatException e2) {
                success = false;
                JOptionPane.showMessageDialog(musiques_panel,
                        "la dure de la musique ne doit comporter que des chiffres", "Echec", JOptionPane.ERROR_MESSAGE);
            }
            try {
                //-- Si L'année de sortie contien bien 4 chifres et est supérieur = 1900
                if (musique_titre_textfield.get(4).getText().toLowerCase().length() == 4
                        && Integer.parseInt(musique_titre_textfield.get(4).getText().toLowerCase()) > 1900) {
                    musique.setAnnee_sortie_media(musique_titre_textfield.get(4).getText().toLowerCase());
                } else {
                    success = false;
                    JOptionPane.showMessageDialog(musiques_panel, "Annee non valide", "Echec",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e2) {
                success = false;
                JOptionPane.showMessageDialog(musiques_panel, "l'annee de sortie ne doit comporter que des chiffres",
                        "Echec", JOptionPane.ERROR_MESSAGE);
            }
            musique.setAlbum(musique_titre_textfield.get(2).getText().toLowerCase());
            musique.setUnivers(musique_titre_textfield.get(3).getText().toLowerCase());
            musique.setEtat(musique_module_etat.getSelectedIndex() + 1);
            for (int i = 0; i < musique_module_genre.getText_list().size(); i++) {
                Genre genre = new Genre();
                genre.setInfo(musique_module_genre.getText_list().get(i));
                musique.addGenre(genre);
            }
        }
        return success;
    }


    /**
     * - Rafraichie le tableau de données.
     */
    public void actualiseTab() {
        musiques = musique.lectureTout(50);
        musiques_aside.setDonnees(musiques);
    }


    /**
     * - Rempli les champs avec les données de l'élément cliqué dans le tableau de données.
     * Ajouter les données des champs du header.
     * puis ceux des personnes, des genres et l'état.
     */
    private void actualiseMusique() {
        //-- header
        this.musique_header.autoCompleteFormHeader(musique.toRowDataForm());


        //-- personne
        ArrayList<String> tPersonneData = new ArrayList<>();
        ArrayList<String> tPersonneDataMetier = new ArrayList<>();
        for (int i = 0; i < musique.getArtistes().size(); i++) {
            for (int j = 0; j < musique.getArtistes().get(i).getMetiers_artiste().size(); j++) {
                tPersonneData.add(musique.getArtistes().get(i).getSurnom_artiste());
                tPersonneDataMetier.add(musique.getArtistes().get(i).getMetiers_artiste().get(j).getNom());
            }
        }
        this.musique_module_personne.setData(tPersonneData.toArray(new String[0]),
                tPersonneDataMetier.toArray(new String[0]));


        //-- genre
        String[] tGenreData = new String[musique.getGenres().size()];
        for (int i = 0; i < tGenreData.length; i++) {
            tGenreData[i] = musique.getGenres().get(i).getNom();
        }
        this.musique_module_genre.setData(tGenreData);


        //-- etat
        this.musique_module_etat.setSelectedIndex(musique.getEtat().getId() - 1);
    }


    /**
     * - Classe interne instancié dans le controleur au moment de la création de l'aside.
     *
     * @author Romain
     */
    class MouseAdapterTableau extends MouseAdapter {

        C_musique controller;

        MouseAdapterTableau(C_musique controller) {
            this.controller = controller;
        }

        /**
         * - Récupère la position y de la souris dans le tableau de données.
         * Récupère dans l'objet musique les données de la ligne sélectionné.
         * Actualise les données de la page avec celles de l'object sélectionné.
         *
         * @param e Gestion de l'évènement du clique de la souris.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = this.controller.getMusique_results_table().rowAtPoint(e.getPoint());
            musique.lireUn(musiques.get(row).getId());
            this.controller.actualiseMusique();
        }
    }
}
