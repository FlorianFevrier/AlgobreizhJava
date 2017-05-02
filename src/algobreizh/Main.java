/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh;

import algobreizh.view.VisiteController;
import algobreizh.view.LoginController;
import algobreizh.model.Authenticator;
import algobreizh.model.User;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author florian
 */
public class Main extends Application {
    
    private Stage stage;
    private User loggedUser;
    private final double WINDOW_WIDTH = 390.0;
    private final double WINDOW_HEIGHT = 300.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Algobreizh");
            stage.setWidth(WINDOW_WIDTH);
            stage.setHeight(WINDOW_HEIGHT);
            stage.setResizable(false);
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        
    //Appelée par la méthode processLogin du controller LoginController
    public boolean userLogging(String userId, String password){
        if (Authenticator.connexion(userId, password)) {//Utilise le modele Authenticator pour verifier si le login est bon
            loggedUser = new User(userId);//Stock un object User dans loggedUser
           // gotoProfile();//Si le login est bon on affiche la scene Profile
            gotoVisite();
            return true;
        } else {
            return false;
        }
    }
    
    //affiche la scene login
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("view/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Affiche la scene visite
    private void gotoVisite(){
        try {
            VisiteController visite = (VisiteController) replaceSceneContentVisite("view/visite.fxml");
            visite.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //remplace une scene par une autre
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        //Effet de transition en dégradée
        FadeTransition ft = new FadeTransition(Duration.millis(3000), page);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        Scene scene = new Scene(page, 390, 300);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
        private Initializable replaceSceneContentVisite(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
        } finally {
            in.close();
        } 
  
        //Effet de transition
        FadeTransition ft = new FadeTransition(Duration.millis(3000), page);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        
        Scene scene = new Scene(page, 1080, 720);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        return (Initializable) loader.getController();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    //Méthode appelée par ProfileController pour se deconnecter
    void userLogout() {
        loggedUser = null;
        gotoLogin();
    }
    
}
