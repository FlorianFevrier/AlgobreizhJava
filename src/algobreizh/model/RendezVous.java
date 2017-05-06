/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <b>RendezVous est la classe représentant un rendez-vous</b>
 * <p>un rendez-vous est caractérisé par les informations suivantes :</p>
 * <ul>
 * <li>La date du rendez-vous.</li>
 * <li>l'heure à laquel commence le rendez-vous</li>
 * <li>l'heure à laquel se termine un rendez-vous</li>
 * <li>le client avec qui on prend le rendez-vous</li>
 * </ul>
 * 
 * @author florian
 */
public class RendezVous {
    /**
    * date, heure, et nom du client d'un rendez-vous
    */
    private final StringProperty date;
    private final StringProperty nomClient;
    private final StringProperty heureDebut;
    private final StringProperty heureFin;
    
    /**
     * Appele le constructeur suivant
     */
    public RendezVous(){
       this(null, null, null, null);
    }
    
    /**
     * Constructeur RendezVous
     * 
     * @param date
     *          La date du rendez-vous
     * @param heureDebut
     *          l'heure à laquel commence un rendez-vous
     * @param heureFin
     *          l'heure à laquel se termine un rendez-vous
     * @param nomClient 
     *          le nom du client avec qui on prend le rendez-vous
     */
    public RendezVous(String date, String heureDebut, String heureFin,String nomClient){
        this.date = new SimpleStringProperty(date);
        this.heureDebut = new SimpleStringProperty(heureDebut);
        this.heureFin = new SimpleStringProperty(heureFin);
        this.nomClient = new SimpleStringProperty(nomClient);
    }
    
    /**
     * Retourne la date du rendez-vous
     * 
     * @return la date du rendez-vous, sous la forme d'une chaine de caractère
     */
    public String getDate(){
        return date.get();
    }
    
    /**
     * Met à jour la date du rendez-vous
     * 
     * @param date 
     *          la date du rendez-vous, sous la forme d'une chaine de caractère
     */
    public void setDate(String date){
        this.date.set(date);
    }
    
    /**
     * Retourne la date du rendez-vous
     * 
     * @return la date du rendez-vous, sous la forme d'une StringProperty
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
     * Retourne l'heure à laquel commence le rendez-vous
     * 
     * @return l'heure de début du rendez-vous,
     *         sous la forme d'une chaine de caractère
     */    
    public String heureDebut(){
        return heureDebut.get();
    }
    
    /**
     * Met à jour l'heure à laquel commence le rendez-vous
     * 
     * @param heureDebut 
     *          l'heure de début du rendez-vous,
     *          sous la forme d'une chaine de caractère
     */       
    public void setheureDebut(String heureDebut){
        this.heureDebut.set(heureDebut);
    }

    /**
     * Retourne l'heure à laquel commence le rendez-vous
     * 
     * @return l'heure de début du rendez-vous,
     *         sous la forme d'une StringProperty
     */      
    public StringProperty heureDebutProperty(){
        return heureDebut;
    }
    
    /**
     * Retourne l'heure à laquel se termine le rendez-vous
     * 
     * @return l'heure de début du rendez-vous,
     *         sous la forme d'une chaine de caractère
     */       
    public String heureFin(){
        return heureFin.get();
    }
    
    /**
     * Met à jour l'heure à laquel se termine le rendez-vous
     * 
     * @param heureFin 
     *          l'heure de fin du rendez-vous,
     *          sous la forme d'une chaine de caractère
     */       
    public void setheureFin(String heureFin){
        this.heureFin.set(heureFin);
    }

    /**
     * Retourne l'heure à laquel se termine le rendez-vous
     * 
     * @return l'heure de fin du rendez-vous,
     *         sous la forme d'une StringProperty
     */       
    public StringProperty heureFinProperty(){
        return heureFin;
    }
}
