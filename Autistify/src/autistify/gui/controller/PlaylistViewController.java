/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void savePlaylist(ActionEvent event)
    {
//        mvm.addPlaylist();
        
        Stage stage = (Stage) saveBtnPL.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelPlaylist(ActionEvent event)
    {
        Stage stage = (Stage) cancelBtnPL.getScene().getWindow();
        stage.close();
    }
    
}
