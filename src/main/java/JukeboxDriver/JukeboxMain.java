package JukeboxDriver;

import ConnectionPackage.Connection_Class;
import DAO_impl.*;
import ExceptionPackage.Jukebox_Exception;
import Model.Podcast;
import Model.Song;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JukeboxMain extends UserDAO {

    public static void main(String[] args) throws Jukebox_Exception, NumberFormatException, UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, ClassNotFoundException {
        UserDAO u1 = new UserDAO();
        SongDAO s1 = new SongDAO();
        DAO_Podcast p1 = new DAO_Podcast();
        Playlist p2 = new Playlist();
        SongPlayer so = new SongPlayer();
        Scanner sc = new Scanner(System.in);
        System.out.println("=================Welcome to audio jukebox=====================");
        System.out.println("You have an account.......? (Y/N)");
        char choice = sc.next().charAt(0);

        if (choice == 'Y' || choice == 'y') {
            u1.existingUser1();
            List<Song> allsongs = s1.getAllSongs1();
            System.out.println("=====================================================================================All songs==============================================================");
            System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
            for (Song s : allsongs) {
                System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", s.getSongID(), s.getSongTitle(), s.getArtist(), s.getAlbum(), s.getDuration(), s.getGenre());
            }
            System.out.println("===================================================================All PodCast==================================================================================");
            List<Podcast> allpodcast = p1.getAllPodcast();
            System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "PodcastId", "EpisodeNo", "Celebrity", "SongTitle", "Duration", "Publishdate");
            for (Podcast p : allpodcast) {
                System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", p.getPodcastid(), p.getEpisodeNo(), p.getCelebrity(), p.getSongTitle(), p.getDuration(), p.getPublishDate());
            }

            Connection con = new Connection_Class().EstablishConnection();
            PreparedStatement ps= con.prepareStatement("select username from user where Userid=?");
            ps.setInt(1,UserId);
            ResultSet r9= ps.executeQuery();
            String name="";
            while (r9.next())
            {
                name= r9.getString("UserName");
            }
            System.out.println("========================================================================All playlists for "+name+"=============================================================");

            p2.displayallplaylisttoUser();
            System.out.println("=====================================================================================================================================================================");
            while (true) {
                System.out.println(" 1. to search a song \n 2. creating playlist\n 3. to delete Song from playlist\n 4 .to search in podcast .\n 5. to view Songs in playlist \n 6. to play a song \n 7. to play podcast");
                int choice2 = sc.nextInt();
                if (choice2 == 1) {
                    List<Song> allSongs1 = s1.getAllSongs1();
                    s1.searchSongs(allSongs1);
                } else if (choice2 == 2) {
                    p2.createPlaylist();
                } else if (choice2 == 3) {
                    p2.deletefromplaylist();
                } else if (choice2 == 4) {
                    List<Podcast> allpodcast1 = p1.getAllPodcast();
                    p1.searchSongs(allpodcast1);
                } else if (choice2 == 5) {
                    p2.displaySongsinplaylist();
                } else if (choice2 == 6) {
                    so.playSong();
                }
                else if(choice2==7)
                {
                    so.playpodcast();
                }
                else
                {
                    System.out.println("invalid choice");
                }
            }
            } else if (choice == 'N' || choice == 'n') {
                System.out.println("Please create a account........");
                u1.UserRegistration();
            } else {
                System.out.println("Invalid choice");

            }

    }


}
