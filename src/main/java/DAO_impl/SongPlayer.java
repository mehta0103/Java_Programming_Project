package DAO_impl;

import ConnectionPackage.Connection_Class;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SongPlayer {

    Scanner sc= new Scanner(System.in);

    public void playSong() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        Connection con = new Connection_Class().EstablishConnection();
        System.out.println("Please Enter a SongId");
        int Songid = sc.nextInt();
        PreparedStatement p1 = con.prepareStatement("select songtitle from song  where Songid=? ");
        p1.setInt(1, Songid);
        ResultSet r1 = p1.executeQuery();

        if (r1.next())
        {
            String Songtitle = r1.getString("SongTitle");
            String songpath = "C:\\Users\\10\\Desktop\\Resources\\" + Songtitle + ".wav";
            AudioPlayer a1 = new AudioPlayer(songpath);
            System.out.println(r1.getString("SongTitle")+"  is playing...");
            a1.PlaySong();

        }
        else
        {
            System.out.println("No Song Available with Songid " + Songid);

        }
    }


      public void playpodcast() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
          Connection con = new Connection_Class().EstablishConnection();
          System.out.println("Enter a podcast id");
          int podcastid= sc.nextInt();
          System.out.println("Enter the episode number");
          int episodeid= sc.nextInt();
          PreparedStatement ps= con.prepareStatement("select * from podcast where PodcastID=? and EpisodeNO=?");
          ps.setInt(1,podcastid);
          ps.setInt(2,episodeid);
          ResultSet rst= ps.executeQuery();
          if(rst.next())
          {
              System.out.println(rst.getString("SongTitle")+ " is playling..........");
              String Songtitle = rst.getString("SongTitle");
              String songpath = "C:\\Users\\10\\Desktop\\Resources\\" + Songtitle + ".wav";
              AudioPlayer a2 = new AudioPlayer(songpath);
              a2.PlaySong();

          }
          else
          {
              System.out.println("No episode avialble for podcastid "+podcastid);
          }

      }
}
