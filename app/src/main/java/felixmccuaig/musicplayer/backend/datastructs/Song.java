package felixmccuaig.musicplayer.backend.datastructs;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class Song {

    private String songName, artistName, songLocation, songAlbumArtLocation;
    private long songID, albumID;

    public Song(String songName, String artistName, String songLocation, String songAlbumArtLocation, long songID) {
        this.songName = songName;
        this.artistName = artistName;
        this.songLocation = songLocation;
        this.songID = songID;
        this.songAlbumArtLocation = songAlbumArtLocation;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongLocation() {
        return songLocation;
    }

    public long getSongID() {
        return songID;
    }

    public String getSongAlbumArtLocation() {
        return songAlbumArtLocation;
    }

    public long getAlbumID() {
        return albumID;
    }
}
