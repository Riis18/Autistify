/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.bll;

import autistify.be.Song;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ollie
 */
public class SongFilter
{
    

    
    public ArrayList<Song> search(List<Song> songs, String searchQuery) {
        ArrayList<Song> result = new ArrayList<>();
        
        for (Song song : songs) {
            String title = song.getName().trim().toLowerCase();
            String artist = song.getArtist().trim().toLowerCase();
            
            if(title.contains(searchQuery.toLowerCase().trim())
                    || artist.contains(searchQuery.toLowerCase().trim())
                    && !result.contains(song)) {
                result.add(song);
            }
            
        }
        return result;
    }
//    public List<Song> search(List<Song> songs, String searchQuery)
//    {
//        List<Song> result = FXCollections.observableArrayList();
//        
//        for (Song song : songs)
//        {
//            String name = song.getName().trim().toLowerCase();
//            String artist = song.getArtist().trim().toLowerCase();
//            
//            if (name.contains(searchQuery.toLowerCase().trim()) || artist.contains(searchQuery.toLowerCase().trim()))
//            {
//                result.add(song);
//            }
//        }
//        
//        return result;
//    }
}
