/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * ConnectionBd est la classe qui effectue la liaison à la base de données,
 * selon le design pattern singleton
 * 
 * @author florian
 */
public class ConnectionBd {
  /**
   * Chemin de la base de données
   */
  private String url = "jdbc:mysql://51.254.222.13/algobreizh";
  /**
   * Fichier de conf de la base de données, contient l'identifiant et le mot de passe
   */
  private String conf = "src/algobreizh/model/config.txt";

  /**
   * Nom d'utilisateur 
   */
  private String user = null;
  /**
   * Mot de passe
   */
  private String passwd = null;

  /**
   * Une connexion à la base de données
   */
  private static Connection connect;

  /**
   * Constructeur privée ConnectionBd
   * 
   * <p>A la construction d'un objet ConnectionBd,
   * on récupere les identifiants de connexion dans le fichier config.txt.
   * Puis on se connecte à la base de données
   * </p>
   */
  private ConnectionBd(){
    //On va lire les info pour se connecter à la base de données dans un fichier texte
    try
    {
        File f = new File (conf);
        Scanner scanner = new Scanner (f);

        while (true)
        {
            try
            {
                user = scanner.next();
                passwd = scanner.next();
            }
            catch (NoSuchElementException exception)
            {
                break;
            }
        }

        scanner.close();
    }
    catch (FileNotFoundException exception) {
        System.out.println ("Le fichier n'a pas été trouvé");
    }
    
    //On se connecte à la base de données
    try { 
        connect = DriverManager.getConnection(url, user, passwd);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Retourne une instance de la base de données
   * @return une instance de la base de données, sous forme d'objet Connection
   */
   public static Connection getInstance(){

    if(connect == null){
      new ConnectionBd();
    }

    return connect;   

  }       
}
