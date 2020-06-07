/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.util.List;
import Modele.Utilisateur;

/**
 *
 * @author danytadrous
 */
public interface UtilisateurDao {
    //ICI ON AJOUTE LES METHODES EN LIEN COMME PAR EXEMPLE AFFICHER AJOUTER ETC
    // PUIS ON DEFINIRA LES METHODES DANS UTILISATEURDAOIMPL
    void ajouter( Utilisateur utilisateur );
    List<Utilisateur> lister();
}
