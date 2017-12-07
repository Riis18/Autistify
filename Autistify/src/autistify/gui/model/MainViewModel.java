/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.model;

import autistify.be.Song;
import autistify.bll.SongManager;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jesper Riis
 */
public class MainViewModel {
    
    private static MainViewModel instance;
    private SongManager sm;
    public ObservableList<Song> songList;

    public MainViewModel() throws IOException {
        this.sm = new SongManager();
        songList = FXCollections.observableArrayList();
    }
    
    public ObservableList<Song> getSongs(){
        return songList;
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
    
   public static MainViewModel getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new MainViewModel();
        }
        return instance;
    }
}
