/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.be;

/**
 *
 * @author ollie
 */
public class Playlist {
    
    public String name;
    private int id;
    
    public Playlist() {
        
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
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Playlist{" + "name=" + name + ", id=" + id + '}';
    }
    
}
