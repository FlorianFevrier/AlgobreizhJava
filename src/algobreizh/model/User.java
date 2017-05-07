/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * <p>User est la classe représentant le commercial.</p>
 * <p>Un commercial est caractérisé par les informations suivantes :</p>
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Son nom.</li>
 * <li>Une adresse email.</li>
 * <li>Une zone géographique.</li>
 * </ul>
 * De plus un commercial a une liste de Client et une liste de rendez-vous,
 * le commercial pourra rajouter des rendez-vous à cette liste.
 * 
 * @see Client
 * @see RendezVous
 * 
 * @author florian
 */
public class User {
    /**
     * Email du commercial.
     */
    private String email;
    /**
     * Nom du commercial.
     */
    private String nom;
    /**
     * Zone géographique du commercial.
     */
    private String zone;
    /**
     * L'ID du commercial.
     */
    private int id;
    /** 
     * Liste de tous les clients du commercial.
     */
    private ObservableList<Client> visiteData = FXCollections.observableArrayList();
    /**
     * Liste qui sert à stocker temporairement les visites d'un client.
     */
    private List<String> listeVisite = new ArrayList<>();
    /**
     * Liste de tous les rendez-vous du commercial.
     */
    private ObservableList<RendezVous> rendezVous = FXCollections.observableArrayList();

    /**
     * Constructeur User.
     * A la construction d'un objet User, l'email du commercial est enregistré.
     * Ainsi que la liste de tous ses clients et la liste de ses rendez-vous.
     * 
     * @param email 
     *          L'email du commercial.
     * @see User#getInfo() 
     */
    public User(String email) {
        this.email = email;
        this.getInfo();
    }
    
    /**
     * Récupère les informations du commercial, ses client et ses rendez-vous.
     * 
     * @see User#recupInfoCommercial() 
     * @see User#recupClient() 
     * @see User#recupListeRendezVous() 
     */
    private void getInfo(){
        try {    
            recupInfoCommercial();
            recupClient();
            recupListeRendezVous();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Enregistre un rendez-vous dans la base de données.
     * 
     * @param idCommercial
     *              L'ID du commercial .
     * @param idClient
     *              L'ID du client.
     * @param lieu
     *              L'adresse du rendez-vous.
     * @param commentaire
     *              Commentaire sur le rendez-vous.
     * @param contact
     *              La personne à contacter.
     * @param dateRdv
     *              Le jour ou a lieu le rendez-vous.
     * @param heureD
     *              L'heure à laquel commence le rendez-vous.
     * @param heureF 
     *              L'heure à laquel se termine le rendez-vous.

     */
    public void setRendezVous(String idCommercial, String idClient, String lieu, String commentaire, String contact, String dateRdv, String heureD, String heureF){
        try {
            // Création de l'objet gérant les requêtes
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement( "INSERT INTO `algobreizh`.`rendezVous` (`idCommercial`, `idClient`, `lieu`, `commentaire`, `personne`, `date`, `heureDebut`, `heureFin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);" );
            //Remplissage des paramètres de la requête
            preparedStatement.setString( 1, idCommercial);
            preparedStatement.setString( 2, idClient);
            preparedStatement.setString( 3, lieu);
            preparedStatement.setString( 4, commentaire);
            preparedStatement.setString( 5, contact);
            //Parse la date pour l'insertion dans la bd
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dateRdv);           
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(date.getTime()));
            //Parse l'heure de debut
            SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm");
            Date heureDebut = formatHeure.parse(heureD);
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(heureDebut.getTime()));
            //Parse l'heure de fin
            Date heureFin = formatHeure.parse(heureF);
            preparedStatement.setTimestamp(8, new java.sql.Timestamp(heureFin.getTime()));
            //execute la requete
            int result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Récupère le nom, la zone géographique et le nom du commercial,
     * dans la base de données.
     * 
     * @see User#id
     * @see User#nom
     * @see User#zone
     */
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
    
    /**
     * Récupère les rendez-vous du commercial dans la base de données
     * et les ajoutes dans une liste d'objet RendezVous.
     * 
     * @see User#rendezVous
     * @see RendezVous
     */
    private void recupListeRendezVous(){
        try {
            PreparedStatement rendezVousStatement = ConnectionBd.getInstance().prepareStatement("SELECT rendezVous.date AS dateRdv, rendezVous.heureDebut AS heureDebut, rendezVous.heureFin AS heureFin, client.nom AS clientRdv FROM rendezVous INNER JOIN client ON rendezVous.idClient = client.idClient WHERE rendezVous.idCommercial = ?");
            rendezVousStatement.setInt(1, this.id);
            ResultSet resultRendezVous = rendezVousStatement.executeQuery();
            
            while ( resultRendezVous.next() ) {
                rendezVous.add(new RendezVous(resultRendezVous.getString("dateRdv"), resultRendezVous.getString("heureDebut"), resultRendezVous.getString("heureFin"), resultRendezVous.getString("clientRdv")));
            }
            
            rendezVousStatement.close();
            resultRendezVous.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Récupère les client du commercial ainsi que l'historique de visite du client,
     * et les ajoutes dans une liste d'objet Client.
     * 
     * @see User#visiteData
     * @see User#listeVisite
     * @see Client
     */
    private void recupClient(){
        try {
            PreparedStatement preparedStatement = ConnectionBd.getInstance().prepareStatement("SELECT idClient AS id, derniereVisite AS dateVisite, nom AS nomClient, num_tel AS tel, adresse AS ad, responsable AS res FROM client WHERE client.zone = ? ORDER BY derniereVisite;");
            preparedStatement.setString( 1, this.zone);
            ResultSet result = preparedStatement.executeQuery();
            
            while ( result.next() ) {
                //Recupere la liste de toute les visite du client et la stock dans temporairement dans listevisite
                //en attendants d'etre passé en parametre lors de l'ajout d'un client
                PreparedStatement visiteStatement = ConnectionBd.getInstance().prepareStatement("SELECT dateVisite AS dateVisite FROM visite WHERE idClient = ? ORDER BY dateVisite");
                visiteStatement.setString( 1, result.getString("id"));
                //Execute la requete
                ResultSet visiteResult = visiteStatement.executeQuery();
                //Ajout les visites dans une liste temporaire
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
    
    /**
     * Retourne l'email du commercial.
     * @return L'email du commercial, sous forme d'une chaine de caractères.
     */        
    public String getEmail() {
        return email;
    }
    /**
     * Retourne le nom du commercial.
     * @return Le nom du commercial, sous forme d'une chaine de caractères.
     */
    public String getNom(){
        return this.nom;
    }
    
    /**
     * Retourne la zone géographique du commercial.
     * @return La zone géographique du commercial, sous forme d'une
     *         chaine de caractères.
     */
    public String getZone(){
        return this.zone;
    }
    
    /**
     * Retourne la liste des client du commercial.
     * @return  La liste des client du commercial, sous la forme 
     *          d'une ObservableList.
     */
    public ObservableList<Client> getPersonData() {
        return visiteData;
    }
    /**
     * 
     * Retourne la liste des rendez-vous du commercial.
     * @return  La liste des rendez-vous du commercial, sous la forme 
     *          d'une ObservableList.
     */    
    public ObservableList<RendezVous> getrendezVous() {
        return rendezVous;
    }
    
    /**
     * Remet à zéro la liste des rendez-vous.<br>
     * Récupère les nouveaux rendez-vous<br>
     * Retourne la liste des rendez-vous du commercial à jour<br>
     * 
     * @return  La liste des rendez-vous du commercial, sous la forme 
     *          d'une ObservableList.
     */   
    public ObservableList<RendezVous> getNewrendezVous() {
        rendezVous.clear();
        recupListeRendezVous();
        return rendezVous;
    }
    
    /**
     * Retourne l'id du commercial.
     * 
     * @return L'id du commercial, sous la forme d'un entier.
     */
    public int getidUSer(){
        return this.id;
    }
}
