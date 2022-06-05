package Model;

public class Song {

    private int songID;
    private String SongTitle;
    private String artist;
    private String duration;
    private String album;
    private String genre;
    private String source;

    public Song(int songID, String songTitle, String artist, String duration, String album, String genre, String source)
    {
        this.songID = songID;
        SongTitle = songTitle;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
        this.source = source;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", SongTitle='" + SongTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
