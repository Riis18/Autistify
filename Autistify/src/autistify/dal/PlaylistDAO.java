/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.dal;

import autistify.be.Playlist;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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
    
    
    public PlaylistDAO() throws IOException
    {
        dbConnector = new DataBaseConnector();    
    }
    
    public void createPlaylist(Playlist playlist) 
    {
        try (Connection con = dbConnector.getConnection()) {
           String sql = "INSERT INTO playlist"
                   + "(name)"
                   + "VALUES (?)";
           PreparedStatement pstmt
                   = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, playlist.getName());
           
           int affected = pstmt.executeUpdate();
           if (affected<1)
                   throw new SQLException("Can't save playlist");
                   
                   ResultSet rs = pstmt.getGeneratedKeys();
                   if (rs.next()) {
                       playlist.setID(rs.getInt(1));
                   }
        }
        
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<Playlist> getAllPlaylists() {
            List<Playlist> allPlaylists = new ArrayList();
            
            try (Connection con = dbConnector.getConnection()) {
                PreparedStatement pstmt
                        = con.prepareStatement("SELECT * FROM playlist");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Playlist playlist = new Playlist();
                    playlist.setID(rs.getInt("songID"));
                    playlist.setName(rs.getString("name"));
                    
                    allPlaylists.add(playlist);
                }
            } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
        }
    
    public void edit(Playlist playlist) {
        try (Connection con = dbConnector.getConnection()) {
            String sql
                    = "UPDATE playlist SET "
                    + "name=?"
                    + "WHERE playlistID=?";
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
    
}
