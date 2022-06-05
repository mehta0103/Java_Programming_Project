package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import DAO_impl.DAO_Podcast;
import DAO_impl.SongDAO;
import ExceptionPackage.Jukebox_Exception;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void getAllSongs1() throws SQLException, ClassNotFoundException, Jukebox_Exception
    {
        SongDAO s=new SongDAO();
        assertEquals(5,s.getAllSongs1().size());
    }

    @Test
    public void getAllPodcast() throws Jukebox_Exception {
        DAO_Podcast d= new DAO_Podcast();
        assertEquals(12,d.getAllPodcast().size());
    }


}
