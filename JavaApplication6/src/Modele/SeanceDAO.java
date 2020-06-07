   
       
package Modele;

import java.util.*;
import java.util.logging.*;
import java.sql.*;
import java.sql.Date;

public class SeanceDAO extends DAO<Seance>{
    
    public SeanceDAO(){
        super();
    }
   
    public void ajouterSeance(int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat, int ID_Cours, int ID_Type, String NomCours, String NomTypeCours, int ID_Prof, int ID_Groupe, int ID_Salle, String NomProf, String NomSalle, String NomGroupe){
        
        Seance seance = new Seance(Semaine, Date, Heure_Debut, Heure_Fin, Etat, ID_Cours, ID_Type, NomCours, NomTypeCours, ID_Prof, ID_Groupe, ID_Salle, NomProf, NomSalle, NomGroupe);
       
        try {
            
            //PreparedStatement stmt = connect.prepareStatement("INSERT INTO seance(Semaine, Date, Heure_Debut, Heure_Fin, Etat, ID_Cours, ID_Type) VALUES(?, '?', '?', '?', ?, ?, ?);");
            //stmt.setInt(1, Semaine);
            //stmt.setString(2, Date);
            //stmt.setString(3, Heure_Debut);
            //stmt.setString(4, Heure_Fin);
            //stmt.setInt(5, Etat);
            //stmt.setInt(6, ID_Cours);
            //stmt.setInt(7, ID_Type);
            //stmt.executeUpdate();
            this.connect.createStatement().executeUpdate("INSERT INTO seance ( Semaine, Date, Heure_Debut, Heure_Fin, Etat, ID_Cours, ID_Type) VALUES ('"+Semaine+"','"+Date+"','"+Heure_Debut+"','"+Heure_Fin+"','"+Etat+"','"+ID_Cours+"','"+ID_Type+"');");
            PreparedStatement stmt2 = connect.prepareStatement("SELECT ID FROM Seance WHERE Semaine = '"+Semaine+"' AND Date = '"+Date+"' AND Heure_Debut = '"+Heure_Debut+"' AND Heure_Fin = '"+Heure_Fin+"' AND Etat = '"+Etat+"' AND ID_Cours = '"+ID_Cours+"' AND ID_Type = '"+ID_Type+"'");
            ResultSet rs = stmt2.executeQuery();
            
            while(rs.next()){
                
                int ID_Seance = rs.getInt("ID");
                this.connect.createStatement().executeUpdate("INSERT INTO seance_enseignants ( ID_Seance, ID_Enseignant) VALUES ('"+ID_Seance+"', '"+ID_Prof+"');");
                this.connect.createStatement().executeUpdate("INSERT INTO seance_groupes ( ID_Seance, ID_Groupe) VALUES ('"+ID_Seance+"', '"+ID_Groupe+"');");
                this.connect.createStatement().executeUpdate("INSERT INTO seance_salles ( ID_Seance, ID_Salle) VALUES ('"+ID_Seance+"', '"+ID_Salle+"');");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void suppSeance(int ID){
        
        //Seance seance = new Seance();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM seance WHERE ID = ?");
            PreparedStatement stmt1 = connect.prepareStatement("DELETE FROM seance_enseignants WHERE ID_Seance = ?");
            PreparedStatement stmt2 = connect.prepareStatement("DELETE FROM seance_groupes WHERE ID_Seance = ?");
            PreparedStatement stmt3 = connect.prepareStatement("DELETE FROM seance_salles WHERE ID_Seance = ?");
            //stmt.setInt(1, seance.getID());
            
            stmt1.setInt(1, ID);
            stmt2.setInt(1, ID);
            stmt3.setInt(1, ID);
            stmt.setInt(1, ID);
            
            stmt1.execute();
            stmt2.execute();
            stmt3.execute();
            stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public  ArrayList<Seance> afficherSeance(){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance");
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                while(rs1.next() && rs2.next()){
                    String NomCours = rs1.getString("Nom");
                    String NomTypeCours = rs2.getString("Nom");
                    
                    Seance seance = new Seance();
                    seance.setID(ID);
                    seance.setSemaine(Semaine);
                    seance.setDate(Date);
                    seance.setHeureDebut(HeureDebut);
                    seance.setHeureFin(HeureFin);
                    seance.setEtat(Etat);

                    seance.setNomCours(NomCours);
                    seance.setNomTypeCours(NomTypeCours);
                    //seance.setIDCours(IDCours);
                    //seance.setIDType(IDType);

                    seances.add(seance);
                }
                
                
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    
    
    public  ArrayList<Seance> afficherSeanceLundi8h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Lundi' AND Heure_Debut LIKE '8h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    
    
    public  ArrayList<Seance> afficherSeanceLundi10h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Lundi' AND Heure_Debut LIKE '10h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    
    public  ArrayList<Seance> afficherSeanceLundi13h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Lundi' AND Heure_Debut LIKE '13h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    public  ArrayList<Seance> afficherSeanceLundi14h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Lundi' AND Heure_Debut LIKE '14h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    public  ArrayList<Seance> afficherSeanceLundi16h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Lundi' AND Heure_Debut LIKE '16h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    public  ArrayList<Seance> afficherSeanceMardi8h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mardi' AND Heure_Debut LIKE '8h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    
     public  ArrayList<Seance> afficherSeanceMardi10h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mardi' AND Heure_Debut LIKE '10h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
     
     
      public  ArrayList<Seance> afficherSeanceMardi13h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mardi' AND Heure_Debut LIKE '13h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
      
      
      
      
       public  ArrayList<Seance> afficherSeanceMardi14h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mardi' AND Heure_Debut LIKE '14h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
       
       
       
        public  ArrayList<Seance> afficherSeanceMardi16h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mardi' AND Heure_Debut LIKE '16h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
        
        
        
        
         public  ArrayList<Seance> afficherSeanceMercredi8h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mercredi' AND Heure_Debut LIKE '8h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceMercredi10h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mercredi' AND Heure_Debut LIKE '10h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceMercredi13h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mercredi' AND Heure_Debut LIKE '13h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceMercredi14h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mercredi' AND Heure_Debut LIKE '14h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         
         
         public  ArrayList<Seance> afficherSeanceMercredi16h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Mercredi' AND Heure_Debut LIKE '16h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         
         
         public  ArrayList<Seance> afficherSeanceJeudi8h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Jeudi' AND Heure_Debut LIKE '8h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         public  ArrayList<Seance> afficherSeanceJeudi10h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Jeudi' AND Heure_Debut LIKE '10h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         
         public  ArrayList<Seance> afficherSeanceJeudi13h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Jeudi' AND Heure_Debut LIKE '13h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         
         public  ArrayList<Seance> afficherSeanceJeudi14h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Jeudi' AND Heure_Debut LIKE '14h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceJeudi16h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Jeudi' AND Heure_Debut LIKE '16h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceVendredi8h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Vendredi' AND Heure_Debut LIKE '8h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         public  ArrayList<Seance> afficherSeanceVendredi10h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Vendredi' AND Heure_Debut LIKE '10h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceVendredi13h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Vendredi' AND Heure_Debut LIKE '13h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         
         public  ArrayList<Seance> afficherSeanceVendredi14h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Vendredi' AND Heure_Debut LIKE '14h00%'AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
         
         
         
         public  ArrayList<Seance> afficherSeanceVendredi16h00(int semaine){
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        int IDE;
        int IDS;
        int IDG;
         
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Seance WHERE Date LIKE 'Vendredi' AND Heure_Debut LIKE '16h00%' AND Semaine ="+semaine);
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                int Semaine = rs.getInt("Semaine");
                String Date = rs.getString("Date");
                String HeureDebut = rs.getString("Heure_Debut");
                String HeureFin = rs.getString("Heure_Fin");
                int Etat = rs.getInt("Etat");
                int IDCours = rs.getInt("ID_Cours") ;
                int IDType = rs.getInt("ID_Type");
                
                PreparedStatement stmt1 = connect.prepareStatement("SELECT Nom FROM Cours WHERE ID = "+IDCours);
                rs1 = stmt1.executeQuery();
                
                PreparedStatement stmt2 = connect.prepareStatement("SELECT Nom FROM Type_Cours WHERE ID = "+IDType);
                rs2 = stmt2.executeQuery();
                
                PreparedStatement stmt3 = connect.prepareStatement("SELECT ID_Enseignant FROM Seance_Enseignants WHERE ID_Seance = "+ID);
                ResultSet rs3 = stmt3.executeQuery();
                
                PreparedStatement stmt5 = connect.prepareStatement("SELECT ID_Salle FROM Seance_Salles WHERE ID_Seance = "+ID);
                ResultSet rs5 = stmt5.executeQuery();
                
                PreparedStatement stmt7 = connect.prepareStatement("SELECT ID_Groupe FROM Seance_Groupes WHERE ID_Seance = "+ID);
                ResultSet rs7 = stmt7.executeQuery();
               
                while (rs3.next() && rs5.next() && rs7.next()){
                    IDE = rs3.getInt("ID_Enseignant");
                    IDS = rs5.getInt("ID_Salle");
                    IDG = rs7.getInt("ID_Groupe");
                    
                    PreparedStatement stmt4 = connect.prepareStatement("SELECT Nom FROM Utilisateur WHERE ID = "+IDE);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt6 = connect.prepareStatement("SELECT Nom FROM Salle WHERE ID = "+IDS);
                    ResultSet rs6 = stmt6.executeQuery();
                    
                    PreparedStatement stmt8 = connect.prepareStatement("SELECT nom FROM Groupe WHERE ID = "+IDG);
                    ResultSet rs8 = stmt8.executeQuery();
                    
                    while (rs4.next() && rs6.next() && rs8.next()){
                        String NomProf = rs4.getString("Nom");
                        String NomSalle = rs6.getString("Nom");
                        String NomGroupe = rs8.getString("nom");
                        
                        while(rs1.next() && rs2.next()){
                        String NomCours = rs1.getString("Nom");
                        String NomTypeCours = rs2.getString("Nom");
                        
                        Seance seance = new Seance();
                        seance.setID(ID);
                        seance.setSemaine(Semaine);
                        seance.setDate(Date);
                        seance.setHeureDebut(HeureDebut);
                        seance.setHeureFin(HeureFin);
                        seance.setEtat(Etat);

                        seance.setNomCours(NomCours);
                        seance.setNomTypeCours(NomTypeCours);

                        seance.setNomProf(NomProf);
                        seance.setNomSalle(NomSalle);
                        seance.setNomGroupe(NomGroupe);
                        //seance.setIDCours(IDCours);
                        //seance.setIDType(IDType);

                        seances.add(seance);
                        }
                    }
                }
                
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seances;
        
    }
    
    
    
    
    
}
