package Vue;

import Modele.Seance;
import Modele.SeanceDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Modele.DAO;


public class InterfaceAjouter extends JFrame{
    
    //JTextField choixSemaine;
    JComboBox choixSemaine = new JComboBox();
    JComboBox choixDate = new JComboBox();
    JComboBox choixHeureDebut = new JComboBox();
    JComboBox choixHeureFin = new JComboBox();
    JComboBox choixEtat = new JComboBox();
    JComboBox choixMatiere = new JComboBox();
    JComboBox choixTypeCours = new JComboBox();
    JComboBox choixProf = new JComboBox();
    JComboBox choixGroupe = new JComboBox();
    JComboBox choixClasse = new JComboBox();
    JComboBox choixSite = new JComboBox();
    JComboBox del = new JComboBox();
    Integer[] semaine = {1, 2, 3, 4, 5};
    //String[] salle = {"Toutes les salles", "E1", "E2", "E3", "E4", "E5"};
    String[] date = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
    String[] heure_debut = {"8h00", "10h00", "13h00", "14h00", "16h00"};
    String[] heure_fin = {"9h00", "11h00", "14h00", "15h00", "17h00"};
    String[] prof = {"DJOUDI", "COUDRAY", "SEGADO", "LECOR", "MOUHALI", "CRAMBES"};
    //String[] matiere = {"JAVA", "MOOC_NANO", "ANGLAIS", "WEB", "BUSINESS", "ANATOMIE"};
    Integer[] matiere = {1, 2, 3, 4, 5};
    //String[] groupe = {"1", "2"};
    //String[] type_cours = {"TD", "AMPHI", "MOOC", "TP", "PROJET"};
    Integer[] type_cours = {1, 2, 3, 4, 5};
    String[] classe = {"P101", "P102", "P201", "P202", "P301", "P302"};
    String[] site = {"E1", "E2", "E3", "E4", "E5"};
    //String[] promo = {"2020", "2021", "2022"};
    Integer[] etat = {1, 0};

    
    public InterfaceAjouter(){
        
        
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(16,8));
        
        
        for (int i = 0; i < 17; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel SemaineRecherche = new JLabel();
        SemaineRecherche.setText("Semaine");
        this.add(SemaineRecherche);
        
        JLabel DateRecherche = new JLabel();
        DateRecherche.setText("Date");
        this.add(DateRecherche);
        
        JLabel HeureDebutRecherche = new JLabel();
        HeureDebutRecherche.setText("Heure Debut");
        this.add(HeureDebutRecherche);
        
        JLabel HeureFinRecherche = new JLabel();
        HeureFinRecherche.setText("Heure Fin");
        this.add(HeureFinRecherche);
        
        /*JLabel SalleRecherche = new JLabel();
        SalleRecherche.setText("Salle");
        this.add(SalleRecherche);*/
        
        JLabel EtatRecherche = new JLabel();
        EtatRecherche.setText("Etat");
        this.add(EtatRecherche);
        
        JLabel ProfRecherche = new JLabel();
        ProfRecherche.setText("Prof");
        this.add(ProfRecherche);
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel PhrasePourChoixCours = new JLabel();
        PhrasePourChoixCours.setText("Selectionnez un cours");
        this.add(PhrasePourChoixCours);
        
        choixSemaine = new JComboBox(semaine);
        this.add(choixSemaine);
        
        choixDate = new JComboBox(date);
        this.add(choixDate);
        
        choixHeureDebut = new JComboBox(heure_debut);
        this.add(choixHeureDebut);
        
        choixHeureFin = new JComboBox(heure_fin);
        this.add(choixHeureFin);
        
        
        /*JComboBox choixSalle = new JComboBox(salle);
        this.add(choixSalle);*/
        
        choixEtat = new JComboBox(etat);
        this.add(choixEtat);
        
        ArrayList<String> listeprof = new ArrayList<String>();

        Connection connn2 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn2 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn2.prepareStatement("SELECT Nom FROM utilisateur WHERE Droit = 3");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listeprof.add(rs.getString(1));
            }

            connn2.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn2 != null) {
                    connn2.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixProf = new JComboBox<>(listeprof.toArray());
        this.add(choixProf);
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel MatiereRecherche = new JLabel();
        MatiereRecherche.setText("Matiere");
        this.add(MatiereRecherche);
        
        JLabel TypeCoursRecherche = new JLabel();
        TypeCoursRecherche.setText("Type de cours");
        this.add(TypeCoursRecherche);
        
        JLabel GroupeRecherche = new JLabel();
        GroupeRecherche.setText("Groupe");
        this.add(GroupeRecherche);
        
        JLabel ClasseRecherche = new JLabel();
        ClasseRecherche.setText("Classe");
        this.add(ClasseRecherche);
        
        /*JLabel SiteRecherche = new JLabel();
        SiteRecherche.setText("Site");
        this.add(SiteRecherche);*/
        
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
       
        ArrayList<String> listematiere = new ArrayList<String>();

        Connection connn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn.prepareStatement("SELECT Nom FROM cours");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listematiere.add(rs.getString(1));
            }

            connn.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn != null) {
                    connn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixMatiere = new JComboBox<>(listematiere.toArray());
        this.add(choixMatiere);
        
        ArrayList<String> listetypecours = new ArrayList<String>();

        Connection connn1 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn1 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn1.prepareStatement("SELECT Nom FROM type_cours");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listetypecours.add(rs.getString(1));
            }

            connn1.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn1 != null) {
                    connn1.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixTypeCours = new JComboBox<>(listetypecours.toArray());
        this.add(choixTypeCours);
        
        ArrayList<String> listegroupe = new ArrayList<String>();
        
        Connection connn3 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn3 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn3.prepareStatement("SELECT nom FROM groupe");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listegroupe.add(rs.getString(1));
            }

            connn3.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn3 != null) {
                    connn3.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixGroupe = new JComboBox<>(listegroupe.toArray());
        this.add(choixGroupe);
        
        ArrayList<String> listeclasse = new ArrayList<String>();
        
        Connection connn4 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn4 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn4.prepareStatement("SELECT nom FROM salle");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listeclasse.add(rs.getString(1));
            }

            connn4.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn4 != null) {
                    connn4.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixClasse = new JComboBox<>(listeclasse.toArray());
        this.add(choixClasse);
        
        /*ArrayList<String> listesite = new ArrayList<String>();
        
        Connection connn5 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projetjava2020";
            String user = "root";
            String password = "";

            connn5 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn5.prepareStatement("SELECT nom FROM site");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listesite.add(rs.getString(1));
            }

            connn5.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn5 != null) {
                    connn5.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        choixSite = new JComboBox<>(listesite.toArray());
        this.add(choixSite);*/
        
        for (int i = 0; i < 12; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonAjouter = new JButton("Ajouter");
        this.add(ButtonAjouter);
        ButtonAjouter.addActionListener(new ButtonAjouterListener());
        
        JButton ButtonRetour = new JButton("Retour");
        this.add(ButtonRetour);
        ButtonRetour.addActionListener(new InterfaceAjouter.ButtonRetourListener());
        
        for (int i = 0; i < 69; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
   }
    
    private class ButtonAjouterListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
           
            //Seance seance = new Seance(Semaine, Date, HeureDebut, HeureFin, Etat, IDCours, IDType);
            
            int Semaine;
            String Date;
            String Heure_Debut;
            String Heure_Fin;
            int Etat;
            //int ID_Cours;
            //int ID_Type;
            Semaine = (int) choixSemaine.getSelectedItem();
            Date = (String) choixDate.getSelectedItem();
            Heure_Debut = (String) choixHeureDebut.getSelectedItem();
            Heure_Fin = (String) choixHeureFin.getSelectedItem();
            Etat = (int) choixEtat.getSelectedItem();
            String Cours = (String) choixMatiere.getSelectedItem();
            String Type = (String) choixTypeCours.getSelectedItem();
            
            Seance s1 = new Seance();
            String NomCours = s1.getNomCours();
            String NomTypeCours = s1.getNomTypeCours();
            String NomProf = s1.getNomProf();
            String NomSalle = s1.getNomSalle();
            String NomGroupe = s1.getNomGroupe();
            
            String Prof = (String) choixProf.getSelectedItem();
            String Groupe = (String) choixGroupe.getSelectedItem();
            String Salle = (String) choixClasse.getSelectedItem();
            
            Connection conn1 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            conn1 = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = conn1.prepareStatement("SELECT ID FROM cours WHERE Nom = '"+Cours+"'");
            ResultSet rs = stmt.executeQuery();
            
            PreparedStatement stmt1 = conn1.prepareStatement("SELECT ID FROM type_cours WHERE Nom = '"+Type+"'");
            ResultSet rs1 = stmt1.executeQuery();
            
            PreparedStatement stmt2 = conn1.prepareStatement("Select ID FROM utilisateur WHERE Nom='"+Prof+"'");
            ResultSet rs2 = stmt2.executeQuery();
            
            PreparedStatement stmt3 = conn1.prepareStatement("Select ID FROM groupe WHERE Nom='"+Groupe+"'");
            ResultSet rs3 = stmt3.executeQuery();
            
            PreparedStatement stmt4 = conn1.prepareStatement("Select ID FROM salle WHERE Nom='"+Salle+"'");
            ResultSet rs4 = stmt4.executeQuery();

            while (rs.next()&&rs1.next()&&rs2.next()&&rs3.next()&&rs4.next()){

                int ID_Cours = rs.getInt("ID");
                int ID_Type = rs1.getInt("ID");
                int ID_Prof = rs2.getInt("ID");
                int ID_Groupe = rs3.getInt("ID");
                int ID_Salle = rs4.getInt("ID");
                SeanceDAO s = new SeanceDAO();
                s.ajouterSeance(Semaine, Date, Heure_Debut, Heure_Fin, Etat, ID_Cours, ID_Type, NomCours, NomTypeCours, ID_Prof, ID_Groupe, ID_Salle, NomProf, NomSalle, NomGroupe);
            }

            conn1.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
            //ID_Cours = (int) choixMatiere.getSelectedItem();
            //ID_Type = (int) choixTypeCours.getSelectedItem();
            /*System.out.println(Semaine);
            System.out.println(Date);
            System.out.println(Heure_Debut);
            System.out.println(Heure_Fin);
            System.out.println(Etat);
            System.out.println(ID_Cours);
            System.out.println(ID_Type);*/
            
            dispose();
            new InterfaceAjouter();
            
        }
    }
    
    
    
    private class ButtonRetourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            dispose();
            new InterfaceMenuModif();
            
        }
    }
}