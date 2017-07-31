package felixmccuaig.musicplayer.backend;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumID);
                songs.add(new Song(titleText, artistText, locationText, albumArtUri.toString(), songID));
            } while(musicCursor.moveToNext());
        }

        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song song, Song songTwo) {
                if (song.getSongName().toCharArray()[0] > songTwo.getSongName().toCharArray()[0]) {
                    return 1;
                } else if(song.getSongName().toCharArray()[0] == songTwo.getSongName().toCharArray()[0]){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return songs;
    }



}
