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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe principale de l'application
 * 
 * 
 * @author florian
 */
public class Main extends Application {
    /**
     * La fenetre
     */
    private Stage stage;
    
    /**
     * L'utilisateur de l'application
     */
    private User loggedUser;
    
    /**
     * Le point d'entrée principal pour toutes les applications JavaFX.
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Algobreizh");
            stage.setWidth(390);
            stage.setHeight(300);
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
        
    /**
     * Regarde si l'utilisateur existe dans la base de donnée
     * Enregistre l'utisateur dans un objet User
     * Affiche la fenetre de gestion des visites
     * 
     * @param userId
     * @param password
     * @return vrai si l'utilisateur existe, sous la forme d'un booléen
     * 
     * @see Authenticator#connexion(java.lang.String, java.lang.String) 
     * @see User
     * @see Main#gotoVisite() 
     */
    public boolean userLogging(String userId, String password){
        if (Authenticator.connexion(userId, password)) {
            loggedUser = new User(userId);
            gotoVisite();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Affiche la scene login
     * 
     * @see Main#replaceSceneContent
     * @see LoginController#setApp(algobreizh.Main)
     */
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("view/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Affiche la scene visite
     * 
     * @see Main#replaceSceneContent
     * @see LoginController#setApp(algobreizh.Main)
     */
    private void gotoVisite(){
        try {
            VisiteController visite = (VisiteController) replaceSceneContentVisite("view/visite.fxml");
            visite.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Charge le fichier fxml dans une scene et l'affiche dans la fenetre
     * 
     * @param fxml
     *              Le nom de la scene a charger
     * 
     * @return Le controlleur de la scene à charger
     * @throws Exception 
     * 
     * @see Main#stage
     */
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
        
        Scene scene = new Scene(page, 390, 300);
        stage.setScene(scene);
        stage.sizeToScene();
        
        return (Initializable) loader.getController();
    }
    
    /**
     * Charge le fichier fxml dans une scene et l'affiche
     * dans la fenetre stage
     * 
     * @param fxml
     *              le nom de la scene à charger
     * @return Le controlleur de la scene
     * @throws Exception 
     * 
     * @see Main#stage
     */
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
  
        Scene scene = new Scene(page, 1180, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        
        return (Initializable) loader.getController();
    }
    
    /**
     * Retourne l'utilisateur connecté
     * 
     * @return l'utilisateur connecté, sous la forme d'un objet User
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Deconnecte l'utilisateur de l'application
     * et retourne a l'écran de connexion
     * 
     * @see Main#gotoLogin() 
     */
    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }
    
}
