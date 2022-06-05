package Model;

import java.sql.Date;

public class Podcast {

     private int podcastid;
     private int episodeNo;
     private String celebrity;
     private String SongTitle;
     private String duration;
     private String publishDate;
     private String source;



    public Podcast(int podcastid, int episodeNo, String celebrity, String songTitle, String duration, String publishdate, String source) {
        this.podcastid = podcastid;
        this.episodeNo = episodeNo;
        this.celebrity = celebrity;
       this.SongTitle = songTitle;
        this.duration = duration;
        this.source = source;
        this.publishDate= publishdate;
    }

    public int getPodcastid() {
        return podcastid;
    }

    public void setPodcastid(int podcastid) {
        this.podcastid = podcastid;
    }

    public int getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(int episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "podcastid=" + podcastid +
                ", episodeNo=" + episodeNo +
                ", celebrity='" + celebrity + '\'' +
                ", SongTitle='" + SongTitle + '\'' +
                ", duration='" + duration + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
