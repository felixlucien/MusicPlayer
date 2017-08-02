package felixmccuaig.musicplayer.backend.datastructs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class Song implements Parcelable{

    private String songName, artistName, songLocation, songAlbumArtLocation;
    private long songID, albumID, songDuration;

    private String mID;
    private String mText;


    public Song(String songName, String artistName, String songLocation, String songAlbumArtLocation, long songID, long songDuration) {
        this.songName = songName;
        this.artistName = artistName;
        this.songLocation = songLocation;
        this.songID = songID;
        this.songAlbumArtLocation = songAlbumArtLocation;
        this.songDuration = songDuration;
    }

    protected Song(Parcel in) {
        songName = in.readString();
        artistName = in.readString();
        songLocation = in.readString();
        songAlbumArtLocation = in.readString();
        songID = in.readLong();
        albumID = in.readLong();
        songDuration = in.readLong();
        mID = in.readString();
        mText = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    public long getSongDuration() {
        return songDuration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(songName);
        parcel.writeString(artistName);
        parcel.writeString(songLocation);
        parcel.writeString(songAlbumArtLocation);
        parcel.writeLong(songID);
        parcel.writeLong(albumID);
        parcel.writeLong(songDuration);
        parcel.writeString(mID);
        parcel.writeString(mText);
    }
}
