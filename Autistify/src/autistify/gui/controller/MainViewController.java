/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Song;
import autistify.bll.SongFilter;
import autistify.dal.SongDAO;
import autistify.gui.model.MainViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable
{

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
    @FXML
    private JFXButton playPause;
    @FXML
    private TableView<?> playlistTable;
    @FXML
    private TableView<?> playlistSongs;

    private SongDAO sDAO;

    private MediaPlayer mp;

    private Media me;

    private String crntPath;
    @FXML
    private JFXButton previousSong;
    @FXML
    private JFXButton nextSong;

    private SongFilter sf;
    @FXML
    private JFXButton clearBtn;
    @FXML
    private JFXTextField txtSearch;
    private ObservableList<Song> searchedSongs;
    List<Song> songs;
    @FXML
    private Label txtSongPlaying;
    @FXML
    private JFXSlider vSlider;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        try
        {
            mvm = MainViewModel.getInstance();
        } catch (IOException ex)
        {
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
    private void openAddSongView(ActionEvent event) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void deleteSong(ActionEvent event)
    {
        Song selectedSong
                = songTable.getSelectionModel().getSelectedItem();
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure you want to delete " + selectedSong.getName() + "?");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES){
            mvm.remove(selectedSong);
        } else {
            deleteAlert.close();
        }
    }

    @FXML
    private void openEditSong(ActionEvent event) throws IOException
    {

        Song song = songTable.getSelectionModel().getSelectedItem();
        mvm.addSelectedSong(song);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddSongViewController controller = fxmlLoader.getController();
        controller.setModel(mvm);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }

    /**
     * Plays and pauses the song selected in song table
     *
     * @param event
     */
    @FXML
    private void play(ActionEvent event)
    {

        if (playPause.getText().equals("Play"))
        {
            String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
            if (crntPath == null || !crntPath.equals(path))
            {
                crntPath = path;
                me = new Media(new File(path).toURI().toString());
                if (mp != null)
                {
                    mp.dispose();
                }
                mp = new MediaPlayer(me);
            }
            playPause.setText("Pause");
            txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());

            mp.play();
        } else
        {
            playPause.setText("Play");

            mp.pause();
        }

    }

    @FXML
    private void previousSong(ActionEvent event)
    {
        mp.getOnEndOfMedia();
        songTable.getSelectionModel().selectPrevious();
        String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
        if (crntPath == null || !crntPath.equals(path))
        {
            crntPath = path;
            me = new Media(new File(path).toURI().toString());
            if (mp != null)
            {
                mp.dispose();
            }
            mp = new MediaPlayer(me);
        }
        playPause.setText("Pause");
        
        mp.play();
    }

    @FXML
    private void nextSong(ActionEvent event)
    {

        mp.getOnEndOfMedia();
        songTable.getSelectionModel().selectNext();
        String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
        if (crntPath == null || !crntPath.equals(path))
        {
            crntPath = path;
            me = new Media(new File(path).toURI().toString());
            if (mp != null)
            {
                mp.dispose();
            }
            mp = new MediaPlayer(me);
        }
        playPause.setText("Pause");
        
        mp.play();
    }

    @FXML
    private void Search(ActionEvent event)
    {
        txtSearch.textProperty().addListener((ObservableValue<? extends String> listener, String oldQuery, String newQuery)
        -> {
            searchedSongs.setAll(sf.search(songs, newQuery));
            songTable.setItems(searchedSongs);
        });
        
    }
    @FXML
    private void vslider(ActionEvent event) {
        vSlider.setValue(mp.getVolume() * 100);
        vSlider.valueProperty().addListener(new InvalidationListener() {
            
            @Override
            public void invalidated(Observable observable) {
                    mp.setVolume(vSlider.getValue() / 100);
            }
        });
   
            }

}
