/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author florian
 */
public class User {
    private String email;
    private String nom;
    private String zone;
    private int id;
    // liste de tout les client du commercial
    private ObservableList<Client> visiteData = FXCollections.observableArrayList();
    // liste qui sert a stocke temporairement les visites d'un client
    private List<String> listeVisite = new ArrayList<>();
    // liste de tout les rendez vous du commercial
    private ObservableList<RendezVous> rendezVous = FXCollections.observableArrayList();

    public User(String email) {
        this.email = email;
        this.getInfo();
    }
    
    //Recupere les info du commercial
    private void getInfo(){
        try {    
            recupInfoCommercial();
            recupListeRendezVous();      
            recupClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //enregistre un rendez-vous dans la base de donnée
    public void setRendezVous(String idCommercial, String idClient, String lieu, String commentaire, String contact, String heure){
        try {
            /* Création de l'objet gérant les requêtes */
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement( "INSERT INTO `algobreizh`.`rendezVous` (`idCommercial`, `idClient`, `lieu`, `commentaire`, `personne`, `date`) VALUES (?, ?, ?, ?, ?, ?);" );
            //Remplissage des paramètres de la requête
            preparedStatement.setString( 1, idCommercial);
            preparedStatement.setString( 2, idClient);
            preparedStatement.setString( 3, lieu);
            preparedStatement.setString( 4, commentaire);
            preparedStatement.setString( 5, contact);
            //Parse la date pour l'insertion dans la bd
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(heure);           
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(date.getTime()));
            //execute la requete
            int result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Recupere les info sur le commercial
    private void recupInfoCommercial(){
        try {
            /* Création de l'objet gérant les requêtes */
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement( "SELECT idCommercial, zone, nom FROM commercial WHERE mail = ?;" );
            //Remplissage des paramètres de la requête
            preparedStatement.setString( 1, this.email);
            ResultSet result = preparedStatement.executeQuery();
            
            //Recupere les info sur le commercial
            while ( result.next() ) {
                id = result.getInt("idCommercial");
                zone = result.getString("zone");
                nom = result.getString("nom");
            }
            
            result.close();
            preparedStatement.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Recupere la liste des rendez vous 
    private void recupListeRendezVous(){
        try {
            PreparedStatement rendezVousStatement = ConnectionBd.getInstance().prepareStatement("SELECT rendezVous.date AS dateRdv, client.nom AS clientRdv FROM rendezVous INNER JOIN client ON rendezVous.idClient = client.idClient");
            ResultSet resultRendezVous = rendezVousStatement.executeQuery();
            
            while ( resultRendezVous.next() ) {
                rendezVous.add(new RendezVous(resultRendezVous.getString("dateRdv"), resultRendezVous.getString("clientRdv")));
            }
            rendezVousStatement.close();
            resultRendezVous.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   //Recupere les client du commercial
    private void recupClient(){
        try {
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement("SELECT idClient AS id, derniereVisite AS dateVisite, nom AS nomClient, num_tel AS tel, adresse AS ad, responsable AS res FROM client WHERE client.zone = ? ORDER BY derniereVisite;");
            preparedStatement.setString( 1, this.zone);
            ResultSet result = preparedStatement.executeQuery();
            
            while ( result.next() ) {
                //Recupere la liste de toute les visite du client et la stock dans temporairement dans listevisite
                //en attendants d'etre passé en parametre lors de l'ajout d'un client
                PreparedStatement visiteStatement = ConnectionBd.getInstance().prepareStatement("SELECT dateVisite AS dateVisite FROM visite WHERE idCommercial = ? ORDER BY dateVisite");
                visiteStatement.setString( 1, result.getString("id"));
                ResultSet visiteResult = visiteStatement.executeQuery();
                while(visiteResult.next()){
                    listeVisite.add(visiteResult.getString("dateVisite"));
                }
                
                //Ajoute le client dans la liste d'objet Client
                visiteData.add(new Client(Integer.parseInt(result.getString("id")) ,result.getString("dateVisite"), result.getString("nomClient"), result.getString("tel"), result.getString("ad"), result.getString("res"), listeVisite));
                
                listeVisite.clear();
                visiteResult.close();
                visiteStatement.close();
            }
            result.close();
            preparedStatement.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
    public String getEmail() {
        return email;
    }
    public String getNom(){
        return this.nom;
    }
    
    public String getZone(){
        return this.zone;
    }
    
    public ObservableList<Client> getPersonData() {
        return visiteData;
    }
    
    public ObservableList<RendezVous> getrendezVous() {
        return rendezVous;
    }
    
    public ObservableList<RendezVous> getNewrendezVous() {
        rendezVous.clear();
        recupListeRendezVous();
        return rendezVous;
    }
    
    public int getidUSer(){
        return this.id;
    }
}
