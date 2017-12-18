/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.model;

import autistify.be.Playlist;
import autistify.be.Song;
import autistify.bll.PlaylistManager;
import autistify.bll.SongManager;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jesper Riis
 */
public class MainViewModel {
    
    private static MainViewModel instance;
    private SongManager sm;
    public ObservableList<Song> songList;
    public ObservableList<Song> selectedSong;
    private PlaylistManager pm;
    public ObservableList<Playlist> playlistList;
    public ObservableList<Playlist> selectedPlaylist;
    public ObservableList<Playlist> playlists;
    private boolean listEnded;

    public MainViewModel() throws IOException {
        this.sm = new SongManager();
        songList = FXCollections.observableArrayList();
        selectedSong = FXCollections.observableArrayList();
        this.pm = new PlaylistManager();
        playlistList = FXCollections.observableArrayList();
        selectedPlaylist = FXCollections.observableArrayList();
    }
    
    public ObservableList<Song> getSongs(){
        return songList;
    }
    
    public ObservableList<Song> getSelectedSong() {
        return selectedSong;
    }
    
    public void addSelectedSong(Song song) {
        selectedSong.add(song);
    }
    
    public void addSong(Song song){
        sm.add(song);
        songList.add(song);
    }
    
    public void loadSongs() {
        songList.clear();
        songList.addAll(sm.getAllSongs());
    }
    
    public void remove(Song selectedSong) {
        sm.remove(selectedSong);
        songList.remove(selectedSong);
    }
    
    public void edit(Song song) {
        sm.edit(song);
        songList.add(song);
        songList.clear();
        songList.addAll(sm.getAllSongs());
                
    }
    
   public static MainViewModel getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new MainViewModel();
        }
        return instance;
    }
   
   public void addPlaylist(Playlist playlist)
   {
       pm.add(playlist);
       playlistList.add(playlist);
       playlistList = FXCollections.observableArrayList(playlist);
   }
   
   public ObservableList<Playlist> getSelectedPlaylist() {
       return selectedPlaylist;
   }
   
   public void addSelectedPlaylist(Playlist playlist) {
       selectedPlaylist.add(playlist);
   }
   
   public ObservableList<Playlist> getPlaylists() {
       return playlistList;
   }
   
   public void loadPlaylist() {
       playlistList.clear();
       playlistList.addAll(pm.getAllPlaylists());
   }
   
   public void edit(Playlist playlist) {
       pm.edit(playlist);
       playlistList.add(playlist);
       playlistList.clear();
       playlistList.addAll(pm.getAllPlaylists());
   }
   
   public void remove(Playlist selectedPlaylist) {
       playlistList.remove(selectedPlaylist);
       pm.remove(selectedPlaylist);
   }

    public void addSongToPlaylist(Playlist playlist, Song song) {
        pm.addSongToPlaylist(playlist, song);
    }
    
    public void loadSongsInPlaylist() {
        pm.getAllSongsFromPlaylist();
    }

    public void removeSongPl(Song selectedSong, Playlist selectedPlaylist) {
        pm.removeSongPl(selectedSong, selectedPlaylist);
        playlistList.remove(selectedPlaylist.getSongList().remove(selectedSong));
    }
    
    public void pauseSong(Song songPlaying) {
        sm.pauseSong(songPlaying);
    }

    public void PlaySong(Song songPlaying) {
       sm.PlaySong(songPlaying);
    }

    public void setVolume(JFXSlider vSlider) {
        sm.setVolume(vSlider);
    }
    
    public void getOnEndOfMedia() {
        sm.OnEndOfMedia();
    }
//    
//    public void onEndOfMedia() {
//        sm.OnEndOfMedia();
//    }

    public MediaPlayer getMediaPlayer() {
        return sm.getMediaPlayer();
    }
    
    public boolean listEnded() {
        return listEnded;
    }

    public void setListEnded(boolean listEnded) {
        this.listEnded = listEnded;
    }
    
   
   
}
