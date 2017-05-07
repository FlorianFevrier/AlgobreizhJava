/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>ConnectionBd est la classe qui effectue la liaison à la base de données,
 * selon le design pattern singleton.</p>
 * 
 * @author florian
 */
public class ConnectionBd {
    /**
     * Chemin de la base de données.
     */
    private String url = "jdbc:mysql://51.254.222.13/algobreizh";
  
    /**
     * Fichier de conf de la base de données, contient l'identifiant et le mot de passe.
    */
    private InputStream in = getClass().getResourceAsStream("config.txt"); 
  
    /**
     * Nom d'utilisateur.
     */
    private String user = null;
    
    /**
     * Mot de passe.
     */
    private String passwd = null;

    /**
     * Une connexion à la base de données.
     */
    private static Connection connect;

    /**
     * Constructeur privée ConnectionBd.
     * 
     * <p>A la construction d'un objet ConnectionBd,
     * on récupere les identifiants de connexion dans le fichier config.txt.
     * Puis on se connecte à la base de données.
     * </p>
     */
    private ConnectionBd(){
      //On va lire les info pour se connecter à la base de données dans un fichier texte
      try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            user = reader.readLine();
            passwd = reader.readLine();
      } 
      catch (IOException e) {
         System.err.println("Error: " + e);
      }

      //On se connecte à la base de données
      try { 
          connect = DriverManager.getConnection(url, user, passwd);

      } catch (SQLException e) {
        e.printStackTrace();
      }

    }

    /**
     * Retourne une instance de la base de données.
     * @return Une instance de la base de données, sous forme d'objet Connection.
     */
     public static Connection getInstance(){

      if(connect == null){
        new ConnectionBd();
      }

      return connect;   

    }       
}
