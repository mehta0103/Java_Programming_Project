package DAO_interfaces;

import ExceptionPackage.Jukebox_Exception;
import Model.Podcast;
import Model.Song;

import java.util.List;


public interface PodcastDAO {


    public List<Podcast> getAllPodcast() throws Jukebox_Exception;
    public  void searchSongs(List<Podcast> allrecords) throws Jukebox_Exception;
}
