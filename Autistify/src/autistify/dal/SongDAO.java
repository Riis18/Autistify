/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.dal;

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
 * @author Jesper Riis
 */
public class SongDAO {
    
    private DataBaseConnector dbConnector;
    
    public SongDAO() throws IOException {
        dbConnector = new DataBaseConnector();
    }
    
    public void createSong(Song song) {
        try (Connection con = dbConnector.getConnection()) {
            String sql = "INSERT INTO song"
                    + "(name, artist, album, genre, path, tracklenght)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, song.getName());
            pstmt.setString(2, song.getArtist());
            pstmt.setString(3, song.getAlbum());
            pstmt.setString(4, song.getGenre());
            pstmt.setString(5, song.getPath());
            pstmt.setInt(6, song.getTrackLenght());
            
            int affected = pstmt.executeUpdate();
            if(affected<1)
                throw new SQLException("pik");
            
            //Get Database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                song.setId(rs.getInt(1));
            }
              
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList();
        
        try (Connection con = dbConnector.getConnection()) {
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT * FROM song");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getInt("songID"));
                song.setName(rs.getString("name"));
                song.setArtist(rs.getString("artist"));
                song.setAlbum(rs.getString("album"));
                song.setGenre(rs.getString("genre"));
                song.setPath(rs.getString("path"));
                song.setTrackLenght(rs.getInt("trackLenght"));
                
                allSongs.add(song);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSongs;
    }
}
