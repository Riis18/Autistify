/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.controller;

import autistify.be.Playlist;
import autistify.be.Song;
import autistify.bll.SongFilter;
import autistify.gui.model.MainViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper Riis
 */
public class MainViewController implements Initializable {


    private boolean songOrPsong;
    private final ObservableList<Song> searchedSongs;
    private final SongFilter songFilter;
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


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public MainViewController() {
        this.searchedSongs = FXCollections.observableArrayList();
        this.songFilter = new SongFilter();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Gets the instance of MainViewModel
        try {
            mvm = MainViewModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Sets the items on song table
        songTable.setItems(mvm.getSongs());;
        // Sets all cells to their values fir song table
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
        // Loads all songs
        mvm.loadSongs();
        // Sets items in the playlist table
        playlistTable.setItems(mvm.getPlaylists());
        // Sets the cells to their values for playlist table
        playlistClmName.setCellValueFactory(
                new PropertyValueFactory("name"));
        // Loads all playlist
        mvm.loadPlaylist();
        // Sets the cells to their values for playlist song table
        psSongName.setCellValueFactory(
                new PropertyValueFactory("name"));
        psSongTime.setCellValueFactory(
                new PropertyValueFactory("trackLenght"));
        // Loads all the songs in every playlist
        mvm.loadSongsInPlaylist();
        // Checks to see which table is selected between playlist song table and song table
        checkWhichTableIsSelected();
        // Filter to search through songs in song table
        searchSong();
    }

    /*
    * Opens the add song view
    */
    @FXML
    private void openAddSongView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }

    /*
    * Checks to see if there is a playlist that contains the song and if there is
    * a alert will come up, if it isn't an alert come up to ask if you want to delete
    * the song selected.
    */
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
        
    }
 
    
    /*
    * Opens the add song view
    */
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
     * if the song is selected from song list it will play and pause a song from that list
     * and the same from a playlist song
     * and if its from a playlist song it will autoplay the next song on the list
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
        setOnMediaEnd();
    }
    
    /*
    * Selects the precious song on the list. And checks to see what table is selected
    */
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

    /*
    * Selects the next song on the list. And checks to see what table is selected
    */
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

    /*
    * Searches through songs in song table
    */
    private void searchSong() {
        txtSearch.textProperty().addListener((ObservableValue<? extends String> listener, String oldQuery, String newQuery)
        -> {
        searchedSongs.setAll(songFilter.search(mvm.getSongs(), newQuery));
        songTable.setItems(searchedSongs);
        });
    }

    /*
    * sets the volume slider
    */
    @FXML
    private void vSlider(MouseEvent event) {
        mvm.setVolume(vSlider);
    }

    /*
    * Opens the add playlistView
    */
    @FXML
    private void openAddPlaylistView(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/PlaylistView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.show();
    }

    /*
    * Deletes the playlist, if there is songs on the playlist an alert will
    * come.
    */
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

    /*
    * Adds the selected song to the selected playlist
    */
    @FXML
    private void addToPlaylist(ActionEvent event) {
        Song selectedSong = songTable.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = playlistTable.getSelectionModel().getSelectedItem();
        selectedPlaylist.getSongList().add(selectedSong);
        mvm.addSongToPlaylist(selectedPlaylist, selectedSong);
    }

    /*
    * Sets the songs on playlist song table by clicking on a playlist
    */
    @FXML
    private void getPlaylistSong(MouseEvent event) {
        
        playlistSongs.setItems(FXCollections.observableArrayList(playlistTable.getSelectionModel().getSelectedItem().getSongList()));
    }

    /*
    * Removes the selected song from the selected playlist
    */
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
    
    /*
    * Sets on Media end and tries to run autoPlay
    */
    public void setOnMediaEnd() {
        mvm.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                try {
                    autoPlay();
                } catch (SQLServerException sQLServerException) {
                }
            }
        });
    }
    
    /*
    * Checks to see if the list has ended else play next song
    */
    private void autoPlay() throws SQLServerException {
            int listSize = mvm.getSongs().size() -1;
            if(listSize == playlistSongs.getSelectionModel().getSelectedIndex()) {
                mvm.setListEnded(true);
             }
            playlistSongs.getSelectionModel().selectNext();
            Song song = playlistSongs.getSelectionModel().getSelectedItem();
            txtSongPlaying.setText("Current Song - " + playlistSongs.getSelectionModel().getSelectedItem().getName());
            mvm.PlaySong(song);
    }
    
    /*
    * Checks to see which table is selected
    */
    private void checkWhichTableIsSelected() {
        songTable.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem != null) {
            playlistSongs.getSelectionModel().clearSelection();
            songOrPsong = true;
        }
        });

       playlistSongs.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem != null) {
            songTable.getSelectionModel().clearSelection();
            songOrPsong = false;
        }
        });
    }

    /*
    * Adds the selected playlist to a list and opens the playlistView
    */
    @FXML
    private void renamePlaylist(ActionEvent event) throws IOException {
        Playlist playlist = playlistTable.getSelectionModel().getSelectedItem();
        mvm.addSelectedPlaylist(playlist);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/autistify/gui/view/PlaylistView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        PlaylistViewController controller = fxmlLoader.getController();
        controller.setModel(mvm);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }
}
