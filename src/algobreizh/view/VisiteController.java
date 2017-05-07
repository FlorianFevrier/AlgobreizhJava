/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.view;

import algobreizh.Main;
import algobreizh.model.User;
import algobreizh.model.Client;
import algobreizh.model.RendezVous;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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

/**
 * FXML Controller class,
 * Le contrôleur de la scène visite.
 * 
 * @author florian
 */
public class VisiteController implements Initializable {
    /**
     * Affiche le nom du commercial.
     */
    @FXML
    private Label nomCommercial;
    
    /**
     * Affiche la zone géographique du commercial.
     */
    @FXML
    private Label zoneCommercial;
   
    /**
     * Affiche le nom du client sélectionné.
     */
    @FXML
    private Label nomClient;
    
    /**
     * Affiche l'adresse du client sélectionné.
     */
    @FXML
    private Label villeClient;
    
    /**
     * Affiche le téléphone du client sélectionné.
     */
    @FXML
    private Label telClient;
    
    /**
     * Tableau qui contient l'historique des dernières visites.
     */
    @FXML
    private TableView<Client> visteTable;
    
    /**
     * Colonne qui contient la date des dernières visites.
     */
    @FXML
    private TableColumn<Client, String> dateColumn;
    /**
     * Colonne qui contient le nom des clients.
     */
    @FXML
    private TableColumn<Client, String> nomClientColumn;
    
    /**
     * Tableau qui contient la liste des rendez-vous.
     */
    @FXML
    private TableView<RendezVous> rendezVousTable;
    
    /**
     * Colonne qui contient la date des rendez-vous.
     */
    @FXML
    private TableColumn<RendezVous, String> dateRendezVousColumn;
    /**
     * Colonne qui contient le nom des client pour les rendez-vous.
     */
    @FXML
    private TableColumn<RendezVous, String> nomClientRendezVousColumn;
    /**
     * Colonne qui contient l'heure à laquel commence un rendez-vous.
     */
    @FXML
    private TableColumn<RendezVous, String> heureDebutColumn;
    /**
     * Colonne qui contient l'heure à laquel se termine un rendez-vous.
     */
    @FXML
    private TableColumn<RendezVous, String> heureFinColumn;
    /**
     * Affiche l'historique de visite du client.
     */
    @FXML
    private ListView<String> listView= new ListView(); 
    
    /**
     * Zone de saisie de l'adresse ou on prend le rendez-vous.
     */
    @FXML
    private TextField lieu;
    
    /**
     * Zone de saisie de la personne a contacter pour le rendez-vous.
     */
    @FXML
    private TextField contact;
    
    /**
     * Calendrier pour choisir la date du rendez-vous.
     */
    @FXML
    private DatePicker dateRdv;
    
    /**
     * Permet de choisir à quel heure débute le rendez-vous.
     */
    @FXML
    private CalendarTimeTextField heureDebut;
    
    /**
     * Permet de choisir à quel heure termine le rendez-vous.
     */
    @FXML
    private CalendarTimeTextField heureFin;
    
    /**
     * Zone de saisie pour ajouter un commentaire sur le rendez-vous.
     */
    @FXML
    private TextArea comment;
    
    /**
     * Zone de Saisie du nom du client pour le rendez-vous.
     */
    @FXML
    private TextField clientRdv;
    /**
     * Référence de la classe Main.
     */
    private Main application;
    /**
     * L'ID du client selectionné.
     */
    private int idClient;
    
    /**
    * Est appelée par la classe Main, pour lui passer une référence vers elle même,
    * cela permet de récuperer certains attributs et méthodes de la classe Main.
    * 
    * @param application
    *          Instance de la classe Main.
    *              
    * @see Main
    */
    public void setApp(Main application){
        this.application = application;
        
        //Remplie les informations du commercial
        User loggedUser = application.getLoggedUser();
        nomCommercial.setText(loggedUser.getNom());
        zoneCommercial.setText(loggedUser.getZone());
        
        //Remplie le tableau des derniere visite
        visteTable.setItems(loggedUser.getPersonData());
        
        //Remplie le tableau des rendez vous
        rendezVousTable.setItems(loggedUser.getrendezVous());
    }
    
    /**
     * Initialise le contrôleur.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        nomClientColumn.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
        
        dateRendezVousColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        nomClientRendezVousColumn.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
        heureDebutColumn.setCellValueFactory(cellData -> cellData.getValue().heureDebutProperty());
        heureFinColumn.setCellValueFactory(cellData -> cellData.getValue().heureFinProperty());
        
        // Clear client details.
        showClientDetails(null);
        
        // Listen for selection changes and show the person details when changed.
        visteTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showClientDetails(newValue));
    }    
    
    /**
     * Remplie les label et les textField avec les information du client
     * et enregistre l'id du client selectionné.
     * 
     * @param client
     *          Le client selectionné dans la liste.
     */
    private void showClientDetails(Client client) {
        if (client != null) {
            nomClient.setText(client.getnomClient());
            villeClient.setText(client.getadresse());
            telClient.setText(client.gettel());
            listView.getItems().clear();
            listView.getItems().addAll(client.listVisiteProperty());
            lieu.setText(client.getadresse());  
            clientRdv.setText(client.getnomClient());
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
    
    /**
     * Créer un rendez dans la base de données.<br>
     * Récupère l'utilisateur et vérifie si les paramètres entrés 
     * sont valides avant l'insertion dans la base de données.<br>
     * On actualise la liste des rendez-vous après l'enregistrement du rendez-vous.
     * 
     * @see Main#loggedUser
     * @see User#setRendezVous(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void creezRendezVous(){
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
            String idUser = Integer.toString(loggedUser.getidUSer());
            String idClientR = Integer.toString(this.idClient);
            String dateR = dateRdv.getValue().toString();
            String heureD = heureDebut.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + heureDebut.getCalendar().get(Calendar.MINUTE);
            String heureF = heureFin.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + heureFin.getCalendar().get(Calendar.MINUTE);
                      
            loggedUser.setRendezVous(idUser, idClientR, lieu.getText(), comment.getText(), contact.getText(), dateR, heureD, heureF);

            rendezVousTable.setItems(loggedUser.getNewrendezVous());
        }
    }
    
    /**
     * Affiche un message d'erreur dans une fenêtre de dialogue.
     * 
     * @param message
     *             Le message à afficher en cas d'erreur.
     */
    private void showAlert(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Se déconnecte de l'application.
     * 
     * @param event
     * @see Main#userLogout() 
     */
    public void processLogout(ActionEvent event) {
        if (application == null){
            return;
        }
        application.userLogout();
    }
    
    /**
     * Ferme l'application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
