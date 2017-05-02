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
 *
 * @author florian
 */
public class Client {
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
    
    public Client(int id, String date, String nomClient, String tel, String addresse, String responsable, List<String> listeVisite){
        this.id = id;
        this.date = new SimpleStringProperty(date);
        this.nomClient = new SimpleStringProperty(nomClient);
        this.tel = new SimpleStringProperty(tel);
        this.addresse = new SimpleStringProperty(addresse);
        this.responsable = new SimpleStringProperty(responsable);
        listProperty.set(FXCollections.observableArrayList(listeVisite));
    }
 
    public int getId(){
        return id;
    }
    
    public String getDate(){
        return date.get();
    }
    
    public void setDate(String date){
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
    
    public String gettel(){
        return tel.get();
    }
    
    public void settel(String tel){
        this.tel.set(tel);
    }
    
    public StringProperty telProperty(){
        return tel;
    }
    
    public String getaddresse(){
        return addresse.get();
    }
    
    public void setaddresse(String addresse){
        this.addresse.set(addresse);
    }
    
    public StringProperty addresseProperty(){
        return addresse;
    }
    
    public String getresponsable(){
        return responsable.get();
    }
    
    public void setresponsable(String responsable){
        this.responsable.set(responsable);
    }
    
    public StringProperty responsableProperty(){
        return responsable;
    }
    
    public ListProperty listVisiteProperty(){
        return listProperty;
    }
}
