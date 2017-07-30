package felixmccuaig.musicplayer.backend;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import felixmccuaig.musicplayer.Song;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class ResLoader {

    public static List<Song> loadSongs(ContentResolver resolver) {
        List<Song> songs = new ArrayList<>();

        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = resolver.query(musicUri, null, null, null, null);
        if(musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int indexColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumIDColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            do {
                String titleText = musicCursor.getString(titleColumn);
                String artistText = musicCursor.getString(artistColumn);
                String locationText = musicCursor.getString(indexColumn);
                long songID = musicCursor.getLong(idColumn);
                long albumID = musicCursor.getLong(albumIDColumn);

                String albumArtLocation = albumArtLocation(albumID, resolver);

                Log.d("ALBUM ART LOCATION", albumArtLocation);

                songs.add(new Song(titleText, artistText, locationText, albumArtLocation, songID));
            } while(musicCursor.moveToNext());
        }
        return songs;
    }

    public static String albumArtLocation(long albumID, ContentResolver resolver) {
        Uri musicUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        //Cursor musicCursor = resolver.query(musicUri, null, null, null, null);

        Cursor musicCursor = resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID+ "=?",
                new String[] {String.valueOf(albumID)},
                null);


        if(musicCursor != null && musicCursor.moveToFirst()) {


            return musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
        } else {
            return null;
        }
    }



}
