/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.bll;

import autistify.be.Song;
import autistify.dal.SongDAO;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jesper Riis
 */
public class SongManager {
    
    SongDAO sdao;

    public SongManager() throws IOException {
        this.sdao = new SongDAO();
    }
    
    public List<Song> getAllSongs() {
        return sdao.getAllSongs();
    }
    
    public void add(Song song) {
        sdao.createSong(song);
    }
}
