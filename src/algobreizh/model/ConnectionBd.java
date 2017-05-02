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
 *
 * @author florian
 */
public class ConnectionBd {
  //URL de connexion
  private String url = "jdbc:mysql://51.254.222.13/algobreizh";
  private String conf = "src/algobreizh/model/config.txt";

  private String user = null;
  private String passwd = null;

  //Objet Connection
  private static Connection connect;

  //Constructeur privé
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
    
    //On se connecte à la base de donnée
    try {
        //Chargement du driver JDBC
        //Class.forName("com.mysql.jdbc.Driver");   
        connect = DriverManager.getConnection(url, user, passwd);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
   public static Connection getInstance(){

    if(connect == null){
      new ConnectionBd();
    }

    return connect;   

  }       
}
