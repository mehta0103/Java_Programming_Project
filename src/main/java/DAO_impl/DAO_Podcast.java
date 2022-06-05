package DAO_impl;

import ConnectionPackage.Connection_Class;
import DAO_interfaces.PodcastDAO;
import ExceptionPackage.Jukebox_Exception;
import Model.Podcast;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DAO_Podcast implements PodcastDAO {

           Scanner sc= new Scanner(System.in);
    @Override
    public List<Podcast> getAllPodcast() throws Jukebox_Exception {
        List<Podcast> allpodcast = new ArrayList<>();

        try {
            Connection con = new Connection_Class().EstablishConnection();
            Statement st= con.createStatement();
            ResultSet rs=st.executeQuery("select * from podcast");
            while(rs.next())
            {
                int podcastID= rs.getInt(1);
                int episodeNo=rs.getInt(2);
                String celebrity= rs.getString(3);
                String Songtitle=rs.getString(4);
                String duration= rs.getString(5);
                String publishdate=rs.getString(6);
                String soure =rs.getString(7);
                Podcast pd= new Podcast(podcastID,episodeNo,celebrity,Songtitle,duration,publishdate,soure);
                allpodcast.add(pd);

            }


        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        catch (ClassNotFoundException e1)
        {
            System.out.println(e1);
        }

        return allpodcast ;
    }

    @Override
    public void searchSongs(List<Podcast> allrecords) throws Jukebox_Exception {

        System.out.println("Enter 1 for seaching through celebrity");
        System.out.println("Enter 2 for seaching through publish date");
        int choice=sc.nextInt();
        if(choice==1)
        {
            try {
                Connection con = new Connection_Class().EstablishConnection();
                System.out.println("Enter the name of celebrity");
                String celeb = sc.next();
                PreparedStatement p1= con.prepareStatement("select * from podcast where celebrity=?");
                p1.setString(1,celeb);
                ResultSet rs=p1.executeQuery();
                int flag=0;
                if(rs.next())
                {
                    flag=1;
                }
                if(flag==1)
                {
                    List<Podcast> search = allrecords.stream().filter(q -> q.getCelebrity().equalsIgnoreCase(celeb)).collect(Collectors.toList());
                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "PodcastId", "EpisodeNo", "Celebrity", "SongTitle", "Duration", "Publishdate");
                    for (Podcast p : search)
                    {
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", p.getPodcastid(), p.getEpisodeNo(), p.getCelebrity(), p.getSongTitle(), p.getDuration(), p.getPublishDate());
                    }
                }
                else
                {
                    System.out.println("No podcast avilable for "+celeb);
                }
            }
            catch ( SQLException e)
            {

            }
            catch ( ClassNotFoundException c)
            {

            }
        }
        else if(choice==2)
        {
            try {
                Connection con = new Connection_Class().EstablishConnection();
                System.out.println("Enter the Publishing Date...YYYY-mm-dd");
                String publisdate = sc.next();
                PreparedStatement s = con.prepareStatement("select * from podcast where publishdate=?");
                s.setString(1, publisdate);
                ResultSet rs = s.executeQuery();
                int flag = 0;
                if (rs.next())
                {
                    flag = 1;
                }
                if (flag == 1)
                {
                    List<Podcast> search1 = allrecords.stream().filter(q -> q.getPublishDate().equals(publisdate)).collect(Collectors.toList());
                    System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", "PodcastId", "EpisodeNo", "Celebrity", "SongTitle", "Duration", "Publishdate");
                    for (Podcast p : search1)
                    {
                        System.out.format("%-30s%-30s%-30s%-30s%-30s%-30s\n", p.getPodcastid(), p.getEpisodeNo(), p.getCelebrity(), p.getSongTitle(), p.getDuration(), p.getPublishDate());
                    }
                }
                    else
                    {
                        System.out.println("No podcast published on " + publisdate);
                    }
                }

            catch ( SQLException s)
            {

            }
            catch (ClassNotFoundException e)
            {

            }

        }
        else
        {
            System.out.println(" Please Enter a valid choice");
        }


    }
}
