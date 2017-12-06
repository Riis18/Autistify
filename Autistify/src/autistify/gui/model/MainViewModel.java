/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.gui.model;

import autistify.be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jesper Riis
 */
public class MainViewModel {
    
    private static MainViewModel instance;
    public ObservableList<Song> songList;

    public MainViewModel() {
        songList = FXCollections.observableArrayList();
    }
    
    public ObservableList<Song> getSongs(){
        return songList;
    }
    
    public void addSong(Song song){
        songList.add(song);
    }
    
   public static MainViewModel getInstance()
    {
        if (instance == null)
        {
            instance = new MainViewModel();
        }
        return instance;
    }
}
