/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Song;
import autistify.gui.model.MainViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable {
    
    private Song song;
    private MainViewModel mvm;
    @FXML
    private TableView<Song> songTable;
    @FXML
    private TableColumn<Song, String> songClmName;
    @FXML
    private TableColumn<Song, String> songClmArtist;
    @FXML
    private TableColumn<Song, String> songClmAlbum;
    @FXML
    private TableColumn<Song, String> songClmGenre;
    @FXML
    private TableColumn<Song, Integer> songClmTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            mvm = MainViewModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        songTable.setItems(mvm.getSongs());
        songClmName.setCellValueFactory(
                new PropertyValueFactory("name"));
        songClmArtist.setCellValueFactory(
                new PropertyValueFactory("artist"));
        songClmAlbum.setCellValueFactory(
                new PropertyValueFactory("album"));
        songClmGenre.setCellValueFactory(
                new PropertyValueFactory("genre"));
        songClmTime.setCellValueFactory(
                new PropertyValueFactory("trackLenght"));
        mvm.loadSongs();
    }    

    @FXML
    private void openAddSongView(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        Stage stage = new Stage();
 
        stage.setScene(new Scene(root1));
        stage.show();
            
    }

    @FXML
    private void deleteSong(ActionEvent event) {
        Song selectedSong
                = songTable.getSelectionModel().getSelectedItem();
        mvm.remove(selectedSong);
    }

    @FXML
    private void openEditSong(ActionEvent event) {
    }
    
}
