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
    
   PlaylistDAO pDAO;

   /*
   * Constructor for PlaylistManager
   */
    public PlaylistManager() throws IOException {
        this.pDAO = new PlaylistDAO();
    }
    
    /*
    * Sends information of a new playlist
    */
    public void add(Playlist playlist)
    {
        pDAO.createPlaylist(playlist);
    }
    
    /*
    * returns all playlists
    */
    public List<Playlist> getAllPlaylists() {
        return pDAO.getAllPlaylists();
    }
    
    /*
    * Sends information on which play list should be edited and to what
    */
    public void edit(Playlist playlist) {
        pDAO.edit(playlist);
    }
    
    /*
    * Sends information on which playlist should be removed
    */
    public void remove(Playlist selectedPlaylist) {
        pDAO.remove(selectedPlaylist);
    }

    /*
    * sends information on which playlist should the song be added to
    */
    public void addSongToPlaylist(Playlist playlist, Song song) {
        pDAO.addSongToPlaylist(playlist, song);
    }
    
    /*
    * gets all songs from playlists
    */
    public void getAllSongsFromPlaylist() {
        pDAO.getAllSongsFromPlaylist();
    }

    /*
    * removes a song from a specific playlist
    */
    public void removeSongPl(Song selectedSong, Playlist selectedPlaylist) {
        pDAO.removeSongPl(selectedSong, selectedPlaylist);
    }
}
