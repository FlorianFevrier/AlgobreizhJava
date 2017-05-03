/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author florian
 */
public class RendezVous {
    private final StringProperty date;
    private final StringProperty nomClient;
    private final StringProperty heureDebut;
    private final StringProperty heureFin;
    
    
    public RendezVous(){
       this(null, null, null, null);
    }
    
    public RendezVous(String date, String heureDebut, String heureFin,String nomClient){
        this.date = new SimpleStringProperty(date);
        this.heureDebut = new SimpleStringProperty(heureDebut);
        this.heureFin = new SimpleStringProperty(heureFin);
        this.nomClient = new SimpleStringProperty(nomClient);
    }
    
    //Accesseur Date
    public String getDate(){
        return date.get();
    }
    
    public void setDate(String date){
        this.date.set(date);
    }
    
    public StringProperty dateProperty(){
        return date;
    }
    
    //Acesseur nom
    public String getnomClient(){
        return nomClient.get();
    }
    
    public void setnomClient(String nomClient){
        this.nomClient.set(nomClient);
    }
    
    public StringProperty nomClientProperty(){
        return nomClient;
    }
    
    //Accesseur Heure de debut
    public String heureDebut(){
        return heureDebut.get();
    }
    
    public void setheureDebut(String heureDebut){
        this.heureDebut.set(heureDebut);
    }
    
    public StringProperty heureDebutProperty(){
        return heureDebut;
    }
    
    //Accesseur Heure de Fin
    public String heureFin(){
        return heureFin.get();
    }
    
    public void setheureFin(String heureFin){
        this.heureFin.set(heureFin);
    }
    
    public StringProperty heureFinProperty(){
        return heureFin;
    }
}
