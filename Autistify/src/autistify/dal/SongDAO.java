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
                    + "(name, artist, album, path, tracklenght)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "pil");
            pstmt.setString(2, "patter");
            pstmt.setString(3, "fisse");
            pstmt.setString(4, "Bryster");
            pstmt.setString(5, "34");
            
            int affected = pstmt.executeUpdate();
            if(affected<1)
                throw new SQLException("pik");
              
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
