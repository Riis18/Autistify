/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Playlist;
import autistify.be.Song;
import autistify.bll.SongFilter;
import autistify.bll.SongManager;
import autistify.dal.SongDAO;
import autistify.gui.model.MainViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ElementObservableListDecorator;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable {

    private Song song;
    private boolean songOrPsong;
    private Playlist playlist;
    private SongDAO sDAO;
    private MediaPlayer mp;
    private Media me;
    private String crntPath;
    private SongFilter sf;
    private ObservableList<Song> searchedSongs;
    private List<Song> songs;
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
    private TableView<Playlist> playlistTable;
    @FXML
    private TableColumn<Playlist, String> playlistClmName;
    @FXML
    private TableView<Song> playlistSongs;
    @FXML
    private JFXButton previousSong;
    @FXML
    private JFXButton nextSong;
    @FXML
    private JFXButton clearBtn;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private Label txtSongPlaying;
    @FXML
    private JFXSlider vSlider;
    @FXML
    private TableColumn<Song, String> psSongName;
    @FXML
    private TableColumn<Song, Integer> psSongTime;

    
    private SongManager sm;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            mvm = MainViewModel.getInstance();
            song =  new Song();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        songTable.setItems(mvm.getSongs());
                
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
        
        playlistTable.setItems(mvm.getPlaylists());
        playlistClmName.setCellValueFactory(
                new PropertyValueFactory("name"));
        mvm.loadPlaylist();
        
        psSongName.setCellValueFactory(
                new PropertyValueFactory("name"));
        psSongTime.setCellValueFactory(
                new PropertyValueFactory("trackLenght"));
        
        mvm.loadSongsInPlaylist();
        //this.sf = new SongFilter();
        songTable.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem != null) {
            playlistSongs.getSelectionModel().clearSelection();
            songOrPsong = true;
            System.out.println(songOrPsong);
        }
        });

       playlistSongs.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem != null) {
            songTable.getSelectionModel().clearSelection();
            songOrPsong = false;
            System.out.println(songOrPsong);
        }
        });
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
        boolean isSongOnPlaylist = false;
        Song selectedSong
                = songTable.getSelectionModel().getSelectedItem();
        for (int i = 0; i < mvm.getPlaylists().size(); i++) {
            if(!mvm.getPlaylists().get(i).getSongList().isEmpty()) {
                for (int j = 0; j < mvm.getPlaylists().get(i).getSongList().size(); j++) {
                    if(selectedSong.getId() == mvm.getPlaylists().get(i).getSongList().get(j).getId()) {
                    isSongOnPlaylist = true;
                }
              }
          }
        }
        if(isSongOnPlaylist == true) {
              Alert warningAlert = new Alert(Alert.AlertType.WARNING,"The song is part of a playlist. Remove the song from the playlist first");
              warningAlert.showAndWait();
            
        } else
        {
          Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
          deleteAlert.setContentText("Are you sure you want to delete " + selectedSong.getName() + "?");
          deleteAlert.showAndWait();
          if (deleteAlert.getResult() == ButtonType.YES) {
              mvm.remove(selectedSong);
          } else {
              deleteAlert.close();
                 }
            
        }
        mvm.loadSongs();
        
    }
 
    
    

    @FXML
    private void openEditSong(ActionEvent event) throws IOException {

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
    private void play(ActionEvent event) {
        if(songOrPsong == true) {
        Song songPlaying = songTable.getSelectionModel().getSelectedItem();
        if (playPause.getText().equals("Play")) {
            mvm.PlaySong(songPlaying);
            playPause.setText("Pause");
            txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());
        } else {
            playPause.setText("Play");
            mvm.setVolume(vSlider);
            mvm.pauseSong(songPlaying);
        }
        } else if (songOrPsong == false) {
        Song songPlaying = playlistSongs.getSelectionModel().getSelectedItem();
        if (playPause.getText().equals("Play")) {
            mvm.PlaySong(songPlaying);
            playPause.setText("Pause");
            txtSongPlaying.setText("Current Song - " + playlistSongs.getSelectionModel().getSelectedItem().getName());
        } else {
            playPause.setText("Play");
            mvm.setVolume(vSlider);
            mvm.pauseSong(songPlaying);
        }
        }
    }
    
    @FXML
    private void previousSong(ActionEvent event) {
        if(songOrPsong == true) {
        songTable.getSelectionModel().selectPrevious();
        Song songPlaying = songTable.getSelectionModel().getSelectedItem();
        mvm.PlaySong(songPlaying);
        playPause.setText("Pause");
        txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());
        } else if (songOrPsong == false) {
        playlistSongs.getSelectionModel().selectPrevious();
        Song songPlaying = playlistSongs.getSelectionModel().getSelectedItem();
        mvm.PlaySong(songPlaying);
        playPause.setText("Pause");
        txtSongPlaying.setText("Current Song - " + playlistSongs.getSelectionModel().getSelectedItem().getName());
            
        }
    }

    @FXML
    private void nextSong(ActionEvent event) {
        if(songOrPsong == true) {
        songTable.getSelectionModel().selectNext();
        Song songPlaying = songTable.getSelectionModel().getSelectedItem();
        mvm.PlaySong(songPlaying);
        playPause.setText("Pause");
        txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName()); 
        } else if(songOrPsong == false) {
        playlistSongs.getSelectionModel().selectNext();
        Song songPlaying = playlistSongs.getSelectionModel().getSelectedItem();
        mvm.PlaySong(songPlaying);
        playPause.setText("Pause");
        txtSongPlaying.setText("Current Song - " + playlistSongs.getSelectionModel().getSelectedItem().getName());
            
        }
    }

    @FXML
    private void Search(ActionEvent event) {
//        txtSearch.textProperty().addListener((ObservableValue<? extends String> listener, String oldQuery, String newQuery)
//                -> {
//            
//            System.out.println(newQuery);
//            if (newQuery != null) {
//            searchedSongs.setAll(sf.search(songs, newQuery));
//            songTable.setItems(searchedSongs);}
//        });
//        System.out.println(txtSearch.getText());
//
//        ObservableList<Song> newList = FXCollections.observableArrayList();
//        newList.addAll(sf.search(songs, txtSearch.getText()));
//        //searchedSongs.setAll(sf.search(songs, txtSearch.getText()));
//        songTable.getItems().setAll(newList);
//

    }

    @FXML
    private void vSlider(MouseEvent event) {
        mvm.setVolume(vSlider);
    }

    @FXML
    private void openAddPlaylistView(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/PlaylistView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.show();
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
        
        Playlist selectedPlaylist
                = playlistTable.getSelectionModel().getSelectedItem();
        if(!selectedPlaylist.getSongList().isEmpty()) {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING,"Playlist contains songs, please remove them first");
            warningAlert.showAndWait();
        } else {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES) {
            mvm.remove(selectedPlaylist);
        } else {
            deleteAlert.close();
        }
        }
    }

    @FXML
    private void addToPlaylist(ActionEvent event) {
        Song selectedSong = songTable.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = playlistTable.getSelectionModel().getSelectedItem();
        selectedPlaylist.getSongList().add(selectedSong);
        mvm.addSongToPlaylist(selectedPlaylist, selectedSong);
    }

    @FXML
    private void getPlaylistSong(MouseEvent event) {
        
        playlistSongs.setItems(FXCollections.observableArrayList(playlistTable.getSelectionModel().getSelectedItem().getSongList()));
    }

    @FXML
    private void SearchType(KeyEvent event)
    {
//        String filter = txtSearch.getText();
//        System.out.println(filter);
//        
//        if (filter.equals("")) {
//            songTable.getItems().setAll(mvm.getSongs());
//        }
//        else {
//        ObservableList<Song> newList = FXCollections.observableArrayList();
//        newList.addAll(sf.search(songs, txtSearch.getText()));
//        songTable.getItems().setAll(newList);
//        }
        
        
        
    }

    @FXML
    private void removeSongPl(ActionEvent event) {
        Song selectedSong
                = playlistSongs.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist
                = playlistTable.getSelectionModel().getSelectedItem();
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES) {
            mvm.removeSongPl(selectedSong, selectedPlaylist);
        } else {
            deleteAlert.close();
        }
    }

}
