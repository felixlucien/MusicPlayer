package felixmccuaig.musicplayer.backend;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.datastructs.Artist;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.ui.MediaControlFrameWork;

/**
 * Created by Felix McCuaig on 3/08/2017.
 */

public class MediaService extends Service implements MediaControlFrameWork, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    public static final String TAG = "Media Service";

    private final IBinder iBinder = new MediaBinder();
    private MediaPlayer mediaPlayer;

    private List<Song> songs;
    private Song currentSong;
    private List<Album> albums;
    private Album currentAlbum;
    private List<Artist> artists;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPlayButtonPress() {
        if(mediaPlayer.isPlaying()) {
            pauseSong();
        } else {
            playSong();
        }
    }

    @Override
    public void onNextSongButtonPress() {
        nextSong();
    }

    @Override
    public void onLastSongButtonPress() {
        lastSong();
    }

    @Override
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public void updateSong(Song song) {
        Log.d("UPDATE SONG", "SONG UPDATED");
        mediaPlayer.reset();
        currentSong = song;
        try {
            mediaPlayer.setDataSource(song.getSongLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playSong() {
        mediaPlayer.start();
    }

    @Override
    public void nextSong() {
        updateSong(currentSong = songs.get(songs.indexOf(currentSong) + 1));
    }

    @Override
    public void lastSong() {
        updateSong(currentSong = songs.get(songs.indexOf(currentSong) - 1));
    }

    @Override
    public void pauseSong() {
        mediaPlayer.pause();
    }

    @Override
    public void stopSong() {
        mediaPlayer.stop();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playSong();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        nextSong();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    public class MediaBinder extends Binder {
        public MediaService getService() {
            return MediaService.this;
        }
    }

}
