/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Playlist;
import autistify.gui.model.MainViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ollie
 */
public class PlaylistViewController implements Initializable
{

    @FXML
    private JFXButton saveBtnPL;
    @FXML
    private JFXButton cancelBtnPL;
    @FXML
    private JFXTextField txtPL;
    
    private MainViewModel mvm;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void savePlaylist(ActionEvent event)
    {
        if (!mvm.getSelectedPlaylist().isEmpty()) { 
            Playlist playlist = new Playlist();
            playlist.setName(txtPL.getText());
            playlist.setID(mvm.getSelectedPlaylist().get(0).getID());
            mvm.getSelectedPlaylist().clear();
            
        } else {
            Playlist playlist = new Playlist();
            playlist.setName(txtPL.getText());
            playlist.setID(-1);
            mvm.addPlaylist(playlist);
        }
        
        Stage stage = (Stage) saveBtnPL.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelPlaylist(ActionEvent event)
    {
        mvm.getSelectedPlaylist().clear();
        
        Stage stage = (Stage) cancelBtnPL.getScene().getWindow();
        stage.close();
    }
    
}
