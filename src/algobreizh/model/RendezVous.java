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
    
    
    public RendezVous(){
       this(null, null);
    }
    
    public RendezVous(String date, String nomClient){
        this.date = new SimpleStringProperty(date);
        this.nomClient = new SimpleStringProperty(nomClient);
    }
    
    public String getDate(){
        return date.get();
    }
    
    public void setNomClient(String date){
        this.date.set(date);
    }
    
    public StringProperty dateProperty(){
        return date;
    }
    
    public String getnomClient(){
        return nomClient.get();
    }
    
    public void setnomClient(String nomClient){
        this.nomClient.set(nomClient);
    }
    
    public StringProperty nomClientProperty(){
        return nomClient;
    }
}
