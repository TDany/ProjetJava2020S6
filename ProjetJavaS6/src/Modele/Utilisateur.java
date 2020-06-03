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
public class Utilisateur {
    private String Email;
    private String Passwd;
    private String nom;
    private String prenom;
    private int ID;
    private int Droit;
    
    public Utilisateur(String Email, String Passwd, String nom, String prenom, int ID, int Droit){
        this.Email = Email;
        this.Passwd = Passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.ID = ID;
        this.Droit = Droit;
    }
    
    public Utilisateur(){
        
    }
    
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
     public String getEmail(){
        return Email;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
     public String getPasswd(){
        return Passwd;
    }
    
    public void setPasswd(String Passwd){
        this.Passwd = Passwd;
    }
    
    public int getDroit(){
        return Droit;
    }
    
    public void setDroit(int Droit){
        this.Droit = Droit;
    }
    
}
