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

    /*
    * Constructor for MainViewModel
    */
    public MainViewModel() throws IOException {
        this.sm = new SongManager();
        songList = FXCollections.observableArrayList();
        selectedSong = FXCollections.observableArrayList();
        this.pm = new PlaylistManager();
        playlistList = FXCollections.observableArrayList();
        selectedPlaylist = FXCollections.observableArrayList();
    }
    
    /*
    * returns the list of songs
    */
    public ObservableList<Song> getSongs(){
        return songList;
    }
    
    /*
    * returns the selected song
    */
    public ObservableList<Song> getSelectedSong() {
        return selectedSong;
    }
    
    /*
    * Adds the selected song to a list
    */
    public void addSelectedSong(Song song) {
        selectedSong.add(song);
    }
    
    /*
    * Sends information of a song and adds it to a list
    */
    public void addSong(Song song){
        sm.add(song);
        songList.add(song);
    }
    
    /*
    * clears the songlist and adds all songs from song manager
    */
    public void loadSongs() {
        songList.clear();
        songList.addAll(sm.getAllSongs());
    }
    
    /*
    * Sends information on the selected song and removes it from the list
    */
    public void remove(Song selectedSong) {
        sm.remove(selectedSong);
        songList.remove(selectedSong);
    }
    
    /*
    * Sends information on the selected song and clear the list and adds all
    * song to the list again for the view to be updated correct
    */
    public void edit(Song song) {
        sm.edit(song);
        songList.add(song);
        songList.clear();
        songList.addAll(sm.getAllSongs());
                
    }
    
    /*
    * gets the instance of MainViewModel
    */
   public static MainViewModel getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new MainViewModel();
        }
        return instance;
    }
   
   /*
   * Sends information of the playlist and adds it to a list
   */
   public void addPlaylist(Playlist playlist)
   {
       pm.add(playlist);
       playlistList.add(playlist);
   }
   
   /*
   * returns the list
   */
   public ObservableList<Playlist> getSelectedPlaylist() {
       return selectedPlaylist;
   }
   
   /*
   * adds the selected playlist to a list
   */
   public void addSelectedPlaylist(Playlist playlist) {
       selectedPlaylist.add(playlist);
   }
   
   /*
   * gets all playlists
   */
   public ObservableList<Playlist> getPlaylists() {
       return playlistList;
   }
   
   /*
   * clears the playlist list and adds all playlist from playlist manager
   */
   public void loadPlaylist() {
       playlistList.clear();
       playlistList.addAll(pm.getAllPlaylists());
   }
   
   /*
   * Sends information on the selected playlist and adds it to the list
   * clears the list and adds all playlist for the view to be updated correct
   */
   public void edit(Playlist playlist) {
       pm.edit(playlist);
       playlistList.add(playlist);
       playlistList.clear();
       playlistList.addAll(pm.getAllPlaylists());
   }
   
   /*
   * Sends information of the selected playlist and removes it from the list
   */
   public void remove(Playlist selectedPlaylist) {
       playlistList.remove(selectedPlaylist);
       pm.remove(selectedPlaylist);
   }

   /*
   * Sends information of the selected playlist and song
   */
    public void addSongToPlaylist(Playlist playlist, Song song) {
        pm.addSongToPlaylist(playlist, song);
    }
    
    /*
    * gets all songs from a playlist
    */
    public void loadSongsInPlaylist() {
        pm.getAllSongsFromPlaylist();
    }

    /*
    * Sends information on selected song and playlist and removes the song
    * from that playlist
    */
    public void removeSongPl(Song selectedSong, Playlist selectedPlaylist) {
        pm.removeSongPl(selectedSong, selectedPlaylist);
        playlistList.remove(selectedPlaylist.getSongList().remove(selectedSong));
    }
    
    /*
    * Pauses the song
    */
    public void pauseSong(Song songPlaying) {
        sm.pauseSong(songPlaying);
    }

    /*
    * Plays the song
    */
    public void PlaySong(Song songPlaying) {
       sm.PlaySong(songPlaying);
    }

    /*
    * Sets the volume of the song
    */
    public void setVolume(JFXSlider vSlider) {
        sm.setVolume(vSlider);
    }
    
    /*
    * gets the media player
    */
    public MediaPlayer getMediaPlayer() {
        return sm.getMediaPlayer();
    }
    
    /*
    * returns list
    */
    public boolean listEnded() {
        return listEnded;
    }

    /*
    * sees if list has ended
    */
    public void setListEnded(boolean listEnded) {
        this.listEnded = listEnded;
    }
    
   
   
}
