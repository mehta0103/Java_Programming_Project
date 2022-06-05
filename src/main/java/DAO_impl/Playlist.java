package DAO_impl;

import ConnectionPackage.Connection_Class;
import DAO_interfaces.PlaylistInterface;
import ExceptionPackage.Jukebox_Exception;
import Model.PlayList;
import Model.PlayListDetails;

import javax.sound.midi.Soundbank;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Playlist extends UserDAO implements PlaylistInterface {

    Scanner sc = new Scanner(System.in);
    SongPlayer s1= new SongPlayer();
    @Override
    public void createPlaylist() throws Jukebox_Exception {
        try {
            Connection con = new Connection_Class().EstablishConnection();
            System.out.println("Enter the name of playlist");
            String playlistname = sc.next();

            PreparedStatement p2 = con.prepareStatement("select * from playlist where user_id=? and playlistname=?");
            p2.setInt(1,UserId);
            p2.setString(2, playlistname);
            ResultSet rs = p2.executeQuery();
            int flag = 0;
            if (rs.next()) {
                flag = 1;
            }
            if (flag == 1) {

                System.out.println("Playlist allready exsits with name " + playlistname + " for userid =" + UserId);

            } else {
                PreparedStatement p1 = con.prepareStatement("insert into playlist(playlistname,user_id) values(?,?)");
                p1.setString(1, playlistname);
                p1.setInt(2, UserId);
                int row = p1.executeUpdate();
                if (row > 0) {
                    System.out.println(playlistname + " created sucessfully ");

                }
                PreparedStatement s3 = con.prepareStatement("select playlistid from playlist where playlistname=? and user_id=?");
                s3.setString(1, playlistname);
                s3.setInt(2, UserId);
                ResultSet rt = s3.executeQuery();

                int playlistid = 0;
                if (rt.next()) {
                    playlistid = rt.getInt("playlistid");
                }
                System.out.println("The playlist id for " + playlistname + " is " + playlistid);
                System.out.println("Press 1 to add song to " + playlistname);
                System.out.println("Press 2 to add podcast to " + playlistname);
                System.out.println("Press 3 to add song and podcast both to " + playlistname);
                int selection = sc.nextInt();
                if (selection == 1) {
                    System.out.println("Enter songid to add in playlist");
                    int id = sc.nextInt();
                    PreparedStatement pt = con.prepareStatement("select songID_or_podcastID from  playlistdetails where playlist_id=?");
                    pt.setInt(1, playlistid);
                    ResultSet rst = pt.executeQuery();
                    if (rst.next()) {
                        PreparedStatement p4 = con.prepareStatement("select * from song where Songid=?");
                        p4.setInt(1, id);
                        ResultSet r1 = p4.executeQuery();
                        if (r1.next()) {
                            PreparedStatement p3 = con.prepareStatement("insert into playlistdetails(playlist_id,user_id,songID_or_podcastID,type) values(?,?,?,?)");
                            p3.setInt(1, playlistid);
                            p3.setInt(2, UserId);
                            p3.setInt(3, id);
                            p3.setString(4, "Song");
                            int row1 = p3.executeUpdate();
                            if (row1 > 0) {
                                System.out.println("Song Added in" + playlistname + " succesfully");
                                PreparedStatement ps = con.prepareStatement("select * from song where songid=?");
                                ps.setInt(1, id);
                                ResultSet r2 = ps.executeQuery();
                                System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
                                while (r2.next()) {
                                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", r2.getInt(1), r2.getString(2), r2.getString(3), r2.getString(4), r2.getString(5), r2.getString(6));
                                }

                            } else {
                                System.out.println("Problem in Song adding in " + playlistname);
                            }
                        } else {
                            System.out.println("Song not avalaible in jukebox");
                        }

                    } else {
                        System.out.println("Song Allready available in playlist");
                    }
                } else if (selection == 2) {
                    System.out.println("Enter podcastid");
                    int id = sc.nextInt();
                    PreparedStatement pt1 = con.prepareStatement("select songID_or_podcastID from  playlistdetails where playlist_id=?");
                    pt1.setInt(1, playlistid);
                    ResultSet rst1 = pt1.executeQuery();
                    if (rst1.next()) {
                        PreparedStatement p5 = con.prepareStatement("select * from podcast where PodcastID=?");
                        p5.setInt(1, id);
                        ResultSet r3 = p5.executeQuery();
                        if (rs.next()) {
                            PreparedStatement p4 = con.prepareStatement("insert into playlistdetails(playlist_id,user_id,songID_or_podcastID,type) values(?,?,?,?)");
                            p4.setInt(1, playlistid);
                            p4.setInt(2, UserId);
                            p4.setInt(3, id);
                            p4.setString(4, "podcast");
                            int row2 = p4.executeUpdate();
                            if (row2 > 0) {
                                System.out.println("Podcast Added in" + playlistname + " succesfully");
                            } else {
                                System.out.println("Problem in Podcast adding in " + playlistname);
                            }
                        } else {
                            System.out.println("Podcast not available.......");
                        }

                    } else {
                        System.out.println("podcast is allready there in playlist");
                    }
                } else if (selection == 3) {
                    System.out.println("Enter songid");
                    int songid = sc.nextInt();
                    System.out.println("Enter podcastid");
                    int podcastid1 = sc.nextInt();
                    PreparedStatement p6 = con.prepareStatement("select * from song where Songid=?");
                    p6.setInt(1, songid);
                    ResultSet r5 = p6.executeQuery();
                    PreparedStatement p7 = con.prepareStatement("select * from podcast where PodcastID=?");
                    p7.setInt(1, podcastid1);
                    ResultSet r6 = p7.executeQuery();
                    if (r5.next() && r6.next()) {
                        PreparedStatement p3 = con.prepareStatement("insert into playlistdetails(playlist_id,user_id,songID_or_podcastID,type) values(?,?,?,?)");
                        p3.setInt(1, playlistid);
                        p3.setInt(2, UserId);
                        p3.setInt(3, songid);
                        p3.setString(4, "Song");
                        int row3 = p3.executeUpdate();
                        PreparedStatement p4 = con.prepareStatement("insert into playlistdetails(playlist_id,user_id,songID_or_podcastID,type) values(?,?,?,?)");
                        p4.setInt(1, playlistid);
                        p4.setInt(2, UserId);
                        p4.setInt(3, podcastid1);
                        p4.setString(4, "podcast");
                        int row4 = p4.executeUpdate();
                        if (row3 > 0 && row4 > 0) {
                            System.out.println("Podcast  and song Added in" + playlistname + " succesfully");
                        } else {
                            System.out.println("Problem in adding song and Podcast  in " + playlistname);
                        }
                    }


                } else {
                    System.out.println("Enter A valid choice");
                }

            }
        } catch (SQLException s) {
            System.out.println("problem is...." + s);
        } catch (ClassNotFoundException c) {
            System.out.println(c);
        }

    }

    @Override
    public void deletefromplaylist() throws Jukebox_Exception {
        try {
            Connection con = new Connection_Class().EstablishConnection();
            System.out.println("Select a Choice to delete......");
            System.out.println("1. for deleting song from playlist");
            System.out.println("2. for deleting Podcast from playlist");
            System.out.println("2. for deleting song and podcast from playlist");
            int choice = sc.nextInt();
            if (choice == 1) {
                //System.out.println("Enter user id");
               // int userid = sc.nextInt();
                System.out.println("Enter playlist id");
                int playlistid = sc.nextInt();
                PreparedStatement p7 = con.prepareStatement("select songID_or_podcastID from playlistdetails where playlist_id=? and  user_ID=? ");
                p7.setInt(1, playlistid);
                p7.setInt(2, UserId);
                ResultSet r7 = p7.executeQuery();
                System.out.println("Songs in playlist Are....");
                while (r7.next()) {
                    System.out.println(r7.getInt("songID_or_podcastID"));
                }
                System.out.println("Enter song id you want to delete");
                int songid = sc.nextInt();
                PreparedStatement ps1 = con.prepareStatement("delete from playlistdetails where songID_or_podcastID=? and user_id=? and playlist_id=?");
                ps1.setInt(1, songid);
                ps1.setInt(2,UserId);
                ps1.setInt(3, playlistid);
                int row4 = ps1.executeUpdate();
                Statement s1 = con.createStatement();
                ResultSet rs = s1.executeQuery("Select * from playlistdetails where  playlist_id");
                int flag = 0;
                if (rs.next()) {
                    flag = 1;
                }
                if (flag == 1 && row4 > 0) {
                    System.out.println("Song deleted from playlist");
                } else if (flag == 0) {
                    PreparedStatement ps2 = con.prepareStatement("delete from playlist where  playlist_id=?");
                    ps2.setInt(1, playlistid);
                    int row5 = ps2.executeUpdate();
                    if (row5 > 0) {
                        System.out.println("Playlist with playlist id " + playlistid + " is deleted as it is empty");
                    }
                }


            } else if (choice == 2) {

                System.out.println("Enter playlist id");
                int playlistid = sc.nextInt();
                PreparedStatement p7 = con.prepareStatement("select songID_or_podcastID from playlistdetails where playlist_id=? and  user_ID=? ");
                p7.setInt(1, playlistid);
                p7.setInt(2, UserId);
                ResultSet r7 = p7.executeQuery();
                System.out.println("Songs in playlist Are....");
                while (r7.next()) {
                    System.out.println(r7.getInt("songID_or_podcastID"));
                }
                System.out.println("Enter podcast id you want to delete");
                int podcastid = sc.nextInt();
                PreparedStatement ps1 = con.prepareStatement("delete from playlistdetails where songID_or_podcastID=? and user_id=? and playlist_id=?");
                ps1.setInt(1, podcastid);
                ps1.setInt(2, UserId);
                ps1.setInt(3, playlistid);
                int row4 = ps1.executeUpdate();
                Statement s1 = con.createStatement();
                ResultSet rs = s1.executeQuery("Select * from playlistdetails where  playlist_id");
                int flag = 0;
                if (rs.next()) {
                    flag = 1;
                }
                if (flag == 1 && row4 > 0) {
                    System.out.println("podcast deleted from playlist");
                } else if (flag == 0) {
                    PreparedStatement ps2 = con.prepareStatement("delete from playlist where  playlist_id=?");
                    ps2.setInt(1, playlistid);
                    int row5 = ps2.executeUpdate();
                    if (row5 > 0) {
                        System.out.println("Playlist with playlist id " + playlistid + " is deleted as it is empty");
                    }
                }

            } else if (choice == 3) {

                System.out.println("Enter playlist id");
                int playlistid1 = sc.nextInt();
                System.out.println("Enter podcast id you want to delete");
                int podcastid1 = sc.nextInt();
                System.out.println("Enter songid you want to delete");
                int songid = sc.nextInt();
                PreparedStatement p3 = con.prepareStatement("delete from playlistdetails where songID_or_podcastID=? and user_id=? and playlist_id=?");
                p3.setInt(1, songid);
                p3.setInt(2, UserId);
                p3.setInt(3, playlistid1);
                int row6 = p3.executeUpdate();
                PreparedStatement p4 = con.prepareStatement("delete from playlistdetails where songID_or_podcastID=? and user_id=? and playlist_id=?");
                p4.setInt(1, podcastid1);
                p4.setInt(2,  UserId);
                p4.setInt(3, playlistid1);
                ResultSet rs = p4.executeQuery("Select * from playlistdetails where  playlist_id");
                int flag = 0;
                if (rs.next()) {
                    flag = 1;
                }
                if (flag == 1 && row6 > 0) {
                    System.out.println(" song and podcast deleted from playlist");
                } else if (flag == 0) {
                    PreparedStatement ps2 = con.prepareStatement("delete from playlist where  playlist_id=?");
                    ps2.setInt(1, playlistid1);
                    int row5 = ps2.executeUpdate();
                    if (row5 > 0) {
                        System.out.println("Playlist with playlist id " + playlistid1 + " is deleted as it is empty");
                    }
                } else {

                    System.out.println("Enter a valid choice");
                }
            }
        } catch (SQLException s1) {
            System.out.println("problem is" + s1);
        } catch (ClassNotFoundException c1) {
            System.out.println("problem is" + c1);
        }
    }

    public void displayallplaylisttoUser() {
        try {
            Connection con = new Connection_Class().EstablishConnection();
            PreparedStatement p4 = con.prepareStatement("select * from playlist where user_id=?");
            p4.setInt(1, UserId);

            ResultSet rs = p4.executeQuery();
            if(rs.next()) {
                System.out.format("%-30s%-30s\n", "PLAYLIST_ID", "PLATLIST_NAME");
                while (rs.next()) {
                    System.out.format("%-30s%-30s\n", rs.getInt("playlistID"), rs.getString("playlistName"));
                }
            }
            else
            {
                System.out.println("No playlist available.........");
            }
        } catch (SQLException s) {

        } catch (ClassNotFoundException c) {

        }
    }

    public void displaySongsinplaylist() {
        try {
            Connection con = new Connection_Class().EstablishConnection();

            System.out.println("Enter Playlistid to view Songs or podcasts");
            int playlistID = sc.nextInt();
            PreparedStatement ps= con.prepareStatement("select * from playlistdetails where  playlist_id=?");
            ps.setInt(1,playlistID);
            ResultSet r1= ps.executeQuery();
            if(r1.next())
            {
                PreparedStatement p7 = con.prepareStatement("select songID_or_podcastID ,type from playlistdetails where playlist_id=? and  user_ID=? ");
                p7.setInt(1, playlistID);
                p7.setInt(2, UserId);
                ResultSet r7 = p7.executeQuery();
                System.out.println("Contents of playlist.....");
                int Songid;
                String Type="";
                int podcastid;

                while (r7.next())
                {
                    Type = r7.getString("type");
                    if (Type.equalsIgnoreCase("song")) {
                        Songid = r7.getInt("songID_or_podcastID");
                        PreparedStatement pst = con.prepareStatement("select * from song where Songid=?");
                        pst.setInt(1, Songid);
                        ResultSet rst = pst.executeQuery();
                        System.out.println("-------------------------------------------------------------------Songs In Playlist-----------------------------------------------------------------------");
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "SongID", "SongTitle", "Artist", "Album", "Duration", "Genre");
                        while (rst.next()) {
                            System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(5));
                        }
                    }
                    else if (Type.equalsIgnoreCase("Podcast"))
                    {

                        podcastid = r7.getInt("songID_or_podcastID");
                        PreparedStatement pt = con.prepareStatement("select * from podcast where  PodcastID=?");
                        pt.setInt(1, podcastid);
                        ResultSet r6 = pt.executeQuery();
                        System.out.println("---------------------------------------------------------------------Podcasts in playlist---------------------------------------------------------------------");
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "PodcastId", "EpisodeNo", "Celebrity", "SongTitle", "Duration", "Publishdate");
                        while (r6.next()) {
                            System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", r6.getInt(1), r6.getInt(2), r6.getString(3), r6.getString(4), r6.getString(5), r6.getString(6));

                        }

                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }

                }
                System.out.println("Do you want to Play the playlist.......?(Y/N)");
                String choice=sc.next();
                if(choice.equalsIgnoreCase("Y"))
                {
                    System.out.println("Press 1. to play Song\n 2.to play podcast");
                    int choice1=sc.nextInt();
                    if(choice1==1)
                    {

                        PreparedStatement pst=con.prepareStatement("select songID_or_podcastID from playlistdetails where  playlist_id=?");
                        pst.setInt(1,playlistID);
                        ResultSet rts= pst.executeQuery();
                        if(rts.next())
                        {
                           s1.playSong();
                        }
                        else
                        {
                            System.out.println("Song Not availbe in playlist");
                        }



                    }
                    else if(choice1==2)
                    {
                        PreparedStatement pst1=con.prepareStatement("select songID_or_podcastID from playlistdetails where  playlist_id=?");
                        pst1.setInt(1,playlistID);
                        ResultSet rs1=pst1.executeQuery();
                        if(rs1.next())
                        {
                            s1.playpodcast();
                        }
                        else
                        {
                            System.out.println("Podcast is not present in playlist");

                        }
                    }
                }
                else if(choice.equalsIgnoreCase("N"))
                {
                    System.out.println("Main Menu");
                }


            }
            else
            {
                System.out.println("Playlist IS empty.........or no playlist is created with playlistid "+playlistID);
            }

        }
        catch (SQLException s1)
        {
             s1.printStackTrace();
        }
        catch (ClassNotFoundException c1)
        {
            c1.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}