package Model;

public class PlayListDetails {

      private int playlistid;
      private int SongID_or_PodcastID;
      private String Type;

      public PlayListDetails(int playlistid, int songID_or_PodcastID, String type)
      {
            this.playlistid = playlistid;
            this.SongID_or_PodcastID = songID_or_PodcastID;
            this.Type = type;
      }

      public int getPlaylistid() {
            return playlistid;
      }

      public void setPlaylistid(int playlistid) {
            this.playlistid = playlistid;
      }

      public int getSongID_or_PodcastID() {
            return SongID_or_PodcastID;
      }

      public void setSongID_or_PodcastID(int songID_or_PodcastID) {
            SongID_or_PodcastID = songID_or_PodcastID;
      }

      public String getType() {
            return Type;
      }

      public void setType(String type) {
            Type = type;
      }

      @Override
      public String toString() {
            return "PlayListDetails{" +
                    "playlistid=" + playlistid +
                    ", SongID_or_PodcastID=" + SongID_or_PodcastID +
                    ", Type='" + Type + '\'' +
                    '}';
      }
}
