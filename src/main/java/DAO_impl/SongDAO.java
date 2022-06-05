package DAO_impl;

import ConnectionPackage.Connection_Class;
import DAO_interfaces.DAO_Song;
import ExceptionPackage.Jukebox_Exception;
import Model.Song;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SongDAO implements DAO_Song {

          Scanner sc= new Scanner(System.in);
    public List<Song> getAllSongs1() throws Jukebox_Exception
    {
        List<Song> songs = new ArrayList<>();
        try {
            Connection con = new Connection_Class().EstablishConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from song");

            while (rs.next())
            {
                 int songID =rs.getInt("Songid");
                 String SongTitle=rs.getString("Songtitle");
                 String artist=rs.getString("artist");
                 String duration=rs.getString("duration");
                 String album=rs.getString("album");
                String genre=rs.getString("genre");
                 String source=rs.getString("Source");
                 Song s= new Song(songID,SongTitle,artist,duration,album,genre,source);
                 songs.add(s);

            }
        }
        catch ( SQLException e)
        {
            System.out.println(e);
        }
        catch ( ClassNotFoundException c)
        {
            System.out.println(c);
        }
        return songs;
    }
    public  void searchSongs(List<Song> allSongs) throws Jukebox_Exception
    {
        try {
            Connection con = new Connection_Class().EstablishConnection();
            System.out.println("Enter 1 for seaching through artist");
            System.out.println("Enter 2 for seaching through album");
            System.out.println("Enter 3 for seaching through genre");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Enter name of the artist");
                String arist = sc.next();
                PreparedStatement p1 = con.prepareStatement("select * from song where artist=?");
                p1.setString(1,arist);
                ResultSet r1= p1.executeQuery();
                if(r1.next())
                {
                    List < Song > sorted1 = allSongs.stream().filter(o -> o.getArtist().equalsIgnoreCase(arist)).sorted((o1, o2) -> o1.getSongTitle().compareToIgnoreCase(o2.getSongTitle())).collect(Collectors.toList());
                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
                    for (Song s : sorted1)
                    {
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", s.getSongID(), s.getSongTitle(), s.getArtist(), s.getAlbum(), s.getDuration(), s.getGenre());
                    }
                }
                else
                {
                    System.out.println("No songs available for arist "+arist);
                }


            } else if (choice == 2) {
                System.out.println("Enter name of the album");
                String album = sc.next();
                PreparedStatement p2 = con.prepareStatement("select * from song where album=?");
                p2.setString(1,album);
                ResultSet r2= p2.executeQuery();
                if(r2.next())
                {
                    List<Song> sorted2 = allSongs.stream().filter(o -> o.getAlbum().equalsIgnoreCase(album)).sorted((o1, o2) -> o1.getSongTitle().compareToIgnoreCase(o2.getSongTitle())).collect(Collectors.toList());
                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
                    for (Song s : sorted2) {
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", s.getSongID(), s.getSongTitle(), s.getArtist(), s.getAlbum(), s.getDuration(), s.getGenre());
                    }
                }
                else
                {
                    System.out.println("No songs avilable for album "+ album);
                }


            } else if (choice == 3) {
                System.out.println("Enter the genre");
                String genre = sc.next();
                PreparedStatement p3 = con.prepareStatement("select * from song where genre=?");
                p3.setString(1,genre);
                ResultSet r3= p3.executeQuery();
                if(r3.next())
                {
                    List<Song> sorted3 = allSongs.stream().filter(o -> o.getGenre().equalsIgnoreCase(genre)).sorted((o1, o2) -> o1.getSongTitle().compareToIgnoreCase(o2.getSongTitle())).collect(Collectors.toList());
                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
                    for (Song s : sorted3) {
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", s.getSongID(), s.getSongTitle(), s.getArtist(), s.getAlbum(), s.getDuration(), s.getGenre());
                    }
                }
                else
                {
                    System.out.println("No songs avilable for genre "+genre);
                }


            } else
            {
                System.out.println("invalid choice");
            }
        }
        catch ( SQLException s)
        {
            System.out.println("problem is "+ s);
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("problem is "+ c);
        }
    }
}
