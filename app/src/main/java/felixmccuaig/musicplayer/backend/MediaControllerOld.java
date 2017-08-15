package felixmccuaig.musicplayer.backend;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.backend.utils.StringShortener;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class MediaControllerOld {

    private MainActivity mainActivity;
    private List<Song> songs;
    private Song currentSong;
    private int songPos;
    private boolean isSlidingPanelExpanded;

    private Button playPauseButton, nextSongButton, lastSongButton,
            expandedPlayPauseButton, expandedNextSongButton, expandedLastSongButton;
    private TextView songName, artistName;
    private ImageView songIcon, expandedSongIcon;
    private MediaPlayer mediaPlayer;
    private SeekBar songProgression;
    private Handler seekBarHandler;



    public MediaControllerOld(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        songName = (TextView) mainActivity.findViewById(R.id.song_name_view);
        artistName = (TextView) mainActivity.findViewById(R.id.artist_name_view);
        songIcon = (ImageView) mainActivity.findViewById(R.id.album_art_icon);

        seekBarHandler = new Handler();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                setupSeekbar();
                updatePlayState();
            }
        });

        playPauseButton = (Button) mainActivity.findViewById(R.id.play_pause_button);
        nextSongButton = (Button) mainActivity.findViewById(R.id.next_song_button);
        lastSongButton = (Button) mainActivity.findViewById(R.id.last_song_button);

        //expandedPlayPauseButton = (Button) mainActivity.findViewById(R.id.expanded_play_song);
        //expandedNextSongButton = (Button) mainActivity.findViewById(R.id.expanded_next_song);
        //expandedLastSongButton = (Button) mainActivity.findViewById(R.id.expanded_last_song);
        expandedSongIcon = (ImageView) mainActivity.findViewById(R.id.expanded_album_art);
        expandedSongIcon.setVisibility(View.INVISIBLE);

        songProgression = (SeekBar) mainActivity.findViewById(R.id.song_progression_expanded);

        songProgression.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SlidingUpPanelLayout panelLayout = (SlidingUpPanelLayout) mainActivity.findViewById(R.id.sliding_layout);
        panelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    isSlidingPanelExpanded = false;
                    updateButtons(true);
                } else if(newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    isSlidingPanelExpanded = true;
                    updateButtons(false);
                }
            }
        });

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

        expandedPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayState();
            }
        });

        expandedNextSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songPos++;
                updateSong(songs.get(songPos), songPos);
            }
        });

        expandedLastSongButton.setOnClickListener(new View.OnClickListener() {
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

        Picasso.with(mainActivity).load(currentSong.getSongAlbumArtLocation()).into(expandedSongIcon, new Callback() {
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
            expandedPlayPauseButton.setText(">");
        } else {
            play();
            playPauseButton.setText("||");
            expandedPlayPauseButton.setText("||");
        }
    }

    private void updateButtons(boolean isCollapsed) {
        if(isCollapsed) {
            playPauseButton.setVisibility(View.VISIBLE);
            nextSongButton.setVisibility(View.VISIBLE);
            lastSongButton.setVisibility(View.VISIBLE);
            songName.setVisibility(View.VISIBLE);
            artistName.setVisibility(View.VISIBLE);
            songIcon.setVisibility(View.VISIBLE);
            expandedSongIcon.setVisibility(View.INVISIBLE);
        } else {
            playPauseButton.setVisibility(View.INVISIBLE);
            nextSongButton.setVisibility(View.INVISIBLE);
            lastSongButton.setVisibility(View.INVISIBLE);
            songName.setVisibility(View.INVISIBLE);
            artistName.setVisibility(View.INVISIBLE);
            songIcon.setVisibility(View.INVISIBLE);
            expandedSongIcon.setVisibility(View.VISIBLE);
        }

    }

    private void setupSeekbar() {
        int duration = mediaPlayer.getDuration();
        int delay = 1000;

        songProgression.setMax(duration);

        seekBarHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                songProgression.setProgress(mediaPlayer.getCurrentPosition());
                if(mediaPlayer.isPlaying()) {
                    seekBarHandler.postDelayed(this, 1000);
                }
            }
        }, delay);
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
