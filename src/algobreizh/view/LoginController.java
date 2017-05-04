/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.view;

import algobreizh.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Le controlleur de la scene login
 * 
 * @author florian
 */
public class LoginController implements Initializable {
    /**
     * La zone de texte ou l'utilisateur rentre son e-mail
     */
    @FXML
    TextField userid;
    
    /**
     * La zone de texte ou l'utilisateur rentre son mot de passe
     */
    @FXML
    PasswordField password;
    
    /**
     * Le bouton qui va appeler la methode processLogin
     * 
     * @see LoginController#processLogin(javafx.event.ActionEvent) 
     */
    @FXML
    Button login;
    
    /**
     * Le Label qui sert a afficher un message en cas d'erreur
     * lors de la connexion
     */
    @FXML
    Label errorMessage;
    
    /**
     * Reference to the main application.
     */
    private Main application;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param application
     */
    public void setApp(Main application){
        this.application = application;
    }
    
    /**
     * Initialise le contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setText("");
        userid.setPromptText("Email");
        password.setPromptText("Password");
    }
    
    /**
     * Action qui se produit quand on clic sur le bouton login.
     * Affiche un message d'erreur si les identifiants entrées sont valides
     * 
     * @see Main#userLogging(java.lang.String, java.lang.String)
     * @see LoginController#errorMessage
     * 
     */
    public void processLogin(ActionEvent event){
        if (application == null){
            errorMessage.setText("error");
        } else {
            if (!application.userLogging(userid.getText(), password.getText())){
                errorMessage.setText("Utilisateur/Mot de passe incorrect");
            } 
        }
    }
}
