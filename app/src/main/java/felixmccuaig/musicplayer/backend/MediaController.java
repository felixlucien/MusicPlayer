package felixmccuaig.musicplayer.backend;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.backend.utils.StringShortener;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class MediaController {

    private MainActivity mainActivity;
    private List<Song> songs;
    private Song currentSong;
    private int songPos;

    private Button playPauseButton, nextSongButton, lastSongButton;
    private TextView songName, artistName;
    private ImageView songIcon;
    private MediaPlayer mediaPlayer;

    public MediaController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        songName = (TextView) mainActivity.findViewById(R.id.song_name_view);
        artistName = (TextView) mainActivity.findViewById(R.id.artist_name_view);
        songIcon = (ImageView) mainActivity.findViewById(R.id.album_art_icon);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                updatePlayState();
            }
        });

        playPauseButton = (Button) mainActivity.findViewById(R.id.play_pause_button);
        nextSongButton = (Button) mainActivity.findViewById(R.id.next_song_button);
        lastSongButton = (Button) mainActivity.findViewById(R.id.last_song_button);



        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayState();
            }
        });

        nextSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songPos++;
                updateSong(songs.get(songPos), songPos);
            }
        });


        lastSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songPos--;
                updateSong(songs.get(songPos), songPos);
            }
        });
    }

    public void updateSong(Song song, int songPos) {
        mediaPlayer.reset();
        this.songPos = songPos;
        currentSong = song;

        Log.d("SONG NAME", currentSong.getSongName());
        Log.d("ARTIST NAME", currentSong.getArtistName());
        Log.d("SONG POS", songPos + "");

        if(songName != null) {
            songName.setText(StringShortener.shortenString(currentSong.getSongName(), mainActivity.getResources(), null));
        }

        if(artistName != null) {
            artistName.setText(StringShortener.shortenString(currentSong.getArtistName(), mainActivity.getResources(), null));
        }

        Picasso.with(mainActivity).load(currentSong.getSongAlbumArtLocation()).resize(100, 100).into(songIcon, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

        try {
            mediaPlayer.setDataSource(currentSong.getSongLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.prepareAsync();
    }

    private void updatePlayState() {
        if(mediaPlayer.isPlaying()) {
            pause();
            playPauseButton.setText(">");
        } else {
            play();
            playPauseButton.setText("||");
        }
    }

    private void play() {
        mediaPlayer.start();
    }

    private void pause() {
        mediaPlayer.pause();
    }

    private void stop() {
        mediaPlayer.stop();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
