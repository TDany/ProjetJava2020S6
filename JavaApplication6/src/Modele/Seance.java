/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;

/**
 *
 * @author danytadrous
 */
public class Seance {
    private int ID;
    private int Semaine;
    private String Date;
    private String HeureDebut;
    private String HeureFin;
    private int Etat;
    
    private int IDCours;
    private int IDType;
    private String NomCours;
    private String NomTypeCours;
    
    private int ID_Prof;
    private int ID_Groupe;
    private int ID_Salle;
    
    private String NomProf;
    private String NomSalle;
    private String NomGroupe;
    
    public Seance(int Semaine, String Date, String HeureDebut, String HeureFin, int Etat, int IDCours, int IDType, String NomCours, String NomTypeCours, int ID_Prof, int ID_Groupe, int ID_Salle, String NomProf, String NomSalle, String NomGroupe){
        this.ID = ID;
        this.Semaine = Semaine;
        this.Date = Date;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
        this.Etat = Etat;
        
        this.IDCours = IDCours;
        this.IDType = IDType;
        
        this.NomCours = NomCours;
        this.NomTypeCours = NomTypeCours;
        this.ID_Prof = ID_Prof;
        this.ID_Groupe = ID_Groupe;
        this.ID_Salle = ID_Salle;
        
        this.NomProf = NomProf;
        this.NomSalle = NomSalle;
        this.NomGroupe = NomGroupe;
    }
    
    public Seance(){
        
    }
    
    public int getSemaine(){
        return Semaine;
    }
    
    public void setSemaine(int Semaine){
        this.Semaine = Semaine;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public String getDate(){
        return Date;
    }
    
    public void setDate(String Date){
        this.Date = Date;
    }
    
    public String getHeureDebut(){
        return HeureDebut;
    }
    
    public void setHeureDebut(String HeureDebut){
        this.HeureDebut = HeureDebut;
    }
    
    public String getHeureFin(){
        return HeureFin;
    }
    
    public void setHeureFin(String HeureFin){
        this.HeureFin = HeureFin;
    }
    
    public int getEtat(){
        return Etat;
    }
    
    public void setEtat(int Etat){
        this.Etat = Etat;
    }
    
    public int getIDCours(){
        return IDCours;
    }
    
    public void setIDCours(int IDCours){
        this.IDCours = IDCours;
    }
    
    public int getIDType(){
        return IDType;
    }
    
    public void setIDType(int IDType){
        this.IDType = IDType;
    }
    
    public String getNomCours(){
        return NomCours;
    }
    
    public void setNomCours(String NomCours){
        this.NomCours = NomCours;
    }
    
    public String getNomTypeCours(){
        return  NomTypeCours;
    }
    
    public void setNomTypeCours(String  NomTypeCours){
        this.NomTypeCours =  NomTypeCours;
    }
    
    public int getID_Prof(){
        return  ID_Prof;
    }
    
    public void setID_Prof(int  ID_Prof){
        this.ID_Prof =  ID_Prof;
    }

    public int getID_Groupe(){
        return  ID_Groupe;
    }
    
    public void setID_Groupe(int  ID_Groupe){
        this.ID_Groupe =  ID_Groupe;
    }
    
    public int getID_Salle(){
        return  ID_Salle;
    }
    
    public void setID_Salle(int  ID_Salle){
        this.ID_Salle =  ID_Salle;
    }
    
    
    
    
    
    public String getNomProf(){
        return  NomProf;
    }
    
    public void setNomProf(String  NomProf){
        this.NomProf =  NomProf;
    }
    
    public String getNomSalle(){
        return  NomSalle;
    }
    
    public void setNomSalle(String  NomSalle){
        this.NomSalle =  NomSalle;
    }
    
    public String getNomGroupe(){
        return  NomGroupe;
    }
    
    public void setNomGroupe(String  NomGroupe){
        this.NomGroupe =  NomGroupe;
    }
}
