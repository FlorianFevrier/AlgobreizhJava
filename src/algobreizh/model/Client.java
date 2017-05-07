/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * <p>Client est la classe représentant un client.</p>
 * <p>Un client est caractérisé par les informations suivantes :</p>
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>La dernière date à laquel le client à reçu une visite du commercial.</li>
 * <li>Le nom de l'entreprise.</li>
 * <li>Son numéro de téléphone.</li>
 * <li>Son adresse.</li>
 * <li>le nom du responsable de l'entreprise.</li>
 * <li>l'historique de visite du client.</li>
 * </ul>
 * 
 * @author florian
 */
public class Client {
    /**
     * L'ID du client.
     */
    private final int id;
    /**
     * La date de dernière visite du client.
     */
    private final StringProperty date;
    /**
     * Le nom du client.
     */
    private final StringProperty nomClient;
    /**
     * Le numéro de téléphone du client.
     */
    private final StringProperty tel;
    /**
     * L'adresse du client.
     */
    private final StringProperty adresse;
    /**
     * Le nom du responsable de l'entreprise.
     */
    private final StringProperty responsable;
    /**
     * l'historique de visite du client.
     */
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
    /**
     * Appele le constructeur suivant.
     */
    public Client(){
        this(0, null, null, null, null, null, null);
    }
    
    /**
     * Constructeur Client
     * 
     * @param id
     *      L'ID du client
     * @param date
     *      La date de dernière visite du client
     * @param nomClient
     *      Le nom du client
     * @param tel
     *      Le numéro de téléphone du client
     * @param addresse
     *      L'adresse du client 
     * @param responsable
     *      Le nom du responsable de l'entreprise
     * @param listeVisite 
     *      La liste de toute les visites du client par le commercial
     */
    public Client(int id, String date, String nomClient, String tel, String addresse, String responsable, List<String> listeVisite){
        this.id = id;
        this.date = new SimpleStringProperty(date);
        this.nomClient = new SimpleStringProperty(nomClient);
        this.tel = new SimpleStringProperty(tel);
        this.adresse = new SimpleStringProperty(addresse);
        this.responsable = new SimpleStringProperty(responsable);
        listProperty.set(FXCollections.observableArrayList(listeVisite));
    }
 
    /**
     * Retourne l'ID du client.
     * 
     * @return L'id du client, sous forme d'entier.
     */
    public int getId(){
        return id;
    }
    
    /**
     * Retourne la date de dernière visite.
     * 
     * @return La date de dernière visite, sous la forme d'une chaine de caractère.
     */
    public String getDate(){
        return date.get();
    }

    /**
     * Met à jour la date de dernière visite.
     * 
     * @param date 
     *          La date de dernière visite, sous la forme d'une chaine de caractère.
     */    
    public void setDate(String date){
        this.date.set(date);
    }

    /**
     * Retourne la date de dernière visite.
     * 
     * @return La date de dernière visite, sous la forme d'une StringProperty.
     */    
    public StringProperty dateProperty(){
        return date;
    }
 
    /**
     * Retourne le nom du client.
     * 
     * @return Le nom du client, sous la forme d'une chaine de caractère.
     */    
    public String getnomClient(){
        return nomClient.get();
    }

    /**
     * Met à jour le nom du client.
     * 
     * @param nomClient
     *          Le nom du client, sous la forme d'une chaine de caractère.
     */        
    public void setnomClient(String nomClient){
        this.nomClient.set(nomClient);
    }
    
    /**
     * Retourne le nom du client.
     * 
     * @return Le nom du client, sous la forme d'une StringProperty.
     */    
    public StringProperty nomClientProperty(){
        return nomClient;
    }

    /**
     * Retourne le numéro de téléphone du client.
     * 
     * @return Le numéro de téléphone, sous la forme d'une chaine de caractère.
     */       
    public String gettel(){
        return tel.get();
    }

    /**
     * Met à jour le numéro de téléphone du client.
     * 
     * @param tel
     *          Le numéro de téléphone, sous la forme d'une chaine de caractère.
     */       
    public void settel(String tel){
        this.tel.set(tel);
    }
    
    /**
     * Retourne le numéro de téléphone du client.
     * 
     * @return Le numéro de téléphone, sous la forme d'une StringProperty.
     */    
    public StringProperty telProperty(){
        return tel;
    }

    /**
     * Retourne l'adresse du client.
     * 
     * @return L'adresse, sous la forme d'une chaine de caractère.
     */       
    public String getadresse(){
        return adresse.get();
    }

    /**
     * Met à jour l'adresse du client.
     * 
     * @param addresse
     *          L'adresse du client, sous la forme d'une chaine de caractère.
     */       
    public void setaddresse(String addresse){
        this.adresse.set(addresse);
    }
    
    /**
     * Retourne l'adresse du client.
     * 
     * @return L'adresse du client, sous la forme d'une StringProperty.
     */    
    public StringProperty addresseProperty(){
        return adresse;
    }

    /**
     * Retourne le nom du responsable.
     * 
     * @return Le nom du responsable, sous la forme d'une chaine de caractère.
     */       
    public String getresponsable(){
        return responsable.get();
    }

    /**
     * Met à jour le nom du responsable.
     * 
     * @param responsable
     *          Le nom du responsable, sous la forme d'une chaine de caractère.
     */       
    public void setresponsable(String responsable){
        this.responsable.set(responsable);
    }
    
    /**
     * Retourne le nom du responsable.
     * 
     * @return le nom du responsable, sous la forme d'une StringProperty.
     */    
    public StringProperty responsableProperty(){
        return responsable;
    }
    
    /**
     * Retourne l'historique de visite.
     * 
     * @return L'historique de visite, sous la forme d'une ListProperty.
     */    
    public ListProperty listVisiteProperty(){
        return listProperty;
    }
}
