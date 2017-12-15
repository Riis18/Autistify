/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.bll;

import autistify.be.Playlist;
import autistify.be.Song;
import autistify.dal.PlaylistDAO;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ollie
 */
public class PlaylistManager
{
    
    private PlaylistDAO pDAO;

    public PlaylistManager() throws IOException {
        this.pDAO = new PlaylistDAO();
    }
    
    public void add(Playlist playlist)
    {
        pDAO.createPlaylist(playlist);
    }
    
    public List<Playlist> getAllPlaylists() {
        return pDAO.getAllPlaylists();
    }
    
    public void edit(Playlist playlist) {
        pDAO.edit(playlist);
    }
    
    public void remove(Playlist selectedPlaylist) {
        pDAO.remove(selectedPlaylist);
    }

    public void addSongToPlaylist(Playlist playlist, Song song) {
        pDAO.addSongToPlaylist(playlist, song);
    }
    
    public void getAllSongsFromPlaylist() {
        pDAO.getAllSongsFromPlaylist();
    }

    public void removeSongPl(Song selectedSong, Playlist selectedPlaylist) {
        pDAO.removeSongPl(selectedSong, selectedPlaylist);
    }
}
