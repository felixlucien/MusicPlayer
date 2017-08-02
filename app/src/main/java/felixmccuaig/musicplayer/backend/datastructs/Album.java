package felixmccuaig.musicplayer.backend.datastructs;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felix McCuaig on 1/08/2017.
 */

public class Album implements Parcelable {
    private long albumID;
    private String albumName, artistName, albumArtLocation;
    private List<Song> songs;

    private String mID;
    private String mText;

    public Album(long albumID, String albumName, String artistName, String albumArtLocation, List<Song> songs) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumArtLocation = albumArtLocation;
        this.songs = songs;
    }

    protected Album(Parcel in) {
        albumID = in.readLong();
        albumName = in.readString();
        artistName = in.readString();
        albumArtLocation = in.readString();
        songs = in.readArrayList(Song.class.getClassLoader());
        mID = in.readString();
        mText = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public void setSongs(List<Song> songs) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(albumID);
        parcel.writeString(albumName);
        parcel.writeString(artistName);
        parcel.writeString(albumArtLocation);
        parcel.writeList(songs);
        parcel.writeString(mID);
        parcel.writeString(mText);
    }
}
