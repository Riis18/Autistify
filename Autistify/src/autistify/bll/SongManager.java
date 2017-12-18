/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.bll;

import autistify.be.Song;
import autistify.dal.SongDAO;
import com.jfoenix.controls.JFXSlider;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jesper Riis
 */
public class SongManager {
    
    SongDAO sdao;
    private MediaPlayer mp;
    private String crntPath;

    /*
    * Constructor for SongManager
    */
    public SongManager() throws IOException {
        this.sdao = new SongDAO();
    }
    
    /*
    * a list that returns all songs
    */
    public List<Song> getAllSongs() {
        return sdao.getAllSongs();
    }
    
    /*
    * Sends information of song information
    */
    public void add(Song song) {
        sdao.createSong(song);
    }
    
    /*
    * Sends information of which song should be removed
    */
    public void remove(Song selectedSong) {
        sdao.remove(selectedSong);
    }
    
    /*
    * Sends information on which song should be edited and to what
    */
    public void edit(Song song) {
        sdao.edit(song);
    }
    
    /*
    * Pauses the song
    */
    public void pauseSong(Song songplaying) {
            mp.pause();
    }

    /*
    * gets the specific file location of a the song and plays it
    */
    public void PlaySong(Song songPlaying) {
            songPlaying = songPlaying;
            File soundFile = new File(songPlaying.getPath());
            if (crntPath == null || !crntPath.equals(soundFile.getAbsolutePath())) {
                crntPath = soundFile.toString();
                Media me = new Media(soundFile.toURI().toString());
                if (mp != null) {
                    mp.dispose();
                }
                mp = new MediaPlayer(me);
            }
            mp.play();
            
        }

    /*
    * sets the volume of the song playing
    */
    public void setVolume(JFXSlider vSlider) {
            vSlider.setValue(mp.getVolume() * 100);
            vSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(vSlider.getValue() / 100);
              }
            });
        }

    /*
    * returns the Media Player
    */
    public MediaPlayer getMediaPlayer() {
        return mp;
    }
}
