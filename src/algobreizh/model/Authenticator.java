/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author florian
 */
public class Authenticator {

    private static String email = ""; 
 
    public static boolean connexion(String user, String password){
        try {
            /* Création de l'objet gérant les requêtes */
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement( "SELECT mail FROM commercial WHERE mail = ?;" );
            //Remplissage des paramètres de la requête
            preparedStatement.setString( 1, user);
          
           ResultSet result = preparedStatement.executeQuery();
            
            while ( result.next() ) {
                email = result.getString( "mail" );
            }

            result.close();
            preparedStatement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (email.isEmpty() == false) && (email.equals(user));
    }
}
