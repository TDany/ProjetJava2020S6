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
public class TypeCours {
    private int ID;
    private  String Nom;
    
    public TypeCours(int ID, String Nom){
        this.ID = ID;
        this.Nom = Nom;
    }
    
    public TypeCours(){
        
    }
    
    public String getNom(){
        return Nom;
    }
    
    public void setNom(String Nom){
        this.Nom = Nom;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }

    
    
}
