package felixmccuaig.musicplayer.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import felixmccuaig.musicplayer.AlbumActivity;
import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.datastructs.Song;

/**
 * Created by Felix McCuaig on 4/08/2017.
 */

public class MediaController implements MediaControlFrameWork {
    List<Album> albums;
    List<Song> songs;

    Button playButton, nextSongButton, lastSongButton;
    TextView songName, artistName, albumName;
    ImageView albumArt;
    SeekBar songProgression;

    MediaPlayer mediaPlayer;
    Activity activity;

    public MediaController(List<List<Button>> buttons, List<List<TextView>> textViews, List<ImageView> albumArt, SeekBar songProgression,
                           MainActivity activity) {
        this.activity = activity;
        //this.mediaPlayer = mediaPlayer;
        initUI(buttons, textViews, albumArt, songProgression);

    }

    public MediaController(List<List<Button>> buttons, List<List<TextView>> textViews, List<ImageView> albumArt, SeekBar songProgression,
                           AlbumActivity activity) {
        this.activity = activity;
        //this.mediaPlayer = mediaPlayer;
        initUI(buttons, textViews, albumArt, songProgression);

    }

    private void initUI(List<List<Button>> buttons, List<List<TextView>> textViews, List<ImageView> albumArt, SeekBar songProgression) {
        initButtons(buttons);
        initTextViews(textViews);
        this.albumArt = albumArt.get(0);
        this.songProgression = songProgression;
    }


    private void initButtons(List<List<Button>> buttons) {
        playButton = buttons.get(0).get(1);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlayButtonPress();
            }
        });

        nextSongButton = buttons.get(0).get(1);
        nextSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextSongButtonPress();
            }
        });

        lastSongButton = buttons.get(0).get(2);
        lastSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLastSongButtonPress();
            }
        });

    }

    private void initTextViews(List<List<TextView>> textViews) {
        songName = textViews.get(0).get(0);
        artistName = textViews.get(0).get(1);
        albumName = textViews.get(0).get(2);
    }

    @Override
    public void onPlayButtonPress() {

    }

    @Override
    public void onNextSongButtonPress() {

    }

    @Override
    public void onLastSongButtonPress() {

    }

    @Override
    public void setSongs(List<Song> songs) {

    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void playSong() {

    }

    @Override
    public void pauseSong() {

    }

    @Override
    public void stopSong() {

    }
}
