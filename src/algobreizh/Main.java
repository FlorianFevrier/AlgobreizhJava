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
 * Classe principale de l'application.
 * 
 * 
 * @author florian
 */
public class Main extends Application {
    /**
     * La fênetre.
     */
    private Stage stage;
    
    /**
     * L'utilisateur de l'application.
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
     * Regarde si l'utilisateur existe dans la base de données.<br>
     * Enregistre l'utilisateur dans un objet User.<br>
     * Affiche la fenêtre de gestion des visites.
     * 
     * @param userId
     *          L'email du commercial.
     * @param password
     *          Le mot de passe du commercial.
     * @return vrai si l'utilisateur existe sinon faux, sous la forme d'un booléen.
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
     * Affiche l'écran de connexion.
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
     * Affiche l'écran de gestion des rendez-vous.
     * 
     * @see Main#replaceSceneContentVisite
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
     * <p>Remplace une scène par une autre.</p>
     * <ul>
     * <li>Charge le fichier fxml dans un layout AnchorPane.</li>
     * <li>L'ajoute à la scène.</li>
     * <li>Ajoute la scène à la fênetre.</li>
     * 
     * </ul>
     * @param fxml
     *              le nom du fichier fxml à charger.
     * 
     * @return Le contrôleur de la scène.
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
     * <p>Remplace une scène par une autre.</p>
     * <ul>
     * <li>Charge le fichier fxml dans un layout BorderPane.</li>
     * <li>L'ajoute à la scène.</li>
     * <li>Ajoute la scène à la fênetre.</li>
     * <li>Centre la fênetre.</li>
     * </ul>
     * 
     * @param fxml
     *          Le nom du fichier fxml à charger.
     * 
     * @return Le contrôleur de la scène.
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
     * Retourne l'utilisateur connecté.
     * 
     * @return L'utilisateur connecté, sous la forme d'un objet User.
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Déconnecte l'utilisateur de l'application
     * et retourne à l'écran de connexion.
     * 
     * @see Main#gotoLogin() 
     */
    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }
    
}
