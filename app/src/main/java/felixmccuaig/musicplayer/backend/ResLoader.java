package felixmccuaig.musicplayer.backend;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import felixmccuaig.musicplayer.Song;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class ResLoader {

    public static List<Song> LoadSongs(ContentResolver resolver) {
        List<Song> songs = new ArrayList<>();

        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = resolver.query(musicUri, null, null, null, null);
        if(musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int indexColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int imagePath = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            do {
                String titleText = musicCursor.getString(titleColumn);
                String artistText = musicCursor.getString(artistColumn);
                String locationText = musicCursor.getString(indexColumn);
                String imageLocation = musicCursor.getString(imagePath);
                long songID = musicCursor.getLong(idColumn);
                songs.add(new Song(titleText, artistText, locationText, imageLocation, songID));
            } while(musicCursor.moveToNext());
        }
        return songs;
    }

}
