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
 * <b>Client est la classe représentant un client</b>
 * <p>Un client est caractérisé par les informations suivantes :</p>
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>La dernière date à laquel le client à reçu une visite du commercial</li>
 * <li>Le nom de l'entreprise</li>
 * <li>Son numéro de téléphone</li>
 * <li>Son adresse</li>
 * <li>le nom du responsable de l'entreprise</li>
 * <li>l'historique de visite du client</li>
 * </ul>
 * 
 * @author florian
 */
public class Client {
    /**
     * L'ID, le nom, le num de tél, l'adresse, le responsable,
     * et l'historique de visite du client
     */
    private final int id;
    private final StringProperty date;
    private final StringProperty nomClient;
    private final StringProperty tel;
    private final StringProperty addresse;
    private final StringProperty responsable;
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
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
        this.addresse = new SimpleStringProperty(addresse);
        this.responsable = new SimpleStringProperty(responsable);
        listProperty.set(FXCollections.observableArrayList(listeVisite));
    }
 
    /**
     * Retourne l'ID du client
     * 
     * @return l'id du client, sous forme d'entier
     */
    public int getId(){
        return id;
    }
    
    /**
     * Retourne la date de dernière visite
     * 
     * @return la date de dernière visite, sous la forme d'une chaine de caractère
     */
    public String getDate(){
        return date.get();
    }

    /**
     * Met à jour la date de dernière visite
     * 
     * @param date 
     *          la date de dernière visite, sous la forme d'une chaine de caractère
     */    
    public void setDate(String date){
        this.date.set(date);
    }

    /**
     * Retourne la date de dernière visite
     * 
     * @return la date de dernière visite, sous la forme d'une StringProperty
     */    
    public StringProperty dateProperty(){
        return date;
    }
 
    /**
     * Retourne le nom du client
     * 
     * @return le nom du client, sous la forme d'une chaine de caractère
     */    
    public String getnomClient(){
        return nomClient.get();
    }

    /**
     * Met à jour le nom du client
     * 
     * @param nomClient
     *          le nom du client, sous la forme d'une chaine de caractère
     */        
    public void setnomClient(String nomClient){
        this.nomClient.set(nomClient);
    }
    
    /**
     * Retourne le nom du client
     * 
     * @return le nom du client, sous la forme d'une StringProperty
     */    
    public StringProperty nomClientProperty(){
        return nomClient;
    }

    /**
     * Retourne le numéro de téléphone du client
     * 
     * @return le numéro de téléphone, sous la forme d'une chaine de caractère
     */       
    public String gettel(){
        return tel.get();
    }

    /**
     * Met à jour le numéro de téléphone du client
     * 
     * @param tel
     *          le numéro de téléphone, sous la forme d'une chaine de caractère
     */       
    public void settel(String tel){
        this.tel.set(tel);
    }
    
    /**
     * Retourne le numéro de téléphone du client
     * 
     * @return le numéro de téléphone, sous la forme d'une StringProperty
     */    
    public StringProperty telProperty(){
        return tel;
    }

    /**
     * Retourne l'adresse du client
     * 
     * @return l'adresse, sous la forme d'une chaine de caractère
     */       
    public String getaddresse(){
        return addresse.get();
    }

    /**
     * Met à jour l'adresse du client
     * 
     * @param addresse
     *          l'adresse du client, sous la forme d'une chaine de caractère
     */       
    public void setaddresse(String addresse){
        this.addresse.set(addresse);
    }
    
    /**
     * Retourne l'adresse du client
     * 
     * @return l'adresse du client, sous la forme d'une StringProperty
     */    
    public StringProperty addresseProperty(){
        return addresse;
    }

    /**
     * Retourne le nom du responsable 
     * 
     * @return le nom du responsable, sous la forme d'une chaine de caractère
     */       
    public String getresponsable(){
        return responsable.get();
    }

    /**
     * Met à jour le nom du responsable
     * 
     * @param responsable
     *          le nom du responsable, sous la forme d'une chaine de caractère
     */       
    public void setresponsable(String responsable){
        this.responsable.set(responsable);
    }
    
    /**
     * Retourne le nom du responsable
     * 
     * @return le nom du responsable, sous la forme d'une StringProperty
     */    
    public StringProperty responsableProperty(){
        return responsable;
    }
    
    /**
     * Retourne l'historique de visite
     * 
     * @return l'historique de visite, sous la forme d'une ListProperty
     */    
    public ListProperty listVisiteProperty(){
        return listProperty;
    }
}
