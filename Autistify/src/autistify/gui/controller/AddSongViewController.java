/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Song;
import autistify.gui.model.MainViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.File; 
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class AddSongViewController implements Initializable {

    private Song song;
    private MainViewModel mvm;
    
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
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton saveBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        mvm = MainViewModel.getInstance();
    }    

    @FXML
    private void saveSong(ActionEvent event) {
        
        Song song = new Song(txtTitle.getText(), txtAlbum.getText(), txtArtist.getText(), txtFilePath.getText(), txtGenre.getText(), Integer.parseInt(txtTime.getText()));
        mvm.addSong(song);
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelAddSongView(ActionEvent event) {
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        
        try {
            FileChooser fileChooser = new FileChooser();
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            
            AudioFile f;
            f = AudioFileIO.read(file);
            Tag t = f.getTagOrCreateAndSetDefault();
            txtTime.setText(Integer.toString(f.getAudioHeader().getTrackLength()));
            txtArtist.setText(t.getFirst(FieldKey.ARTIST));
            txtAlbum.setText(t.getFirst(FieldKey.ALBUM));
            txtTitle.setText(t.getFirst(FieldKey.TITLE));
            txtGenre.setText(t.getFirst(FieldKey.GENRE));
            txtFilePath.setText(file.getPath());
            f.commit();      
             
        } catch (Exception e) {

        }

    }
    
}
