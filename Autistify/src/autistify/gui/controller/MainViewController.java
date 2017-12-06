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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable {
    
    private SongDAO sDAO;
    private Song song;
    @FXML
    private TableView<?> songTable;
    @FXML
    private TableColumn<?, ?> songClmName;
    @FXML
    private TableColumn<?, ?> songClmArtist;
    @FXML
    private TableColumn<?, ?> songClmAlbum;
    @FXML
    private TableColumn<?, ?> songClmGenre;
    @FXML
    private TableColumn<?, ?> songClmTime;

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
    private void openAddSongView(ActionEvent event) {
            
    }
    
}
