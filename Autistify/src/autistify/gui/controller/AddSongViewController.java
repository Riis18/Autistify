/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Song;
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
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
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
    @FXML
    private JFXButton cancelBtn;

    
    
    private Song song = new Song();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Song song = new Song();
       
    }    

    @FXML
    private void saveSong(ActionEvent event) {
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
            song.setArtist(t.getFirst(FieldKey.ARTIST));
            song.setAlbum(t.getFirst(FieldKey.ALBUM));
            song.setName(t.getFirst(FieldKey.TITLE));
            song.setGenre(t.getFirst(FieldKey.GENRE));
            f.commit();
            txtTitle.setText(song.getName());
            txtArtist.setText(song.getArtist());
            txtAlbum.setText(song.getAlbum());
            txtGenre.setText(song.getGenre());
            txtFilePath.setText(file.getPath());
             
        } catch (Exception e) {

        }

    }
    
//    private void getMetaData() throws InvalidAudioFrameException, CannotReadException, IOException, TagException, ReadOnlyFileException{
//       
//       txtFilePath.getText();
//       AudioFile f;
//       f = AudioFileIO.read(txtFilePath.getText().);
//       Tag t = f.getTagOrCreateAndSetDefault();
//       song.setArtist(t.getFirst(FieldKey.ARTIST));
//       song.setAlbum(t.getFirst(FieldKey.ALBUM));
//       song.setName(t.getFirst(FieldKey.TITLE));
//       song.setGenre(t.getFirst(FieldKey.GENRE));
//       
//       
//    }
}
