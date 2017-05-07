/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>Classe qui permet d'authentifier l'utilisateur.</p>
 * 
 * @author florian
 */
public class Authenticator {
    /**
     * L'email du commercial.
     */
    private static String email = ""; 
 
    /**
     * Vérifie si l'email et le mot de passe que l'utilisateur a saisi 
     * correspondent à ceux dans la base de données.
     * 
     * @param user
     *          L'email du commercial.
     * @param password
     *          Le mot de passe du commercial.
     * @return vrai si la requête renvoie la même adresse email sinon faux, sous la forme d'un booléen.
     * 
     * @see ConnectionBd#getInstance() 
     */
    public static boolean connexion(String user, String password){
        try {
            // Création de l'objet gérant les requêtes 
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement( "SELECT mail FROM utilisateur WHERE mail = ? AND mdp = MD5(?);");
            //Remplissage des paramètres de la requête
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            //Execution de la requete
            ResultSet result = preparedStatement.executeQuery();
            //Lecture du resultat
            while ( result.next() ) {
                email = result.getString( "mail" );
            }
            //Ferme la connection
            result.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (email.isEmpty() == false) && (email.equals(user));
    }
}
