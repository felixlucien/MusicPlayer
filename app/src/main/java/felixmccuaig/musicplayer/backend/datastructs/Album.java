package felixmccuaig.musicplayer.backend.datastructs;

import java.util.List;

/**
 * Created by Felix McCuaig on 1/08/2017.
 */

public class Album {
    private long albumID;
    private String albumName, artistName, albumArtLocation;
    private List<Song> songs;

    public Album(long albumID, String albumName, String artistName, String albumArtLocation, List<Song> songs) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumArtLocation = albumArtLocation;
        this.songs = songs;
    }

    public long getAlbumID() {
        return albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumArtLocation() {
        return albumArtLocation;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
