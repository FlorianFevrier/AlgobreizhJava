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
 *
 * @author florian
 */
public class LoginController implements Initializable {

    @FXML
    TextField userid;
    
    @FXML
    PasswordField password;
    
    @FXML
    Button login;
    
    @FXML
    Label errorMessage;
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setText("");
        userid.setPromptText("Email");
        password.setPromptText("Password");
    }
    
    //methode appelée quand on click sur le bouton login
    public void processLogin(ActionEvent event){
        if (application == null){
            errorMessage.setText("error");
        } else {
            if (!application.userLogging(userid.getText(), password.getText())){//Appele la méthode userLoggin du main
                errorMessage.setText("Username/Password is incorrect");
            } 
        }
    }
}
