/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

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
    
    
    public Seance(int ID, int Semaine, String Date, String HeureDebut, String HeureFin, int Etat, int IDCours, int IDType){
        this.ID = ID;
        this.Semaine = Semaine;
        this.Date = Date;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
        this.Etat = Etat;
        
        this.IDCours = IDCours;
        this.IDType = IDType;
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
    
    public void setHeureFint(String HeureFin){
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

    
    
}
