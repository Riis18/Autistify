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
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Iterator;
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
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
<<<<<<< HEAD
import javafx.scene.input.KeyEvent;
=======
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
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
    private Playlist playlist;
<<<<<<< HEAD
    private SongDAO sDAO;
    private MediaPlayer mp;
    private Media me;
    private String crntPath;
    private SongFilter sf;
    private ObservableList<Song> searchedSongs;
    private List<Song> songs;
=======
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
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
    @FXML
    private TableColumn<Song, String> psSongName;
    @FXML
    private TableColumn<Song, Integer> psSongTime;
<<<<<<< HEAD
;
    @FXML
    private JFXButton cancelRmvSongPl;
    
    private SongManager sm;
=======
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op

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
            this.sm = new SongManager();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
        
        songs = sm.getAllSongs();
        songTable.getItems().setAll(songs);
                
        
=======
        songTable.setItems(mvm.getSongs());
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
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
        
<<<<<<< HEAD
        mvm.loadSongsInPlaylist();
        
        this.sf = new SongFilter();
=======
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
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
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure you want to delete " + selectedSong.getName() + "?");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES) {
            mvm.remove(selectedSong);
        } else {
            deleteAlert.close();
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

        if (playPause.getText().equals("Play")) {
            String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
            String pPath = new File(playlistSongs.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
            if (crntPath == null || !crntPath.equals(path)) {
                crntPath = path;
                me = new Media(new File(path).toURI().toString());
                if (mp != null) {
                    mp.dispose();
                }
                mp = new MediaPlayer(me);
            }
            playPause.setText("Pause");
            txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());

            mp.play();
        } else {
            playPause.setText("Play");
            
            mp.setVolume(vSlider.getValue() / 100);

            mp.pause();
        }

    }

    @FXML
    private void previousSong(ActionEvent event) {
        mp.getOnEndOfMedia();
        songTable.getSelectionModel().selectPrevious();
        String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
        if (crntPath == null || !crntPath.equals(path)) {
            crntPath = path;
            me = new Media(new File(path).toURI().toString());
            if (mp != null) {
                mp.dispose();
            }
            mp = new MediaPlayer(me);
        }
        playPause.setText("Pause");
        
        txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());
        
        mp.setVolume(vSlider.getValue() / 100);

        mp.play();
    }

    @FXML
    private void nextSong(ActionEvent event) {

        mp.getOnEndOfMedia();
        songTable.getSelectionModel().selectNext();
        String path = new File(songTable.getSelectionModel().getSelectedItem().getPath()).getAbsolutePath();
        if (crntPath == null || !crntPath.equals(path)) {
            crntPath = path;
            me = new Media(new File(path).toURI().toString());
            if (mp != null) {
                mp.dispose();
            }
            mp = new MediaPlayer(me);
        }
        playPause.setText("Pause");
        
        txtSongPlaying.setText("Current Song - " + songTable.getSelectionModel().getSelectedItem().getName());
        
        mp.setVolume(vSlider.getValue() / 100);

        mp.play();
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
        System.out.println(txtSearch.getText());

        ObservableList<Song> newList = FXCollections.observableArrayList();
        newList.addAll(sf.search(songs, txtSearch.getText()));
        //searchedSongs.setAll(sf.search(songs, txtSearch.getText()));
        songTable.getItems().setAll(newList);


    }

    @FXML
    private void vSlider(MouseEvent event) {
        vSlider.setValue(mp.getVolume() * 100);
        vSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(vSlider.getValue() / 100);
            }
        });
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
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES) {
            mvm.remove(selectedPlaylist);
        } else {
            deleteAlert.close();
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

<<<<<<< HEAD
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

    @FXML
    private void SearchType(KeyEvent event)
    {
        String filter = txtSearch.getText();
        System.out.println(filter);
        
        if (filter.equals("")) {
            songTable.getItems().setAll(mvm.getSongs());
        }
        else {
        ObservableList<Song> newList = FXCollections.observableArrayList();
        newList.addAll(sf.search(songs, txtSearch.getText()));
        songTable.getItems().setAll(newList);
        }
        
        
        
    }

=======
>>>>>>> parent of 52b76df... kl 4 om natten.. fik ordnet playlisten.. fuck mig + ryddet op
}
