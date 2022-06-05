package DAO_interfaces;

import ExceptionPackage.Jukebox_Exception;
import Model.PlayList;
import Model.PlayListDetails;

import java.util.List;

public interface PlaylistInterface
{

         public void createPlaylist() throws Jukebox_Exception;
         public void deletefromplaylist() throws Jukebox_Exception;


}
