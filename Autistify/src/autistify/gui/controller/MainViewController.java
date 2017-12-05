/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Song;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import autistify.dal.SongDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable {
    
    private SongDAO sDAO;
    private Song song;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            sDAO = new SongDAO();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void openNewSongView(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
//        AddSongViewController controller = fxmlLoader.getController();
//        controller.setModel(songmodel);
//        controller.setManager(playlistManager);
        Stage stage = new Stage();
        stage.setTitle("Add Song Window");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    
}
