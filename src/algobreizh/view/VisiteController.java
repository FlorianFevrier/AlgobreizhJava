/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.view;

import algobreizh.Main;
import algobreizh.model.User;
import algobreizh.model.Client;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfxtras.scene.control.CalendarTimeTextField;
import jfxtras.scene.control.agenda.Agenda;

/**
 * FXML Controller class
 *
 * @author florian
 */
public class VisiteController implements Initializable {
    @FXML
    private Label nomCommercial;
    
    @FXML
    private Label zoneCommercial;
   
    @FXML
    private Label nomClient;
    
    @FXML
    private Label villeClient;
    
    @FXML
    private Label telClient;
    
    //tableau des dernieres visites
    @FXML
    private TableView<Client> visteTable;
    
    @FXML
    private TableColumn<Client, String> dateColumn;
    
    @FXML
    private TableColumn<Client, String> nomClientColumn;
    
    //tableau des rendez vous
    /*@FXML
    private TableView<RendezVous> rendezVousTable;
    
    @FXML
    private TableColumn<RendezVous, String> dateRendezVousColumn;
    
    @FXML
    private TableColumn<RendezVous, String> nomClientRendezVousColumn;
    */
    @FXML
    private ListView<String> listView= new ListView(); 
    
    @FXML
    private TextField lieu;
    
    @FXML
    private TextField contact;
    
    @FXML
    private DatePicker dateRdv;
    
    @FXML
    private CalendarTimeTextField heureDebut;
    
    @FXML
    private CalendarTimeTextField heureFin;
    
    @FXML
    private TextArea comment;
    
    @FXML
    private TextField clientRdv;
    
    @FXML
    private Agenda agenda;
            
    private Main application;
    private int idClient;
    
    ArrayList minute = new ArrayList();
    ArrayList heure = new ArrayList();
    
    public void setApp(Main application){
        this.application = application;
        
        //Remplie les informations du commercial
        User loggedUser = application.getLoggedUser();
        nomCommercial.setText(loggedUser.getNom());
        zoneCommercial.setText(loggedUser.getZone());
        
        //Remplie le tableau des derniere visite
        visteTable.setItems(loggedUser.getPersonData());
        
        //Remplie le tableau des rendez vous
        //rendezVousTable.setItems(loggedUser.getrendezVous());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        nomClientColumn.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
        
        //dateRendezVousColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        //nomClientRendezVousColumn.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
        
        // Clear client details.
        showClientDetails(null);
        
        // Listen for selection changes and show the person details when changed.
        visteTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showClientDetails(newValue));
    }    
    
    private void showClientDetails(Client client) {
        if (client != null) {
            // Remplie les labels et la liste avec les information clients
            nomClient.setText(client.getnomClient());
            villeClient.setText(client.getaddresse());
            telClient.setText(client.gettel());
            listView.getItems().clear();
            listView.getItems().addAll(client.listVisiteProperty());
            //pre-remplie les info du client pour la prise de rendez-vous
            lieu.setText(client.getaddresse());  
            clientRdv.setText(client.getnomClient());
            //Stock l'id du client selectionné
            this.idClient = client.getId();
        } else {
            nomClient.setText("");
            villeClient.setText("");
            telClient.setText("");
            listView.getItems().clear();
            lieu.setText("");
            clientRdv.setText("");
            this.idClient = 0;
        }
    }
    
    public void creezRendezVous(){
        //Appele le model user pour creez un rendez vous
        User loggedUser = application.getLoggedUser();
        
        boolean inputValid = true;
        
        if(this.idClient == 0){
            showAlert("Vous devez choisir un client dans la liste de gauche");
            inputValid = false;
        }
        
        if(dateRdv.getValue() == null){
            showAlert("Vous devez choisir une date");
            inputValid = false;
        }
 
        
        if(lieu.getText() == null || lieu.getText().trim().isEmpty()){
            showAlert("Vous devez entrez un lieu");
            inputValid = false;
        }
        
        if (contact.getText() == null || contact.getText().trim().isEmpty()) {
            showAlert("Vous devez renseignez la personne a contacter");            
            inputValid = false;
        }
        
        if(heureDebut.getCalendar() == null){
            showAlert("Vous devez renseignez l'heure");
            inputValid = false;
        }
        
        if(heureFin.getCalendar() == null){
            showAlert("Vous devez renseignez l'heure");
            inputValid = false; 
        }
 
        if(heureDebut.getCalendar().get(Calendar.HOUR_OF_DAY) >= heureFin.getCalendar().get(Calendar.HOUR_OF_DAY)){
            if(heureDebut.getCalendar().get(Calendar.MINUTE) >= heureFin.getCalendar().get(Calendar.MINUTE)){
                showAlert("L'heure de fin ne peut pas etre infèrieur a l'heure de fin");
                inputValid = false; 
            }
        }
        
        //TODO
        //Regarder si l'heure de debut est inferieur a l'heure de fin
        //regarder si l'heure de rendez vous n'est pas deja rentrez 
        
        if(inputValid){
            System.out.println("valid");
            String idUser = Integer.toString(loggedUser.getidUSer());
            String idClientR = Integer.toString(this.idClient);
            String dateR = dateRdv.getValue().toString();
            String heureD = heureDebut.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + heureDebut.getCalendar().get(Calendar.MINUTE);
            String heureF = heureFin.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + heureFin.getCalendar().get(Calendar.MINUTE);
                      
            loggedUser.setRendezVous(idUser, idClientR, lieu.getText(), comment.getText(), contact.getText(), dateR, heureD, heureF);
            //rafraichie la liste des rendez-vous
            //rendezVousTable.setItems(loggedUser.getNewrendezVous());
        }
    }
    
    //Affiche une fenetre d'erreur
    private void showAlert(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
