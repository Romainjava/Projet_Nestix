package view;

//-- imports swing
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

//-- imports awt
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

//-- imports internes Controllers
import controller.C_Livre;
import controller.C_artiste;
import controller.C_film;
import controller.C_musique;

//-- imports internes Modèles
import modele.Etat;

public class MainApp {

    private JFrame frame;

    /**
     * Lancement de l'application.
     * Permet l'affichage de la fenètre swing.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainApp window = new MainApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /*Remplacement par une fonction lambda

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainApp window = new MainApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Constructeur de l'application.
     * lance la methose d'initialisation
     */
    private MainApp() {
        initialize();
    }

    /**
     * Initialisation de l'application.
     * Affiche le pannel à onglet, ainsi que tout le contenu de ces onglets.
     * Ajoute également les états des media à l'objet Etat.
     * Gestion du rafraichissement des tableaux au changement d'onglet.
     */
    @SuppressWarnings("deprecation")
    private void initialize() {

        //-- propriétées de la fenêtre. --\\
        //--
        frame = new JFrame();
        //-- UI nimbus
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setBounds(100, 0, 1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //-- Panel de connexion (en cours). --\\
        //--
        //ConnexionPanel connexionPanel = new ConnexionPanel();
        //frame.getContentPane().add(connexionPanel);


        //-- On ajouter la liste des Etats de media dans un objet static. --\\
        //--
        Etat.setListe_etat(Etat.lectureToutListe());


        //-- On ajoute et paramètre le panel à onglets. --\\
        //--
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        frame.getContentPane().add(tabbedPane);
        frame.setLocationRelativeTo(null);


        //-- Paramétrage du pannel des livres --\\
        //--
        JPanel livres_panel = new JPanel();
        GridBagLayout gbl_livres_panel = new GridBagLayout();
        gbl_livres_panel.columnWeights = new double[]{1.0, 3.0};
        gbl_livres_panel.rowWeights = new double[]{1.0, 3.5, 0.5};
        livres_panel.setLayout(gbl_livres_panel);
        tabbedPane.addTab("Livres", null, livres_panel, null);
        //-- Construction du panel
        C_Livre livres_controler_panel = new C_Livre(livres_panel);


        //-- Paramétrage du pannel des films --\\
        //--
        JPanel films_panel = new JPanel();
        GridBagLayout gbl_films_panel = new GridBagLayout();
        gbl_films_panel.columnWeights = new double[]{1.0, 3.0};
        gbl_films_panel.rowWeights = new double[]{1.0, 3.5, 0.5};
        films_panel.setLayout(gbl_films_panel);
        tabbedPane.addTab("Films", null, films_panel, null);
        //-- Construction du panel
        C_film film_controler_panel = new C_film(films_panel);


        //-- Paramétrage du pannel des musiques --\\
        //--
        JPanel musiques_panel = new JPanel();
        GridBagLayout gbl_musique_panel = new GridBagLayout();
        gbl_musique_panel.columnWeights = new double[]{1.0, 3.0};
        gbl_musique_panel.rowWeights = new double[]{1.0, 3.5, 0.5};
        musiques_panel.setLayout(gbl_musique_panel);
        tabbedPane.addTab("Musique", null, musiques_panel, null);
        //-- Construction du panel
        C_musique musique_controler_panel = new C_musique(musiques_panel);


        //-- Paramétrage du pannel des artistes --\\
        //--
        JPanel artistes_panel = new JPanel();
        GridBagLayout gbl_artistes_panel = new GridBagLayout();
        gbl_artistes_panel.columnWeights = new double[]{1.0, 3.0};
        gbl_artistes_panel.rowWeights = new double[]{1.0, 3.5, 0.5};
        artistes_panel.setLayout(gbl_artistes_panel);
        tabbedPane.addTab("Artistes", null, artistes_panel, null);
        //-- Construction du panel
        C_artiste artiste_controler_panel = new C_artiste(artistes_panel);


        //-- Paramétrage du pannel à valider (en cour) --\\
        //--
        JPanel a_valider_panel = new JPanel();
        tabbedPane.addTab("A valider", null, a_valider_panel, null);
        a_valider_panel.setLayout(new GridLayout(1, 0, 0, 0));


        //-- Paramétrage du pannel de netoyage (en cour) --\\
        //--
        JPanel netoyagebdd_panel = new JPanel();
        tabbedPane.addTab("Nettoyage BDD", null, netoyagebdd_panel, null);
        netoyagebdd_panel.setLayout(new GridLayout(1, 0, 0, 0));



        //-- Permet d'actualiser le tableau sur le click d'un onglet --\\
        //--
        tabbedPane.addChangeListener(e -> {
            switch (tabbedPane.getSelectedIndex()) {
                case 0:
                    livres_controler_panel.actualiseTab();
                    break;
                case 1:
                    film_controler_panel.actualiseTab();
                    break;
                case 2:
                    musique_controler_panel.actualiseTab();
                    break;
                case 3:
                    artiste_controler_panel.actualiseTab();
                    break;
                // TODO A VALIDER ET NETTOYAGE BDD QUAND CREE
                default:
                    System.out.println("Erreur dans l'event addChangeListener MainApp");
                    break;
            }
        });
        /* proposition de changement par rapport à ça

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                switch (tabbedPane.getSelectedIndex()) {
                    case 0:
                        livres_controler_panel.actualiseTab();
                        break;
                    case 1:
                        film_controler_panel.actualiseTab();
                        break;
                    case 2:
                        musique_controler_panel.actualiseTab();
                        break;
                    case 3:
                        artiste_controler_panel.actualiseTab();
                        break;
                    // TODO A VALIDER ET NETTOYAGE BDD QUAND CREE
                    default:
                        System.out.println("Erreur dans l'event addChangeListener MainApp");
                        break;
                }
            }
        });*/
    }
}
