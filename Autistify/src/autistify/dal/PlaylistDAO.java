/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.dal;

import autistify.be.Playlist;
import autistify.be.Song;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ollie
 */
public class PlaylistDAO
{
    
    private DataBaseConnector dbConnector;
    private final List<Playlist> allPlaylists = new ArrayList();
    
    /*
    * Constructor for PlaylistDAO
    */
    public PlaylistDAO() throws IOException
    {
        dbConnector = new DataBaseConnector();    
    }
    
    /*
    * Inserts the values of a playlist into the database table "playlist"
    */
    public void createPlaylist(Playlist playlist) 
    {
        try (Connection con = dbConnector.getConnection()) {
           String sql = "INSERT INTO playlist"
                   + "(plName)"
                   + "VALUES (?)";
           PreparedStatement pstmt
                   = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, playlist.getName());
           
           int affected = pstmt.executeUpdate();
           if (affected<1)
                   throw new SQLException("Can't save playlist");
                   
           //Gets the auto generated keys in database and sets it for the playlist
                   ResultSet rs = pstmt.getGeneratedKeys();
                   if (rs.next()) {
                       playlist.setID(rs.getInt(1));
                   }
        }
        
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * Gets all playlists from database table "playlist" with id and names.
    * Adds each to a list and returns the list
    */
    public List<Playlist> getAllPlaylists() {
            
            try (Connection con = dbConnector.getConnection()) {
                PreparedStatement pstmt
                        = con.prepareStatement("SELECT * FROM playlist");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Playlist playlist = new Playlist();
                    playlist.setID(rs.getInt("playlistID"));
                    playlist.setName(rs.getString("plName"));
                    
                    allPlaylists.add(playlist);
                }
            } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
        }
    
    /*
    * Updates the specific values of a playlist in the database by id.
    */
    public void edit(Playlist playlist) {
        try (Connection con = dbConnector.getConnection()) {
            String sql
                    = "UPDATE playlist SET "
                    + "plName=?"
                    + " WHERE playlistID=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, playlist.getName());
            pstmt.setInt(2, playlist.getID());
            
            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Can't edit playlist");
            
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * Removes a playlist from the database by getting the selectedPlaylist id.
    */
    public void remove(Playlist selectedPlaylist) {
        try (Connection con = dbConnector.getConnection()) {
            String sql
                    = "DELETE FROM playlist WHERE playlistID=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getID());
            
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Adds a song into the relations table "playlistSongs" in the database.
    */
    public void addSongToPlaylist(Playlist playlist, Song song) {
        
                try (Connection con = dbConnector.getConnection()) {
           String sql = "INSERT INTO playlistSongs"
                   + "(songID, playlistID)"
                   + "VALUES (?,?)";
           PreparedStatement pstmt
                   = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, song.getId());
           pstmt.setInt(2, playlist.getID());
           
           int affected = pstmt.executeUpdate();
           if (affected<1)
                   throw new SQLException("Can't save song to playlist");
                 
        }
        
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    /*
    * Selects all tables in the database and compare id's. 
    */
    public void getAllSongsFromPlaylist() {
            
        try (Connection con = dbConnector.getConnection()) {
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT * FROM playlistSongs, song, playlist"
                            + " WHERE playlistSongs.songID = song.songID AND playlistSongs.playlistID = playlist.playlistID");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                Song song = new Song();
                playlist.setID(rs.getInt("playlistID"));
                song.setId(rs.getInt("songID"));
                song.setName(rs.getString("name"));
                song.setTrackLenght(rs.getInt("trackLenght"));
                song.setPath(rs.getString("path"));
                
                // Goes through the list of all playlists and if a id on the list is the same as one in database
                // it will get the song list from that specific playlist and add the song that is on the database.
                for (int i = 0; i < allPlaylists.size(); i++) { 
                    if(allPlaylists.get(i).getID() == playlist.getID() ) 
                    {
                    allPlaylists.get(i).getSongList().add(song);
                    }
                }
                
                    
            }
               allPlaylists.clear();
            } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

    /*
    * Deletes a song from a playlist by getting song id and playlist id.
    */
    public void removeSongPl(Song selectedSong, Playlist selectedPlaylist) {
            try (Connection con = dbConnector.getConnection()) {
            String sql
                    = "DELETE FROM playlistSongs WHERE songID=? AND playlistID=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.setInt(2, selectedPlaylist.getID());
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
