/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class AddSongViewController implements Initializable {

    @FXML
    private JFXTextField txtTitle;
    @FXML
    private JFXTextField txtArtist;
    @FXML
    private JFXTextField txtAlbum;
    @FXML
    private JFXTextField txtGenre;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private JFXTextField txtFilePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveSong(ActionEvent event) {
    }

    @FXML
    private void cancelAddSongView(ActionEvent event) {
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        
                try {
            FileChooser fileChooser = new FileChooser();
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            txtFilePath.setText(file.getPath());
        } catch (Exception e) {

        }

    }
    
}
