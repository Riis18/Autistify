/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.be;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ollie //hej
 */
public class Playlist {
    
    public String name;
    private int id;
    private List<Song> songList;
    
    public Playlist() {
        this.songList = new ArrayList();
    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Song> getSongList() {
        return songList;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Playlist{" + "name=" + name + ", id=" + id + '}';
    }
    
}
