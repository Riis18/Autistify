/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ollie //hej
 */
public class Playlist {
    
    public String name;
    private int id;
    private final List<Song> songList;
    
    // Constructer for Playlist
    public Playlist() {
        this.songList = new ArrayList();
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getID() {
        return id;
    }
    
    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /*
     * returns songList
     */
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
