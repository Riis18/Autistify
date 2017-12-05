/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.be;

/**
 *
 * @author Jesper Riis
 */
public class Song {
    
       public String name;
       public String album;
       public String artist;
       public String path;
       public int trackLenght;
       private int id;

    public Song(int id,String name, String album, String artist, String path, int trackLenght) {
        
        this.id = id;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.path = path;
        this.trackLenght = trackLenght;
       
    }
    
        /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }


    /**
     * Get the value of trackLenght
     *
     * @return the value of trackLenght
     */
    public int getTrackLenght() {
        return trackLenght;
    }

    /**
     * Set the value of trackLenght
     *
     * @param trackLenght new value of trackLenght
     */
    public void setTrackLenght(int trackLenght) {
        this.trackLenght = trackLenght;
    }


    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        this.path = path;
    }


    /**
     * Get the value of album
     *
     * @return the value of album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Set the value of album
     *
     * @param album new value of album
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    

    /**
     * Get the value of artist
     *
     * @return the value of artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the value of artist
     *
     * @param artist new value of artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
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

    
}
