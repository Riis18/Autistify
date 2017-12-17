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
    private Song songPlaying;
    private MediaPlayer mp;
    private Media me;
    private String crntPath;

    public SongManager() throws IOException {
        this.sdao = new SongDAO();
    }
    
    public List<Song> getAllSongs() {
        return sdao.getAllSongs();
    }
    
    public void add(Song song) {
        sdao.createSong(song);
    }
    
    public void remove(Song selectedSong) {
        sdao.remove(selectedSong);
    }
    
    public void edit(Song song) {
        sdao.edit(song);
    }
    
    public void pauseSong(Song songplaying) {
            mp.pause();
    }

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

    public void setVolume(JFXSlider vSlider) {
            vSlider.setValue(mp.getVolume() * 100);
            vSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(vSlider.getValue() / 100);
              }
            });
        }

    public void getOnEndOfMedia() {
        mp.getOnEndOfMedia();
        }
}
