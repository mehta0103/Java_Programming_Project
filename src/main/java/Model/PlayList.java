package Model;

public class PlayList {

          private int playlistID;
          private String playlistName;
          private int userid;


    public PlayList(int playlistID, String playlistName, int userid)
    {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.userid = userid;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "playlistID=" + playlistID +
                ", playlistName='" + playlistName + '\'' +
                ", userid=" + userid +
                '}';
    }
}
