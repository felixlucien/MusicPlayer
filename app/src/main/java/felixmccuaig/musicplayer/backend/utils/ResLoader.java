package felixmccuaig.musicplayer.backend.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.datastructs.Song;

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
            int songDurationColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            do {
                String titleText = musicCursor.getString(titleColumn);
                String artistText = musicCursor.getString(artistColumn);
                String locationText = musicCursor.getString(indexColumn);
                long songID = musicCursor.getLong(idColumn);
                long albumID = musicCursor.getLong(albumIDColumn);
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumID);
                long songDuration = musicCursor.getLong(songDurationColumn);
                songs.add(new Song(titleText, artistText, locationText, albumArtUri.toString(), songID, songDuration));
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

    public static List<Album> loadAlbums(ContentResolver resolver) {
        List<Album> albums = new ArrayList<>();

        Uri musicUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = resolver.query(musicUri, null, null, null, null);
        if(musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
            int songListColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
            int albumIDColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID);
            do {
                String titleText = musicCursor.getString(titleColumn);
                String artistText = musicCursor.getString(artistColumn);
                //String locationText = musicCursor.getString(indexColumn);
                long albumID = musicCursor.getLong(idColumn);
                //long albumID = musicCursor.getLong(albumIDColumn);
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumID);
                albums.add(new Album(albumID, titleText, artistText, albumArtUri.toString(), getSongsFromAlbumID(albumID)));
            } while(musicCursor.moveToNext());
        }

        return albums;
    }

    private static List<Song> getSongsFromAlbumID(long albumID) {
        return null;
    }

}
