package DAO_interfaces;

import ExceptionPackage.Jukebox_Exception;
import Model.Song;

import java.util.List;

public interface DAO_Song {


        public List<Song> getAllSongs1() throws Jukebox_Exception;
        public  void searchSongs(List<Song> allSongs) throws Jukebox_Exception;
}
